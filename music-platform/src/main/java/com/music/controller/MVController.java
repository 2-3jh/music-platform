package com.music.controller;

import com.music.annotation.Log;
import com.music.dto.MVPageQueryDTO;
import com.music.result.PageResult;
import com.music.result.Result;
import com.music.service.MVService;
import com.music.vo.MVItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mv")
@Slf4j
public class MVController {

    @Autowired
    private MVService mvService;


    /**
     * mv分类查询
     * @param mvPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Log(doingName = "MV的分页查询")
    public Result<PageResult> get(MVPageQueryDTO mvPageQueryDTO) {
        PageResult pageResult = mvService.pageQuery(mvPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 获取具体MV
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Log(doingName = "根据id获取MV")
    public Result getById(@PathVariable("id") Integer id) {
        MVItemVO mvItemVO=mvService.getById(id);
        return Result.success(mvItemVO);
    }
}
