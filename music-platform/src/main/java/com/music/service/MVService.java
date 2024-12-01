package com.music.service;

import com.music.dto.MVPageQueryDTO;
import com.music.result.PageResult;
import com.music.vo.MVItemVO;

public interface MVService {
    PageResult pageQuery(MVPageQueryDTO mvPageQueryDTO);

    MVItemVO getById(Integer id);
}
