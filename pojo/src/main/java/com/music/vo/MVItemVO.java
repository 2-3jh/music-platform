package com.music.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MVItemVO {
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
