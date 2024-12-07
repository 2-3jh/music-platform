package com.music.service.impl;


import com.music.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisServiceImpl {


    /**
     * 自动注入RedisTemplate，用于操作Redis数据库。
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 在Redis中创建用户。
     *
     * @param key 用户在Redis中的键。
     * @param userLoginVO 要存储的对象。
     * 此方法通过键值对的形式将用户对象存储到Redis中。
     */
    public void createUser(String key, UserLoginVO userLoginVO) {
        redisTemplate.opsForValue().set(key, userLoginVO);
    }

    /**
     * 从Redis中读取用户。
     *
     * @param key 用户在Redis中的键。
     * @return 返回与给定键关联的对象。
     * 通过键值对的形式从Redis中获取用户对象。
     */
    public UserLoginVO readUser(String key) {
        return (UserLoginVO) redisTemplate.opsForValue().get(key);
    }

    /**
     * 更新Redis中的用户。
     *
     * @param key 用户在Redis中的键。
     * @param userLoginVO 要更新的对象。
     * 通过键值对的形式更新Redis中的用户对象。
     */
    public void updateUser(String key, UserLoginVO userLoginVO) {
        redisTemplate.opsForValue().set(key, userLoginVO);
    }

    /**
     * 从Redis中删除用户。
     *
     * @param key 用户在Redis中的键。
     * 删除Redis中与给定键关联的用户对象。
     */
    public void deleteUser(String key) {
        redisTemplate.delete(key);
    }
}