package com.music.mapper;


import com.music.dto.UserEntryDTO;
import com.music.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from User where name = #{name}")
    User getUserByName(String name);

    void insertUser(User user);

    @Select("select * from User where id = #{id}")
    User getUserById(Integer currentId);

    void updateUser(User user);
}
