package com.music.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Playlist {
      //歌单的id
       private Integer id;
      //歌单的名称       
       private String name;
       //创建用户
       private Integer createUser;
       //创建时间
       private Date createTime;
}
