package com.music.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表(User)表实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
      //用户的id
       private Integer id;
      //用户的名字       
       private String name;
      //用户的性别       
       private String gender;
      //用户的爱好       
       private String hobby;
       //密码
        private String password;
}
