package com.music.vo;

import lombok.Data;

@Data
public class MusicItemVO {
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
    //歌词
    private String lyric;
    private String singer;
}
