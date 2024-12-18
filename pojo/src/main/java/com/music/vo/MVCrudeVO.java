package com.music.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MVCrudeVO {
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
}
