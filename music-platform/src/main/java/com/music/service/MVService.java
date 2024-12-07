package com.music.service;

import com.music.dto.MVPageQueryDTO;
import com.music.result.PageResult;
import com.music.vo.MVCrudeVO;
import com.music.vo.MVItemVO;

import java.util.List;

public interface MVService {
    PageResult pageQuery(MVPageQueryDTO mvPageQueryDTO);

    MVItemVO getById(Integer id);

    List<MVCrudeVO> getByName(String name);
}
