package com.music.service;

import com.music.dto.MusicPageQueryDTO;
import com.music.result.PageResult;
import com.music.vo.MusicCrudeVO;
import com.music.vo.MusicItemVO;

import java.util.List;

public interface MusicService {
    PageResult pageQuery(MusicPageQueryDTO musicPageQueryDTO);

    MusicItemVO getById(Integer id);

    List<String> getCategory();

    List<MusicCrudeVO> getByName(String name);
}
