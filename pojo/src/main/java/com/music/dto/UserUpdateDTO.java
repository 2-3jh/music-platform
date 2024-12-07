package com.music.dto;


import lombok.Data;

@Data
public class UserUpdateDTO {
    private Integer userId;
    //用户的名字
    private String name;
    //用户的性别
    private String gender;
    //用户的爱好
    private String hobby;
    //密码
    private String password;

}
