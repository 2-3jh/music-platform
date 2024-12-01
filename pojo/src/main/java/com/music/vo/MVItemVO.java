package com.music.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MVItemVO {
    //mv的id
    private Integer id;
    //对mv的描述
    private String describe;
    //mv的作者
    private String author;
    //mv视频的详细地址
    private String video;


}
