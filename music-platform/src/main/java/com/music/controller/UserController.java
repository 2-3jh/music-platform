package com.music.controller;


import com.music.annotation.Log;
import com.music.dto.UserEntryDTO;
import com.music.dto.UserUpdateDTO;
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
    @Log(doingName = "用户注册")
    public Result userRegister(@RequestBody UserEntryDTO userEntryDTO) {
        userService.register(userEntryDTO);
        return Result.success();
    }

    /**
     * 用户登录
     * @param userEntryDTO
     * @return
     */
    @PostMapping("/login")
    @Log(doingName = "用户登录")
    public Result userLogin(@RequestBody UserEntryDTO userEntryDTO) {
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
    @Log(doingName = "获取用户信息")
    public Result getUserInfo() {
        UserInfoVO userInfoVO = userService.getUserInfo();
        return Result.success(userInfoVO);
    }

    /**
     * 修改用户的信息
     * @return
     */
    @PutMapping()
    @Log(doingName = "修改用户的信息")
    public Result UpdateUserInfo(@RequestBody UserUpdateDTO userUpdateDTO) {
        userService.updateUser(userUpdateDTO);
        return Result.success();
    }
}
