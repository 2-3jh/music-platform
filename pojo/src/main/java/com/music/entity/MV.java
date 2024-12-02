package com.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MV {
    //mv的id
    private Integer id;
    //名称
    private String name;
    //对mv的描述
    private String describe;
    //mv的作者
    private String author;
    //mv的展示图片
    private String photo;
    //mv视频的详细地址
    private String video;
    //mv的分类
    private String category;

}
