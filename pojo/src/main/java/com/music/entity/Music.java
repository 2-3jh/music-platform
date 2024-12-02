package com.music.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Music {
    //id列
    private Integer id;
    //名称
    private String name;
    //作词
    private String wording;
    //作曲人
    private String writeMusic;
    //背景图片
    private String photo;
    //音频地址
    private String audio;
    //music的分类
    private String category;
    //歌词
    private String lyric;
    private String singer;
}
