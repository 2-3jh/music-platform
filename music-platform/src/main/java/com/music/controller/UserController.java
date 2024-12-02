package com.music.controller;


import com.music.dto.UserEntryDTO;
import com.music.entity.User;
import com.music.result.Result;
import com.music.service.UserService;
import com.music.utils.BeanCopyUtils;
import com.music.utils.JwtUtil;
import com.music.vo.UserInfoVO;
import com.music.vo.UserLoginVO;
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
    @PostMapping("/login")
    public Result userLogin(@RequestBody UserEntryDTO userEntryDTO) {
        log.info("用户登录:{}", userEntryDTO);
        User user = userService.login(userEntryDTO);

        //登录成功下发Jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("name",user.getName());
        String token = JwtUtil.generateJWT(claims);

        //封装
        UserLoginVO userLogonVO = BeanCopyUtils.copyBean(user, UserLoginVO.class);
        userLogonVO.setToken(token);
        return Result.success(userLogonVO);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping()
    public Result getUserInfo() {
        log.info("获取用户信息");
        UserInfoVO userInfoVO = userService.getUserInfo();
        return Result.success(userInfoVO);
    }
}
