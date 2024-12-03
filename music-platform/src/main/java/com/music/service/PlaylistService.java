package com.music.service;

import com.music.dto.PlaylistSaveDTO;
import com.music.vo.PlaylistVO;

import java.util.List;

public interface PlaylistService {
    void save(PlaylistSaveDTO playlistSaveDTO);

    List<PlaylistVO> getPlaylist();
}
