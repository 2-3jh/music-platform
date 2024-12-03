package com.music.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSong {
       //歌单的id
       private Integer playlistId;
       //歌曲的id
       private Integer songId;
}
