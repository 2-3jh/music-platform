package com.music.dto;


import lombok.Data;

@Data
public class PlaylistDelDTO {
    //要删除的id
    private Integer id;

    private Integer userId;
}
