package com.music.controller;


import com.music.annotation.Log;
import com.music.dto.UserEntryDTO;
import com.music.dto.UserUpdateDTO;
import com.music.entity.User;
import com.music.result.Result;
import com.music.service.UserService;
import com.music.service.impl.RedisServiceImpl;
import com.music.utils.BeanCopyUtils;
import com.music.utils.JwtUtil;
import com.music.utils.MyContext;
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

    @Autowired
    RedisServiceImpl redisService;


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
        UserLoginVO userLoginVO = BeanCopyUtils.copyBean(user, UserLoginVO.class);
        userLoginVO.setToken(token);

        //将信息存入redis中
        redisService.createUser(userLoginVO.getId().toString(),userLoginVO);
        return Result.success(userLoginVO);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping()
    @Log(doingName = "获取用户信息")
    public Result getUserInfo() {
//        UserInfoVO userInfoVO = userService.getUserInfo();
        UserLoginVO userLoginVO = redisService.readUser(MyContext.getCurrentId().toString());
        UserInfoVO userInfoVO = BeanCopyUtils.copyBean(userLoginVO, UserInfoVO.class);
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

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    @Log(doingName = "退出登录")
    public Result Logout(){
        redisService.deleteUser(MyContext.getCurrentId().toString());
        return Result.success();
    }
}
