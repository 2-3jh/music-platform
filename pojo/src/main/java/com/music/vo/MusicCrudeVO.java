package com.music.vo;

import lombok.Data;

@Data
public class MusicCrudeVO {

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

    private String singer;
}
