package com.music.service.impl;

import com.music.constant.Constant;
import com.music.dto.UserEntryDTO;
import com.music.dto.UserUpdateDTO;
import com.music.entity.Playlist;
import com.music.entity.User;
import com.music.exception.MyException;
import com.music.mapper.PlaylistMapper;
import com.music.mapper.UserMapper;
import com.music.service.UserService;
import com.music.utils.BeanCopyUtils;
import com.music.utils.MyContext;
import com.music.vo.UserInfoVO;
import com.music.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper  userMapper;

    @Autowired
    PlaylistMapper playlistMapper;

    @Autowired
    private RedisServiceImpl redisService;

    /**
     * 用户注册
     * @param userRegisterDTO
     */
    @Transactional
    @Override
    public void register(UserEntryDTO userRegisterDTO) {

        //判断用户名是否空
        if(userRegisterDTO.getName() == null) {
            throw new MyException(Constant.NAME_NOT_NULL);
        }

        //判断用户名是否存在
        User user = userMapper.getUserByName(userRegisterDTO.getName());

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

        MyContext.setCurrentId(newUser.getId());
        playlistMapper.save(new Playlist(null,"我喜欢",null,null));
    }


    /**
     * 用户登录
     * @param userEntryDTO
     * @return
     */
    @Override
    public User login(UserEntryDTO userEntryDTO) {

        //判断用户名是否空
        if(userEntryDTO.getName() == null) {
            throw new MyException(Constant.NAME_NOT_NULL);
        }

        //通过用户名获取用户
        User user = userMapper.getUserByName(userEntryDTO.getName());

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

    /**
     * 获取当前用户的信息
     * @return
     */
    @Override
    public UserInfoVO getUserInfo() {
        Integer currentId = MyContext.getCurrentId();
        User user = userMapper.getUserById(currentId);
        UserInfoVO userInfoVO = BeanCopyUtils.copyBean(user, UserInfoVO.class);
        return userInfoVO;
    }

    /**
     * 更新用户的信息
     * @param userUpdateDTO
     */
    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        long currentId = MyContext.getCurrentId();
        User user = null;

        //判断用户名是否空
        if(userUpdateDTO.getName() == null) {
            throw new MyException(Constant.NAME_NOT_NULL);
        }


        //判断是否更新用户名
        if (userUpdateDTO.getName() != null) {
            //查询用户名是否已经存在
            user = userMapper.getUserByName(userUpdateDTO.getName());

            if(user != null&&user.getId()!=currentId) {
                throw new MyException(Constant.USER_NAME_EXIST);
            }
        }


        //进行user的封装
        user = BeanCopyUtils.copyBean(userUpdateDTO, User.class);
        user.setId((int) currentId);

        //进行密码加密

        if (userUpdateDTO.getPassword() != null&& StringUtils.hasText(userUpdateDTO.getPassword())) {
            user.setPassword(DigestUtils.md5DigestAsHex(userUpdateDTO.getPassword().getBytes()));
        }else{
            throw new MyException(Constant.PASSWORD_EMPTY);
        }

        //更新
        userMapper.updateUser(user);

        //更新redis中的数据
        UserLoginVO userLoginVO = redisService.readUser(user.getId().toString());
        userLoginVO.setName(userUpdateDTO.getName());
        userLoginVO.setGender(userUpdateDTO.getGender());
        userLoginVO.setHobby(userUpdateDTO.getHobby());
        redisService.updateUser(user.getId().toString(),userLoginVO);

    }
}
