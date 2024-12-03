package com.music.service;

import com.music.dto.PlaylistSaveDTO;
import com.music.vo.MusicCrudeVO;
import com.music.vo.PlaylistVO;

import java.util.List;

public interface PlaylistService {
    void save(PlaylistSaveDTO playlistSaveDTO);

    List<PlaylistVO> getPlaylist();

    void delete(Integer id);

    List<MusicCrudeVO> getMusic(Integer id);
}
