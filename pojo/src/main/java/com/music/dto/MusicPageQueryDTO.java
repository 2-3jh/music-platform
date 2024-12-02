package com.music.dto;

import lombok.Data;

@Data
public class MusicPageQueryDTO {


    //分类
    private String category;

    //页码
    private Integer page;

    //每页的记录数
    private Integer pageSize;

}


