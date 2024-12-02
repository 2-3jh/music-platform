package com.music.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    //用户的id
    private Integer id;
    //用户的名字
    private String name;
    //用户的性别
    private String gender;
    //用户的爱好
    private String hobby;

}

