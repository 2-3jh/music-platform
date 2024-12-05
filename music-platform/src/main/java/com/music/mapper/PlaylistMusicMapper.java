package com.music.mapper;

import com.music.dto.PlaylistMusicDTO;
import com.music.entity.PlaylistSong;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PlaylistMusicMapper {

    /**
     * 根据歌单id获取音乐id
     * @param id
     * @return
     */
    @Select("select song_id from playlist_song where playlist_id = #{id}")
    public List<Integer> getMusicId(Integer id);


    @Delete("delete from playlist_song where playlist_id = #{id}")
    void deleteMusicById(Integer id);


    @Select("select * from playlist_song where playlist_id = #{playlistId} and song_id = #{musicId}")
    PlaylistSong getByPlaylistMusicId(PlaylistMusicDTO playlistMusicDTO);

    @Insert("insert into playlist_song value (#{playlistId},#{musicId})")
    void addMusic(PlaylistMusicDTO playlistMusicDTO);


    @Delete("delete from playlist_song where playlist_id = #{playlistId} and song_id = #{musicId}")
    void deleteMusicFromPlaylist(PlaylistMusicDTO playlistMusicDTO);
}
