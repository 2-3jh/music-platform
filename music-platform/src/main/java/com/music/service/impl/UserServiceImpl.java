package com.music.service.impl;

import com.music.constant.Constant;
import com.music.dto.UserEntryDTO;
import com.music.entity.User;
import com.music.exception.MyException;
import com.music.mapper.UserMapper;
import com.music.service.UserService;
import com.music.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper  userMapper;

    /**
     * 用户注册
     * @param userRegisterDTO
     */
    @Override
    public void register(UserEntryDTO userRegisterDTO) {
        //判断用户名是否存在
        User user = userMapper.getUserByName(userRegisterDTO);

        if(user != null) {
            throw new MyException(Constant.USER_EXIST);
        }

        //插入新用户
        User newUser = BeanCopyUtils.copyBean(userRegisterDTO,User.class);
        //填充默认值
        newUser.setGender("男");
        newUser.setHobby("无");
        newUser.setPassword(DigestUtils.md5DigestAsHex(newUser.getPassword().getBytes()));
        userMapper.insertUser(newUser);
    }


    /**
     * 用户登录
     * @param userEntryDTO
     * @return
     */
    @Override
    public User logon(UserEntryDTO userEntryDTO) {
        //通过用户名获取用户
        User user = userMapper.getUserByName(userEntryDTO);

        //判断用户是否存在
        if(user == null) {
            throw new MyException(Constant.USER_NOT_EXIST);
        }

        //判断密码是否正确
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(userEntryDTO.getPassword().getBytes()))) {
            throw new MyException(Constant.PASSWORD_ERROR);
        }

        //返回
        return user;
    }
}
