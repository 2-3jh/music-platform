package com.music.mapper;


import com.music.annotation.Save;
import com.music.entity.Playlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlaylistMapper {

    @Select("select * from playlist where name = #{name}")
    Playlist getByName(String name);


    /**
     * 插入新歌单
     * @param playlist
     */
    @Save
    void save(Playlist playlist);

    @Select("select * from playlist where create_user = #{currentId}")
    List<Playlist> getPlaylist(long currentId);
}
