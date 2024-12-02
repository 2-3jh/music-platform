package com.music.controller;


import com.music.dto.UserEntryDTO;
import com.music.entity.User;
import com.music.result.Result;
import com.music.service.UserService;
import com.music.utils.BeanCopyUtils;
import com.music.utils.JwtUtil;
import com.music.vo.UserLogonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户注册
     * @param userEntryDTO
     * @return
     */
    @PostMapping("/register")
    public Result userRegister(@RequestBody UserEntryDTO userEntryDTO) {
        log.info("用户注册：{}", userEntryDTO);
        userService.register(userEntryDTO);
        return Result.success();
    }

    /**
     * 用户登录
     * @param userEntryDTO
     * @return
     */
    @PostMapping("/logon")
    public Result userLogon(@RequestBody UserEntryDTO userEntryDTO) {
        log.info("用户登录:{}", userEntryDTO);
        User user = userService.logon(userEntryDTO);

        //登录成功下发Jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("name",user.getName());
        String token = JwtUtil.generateJWT(claims);

        //封装
        UserLogonVO userLogonVO = BeanCopyUtils.copyBean(user, UserLogonVO.class);
        userLogonVO.setToken(token);
        return Result.success(userLogonVO);
    }

//    @GetMapping("/{id}")
//    public Result getUserById(@PathVariable  int id) {
//        log.info("获取用户信息：{}",id);
//        userService.getUserById();
//    }
}
