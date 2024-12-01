package com.music.controller;

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


    @GetMapping("/page")
    public Result<PageResult> get(MVPageQueryDTO mvPageQueryDTO) {
        log.info("MV的分页查询:{}", mvPageQueryDTO);
        PageResult pageResult = mvService.pageQuery(mvPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("根据id获取MV:{}",id);
        MVItemVO mvItemVO=mvService.getById(id);
        return Result.success(mvItemVO);
    }
}
