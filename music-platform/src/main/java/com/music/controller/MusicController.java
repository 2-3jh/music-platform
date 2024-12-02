package com.music.controller;


import com.music.dto.MusicPageQueryDTO;
import com.music.result.PageResult;
import com.music.result.Result;
import com.music.service.MusicService;
import com.music.vo.MVItemVO;
import com.music.vo.MusicItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/music")
@Slf4j
public class MusicController {

    @Autowired
    MusicService musicService;

    //音乐推送的分页查询
    @GetMapping("/page")
    public Result musicPageQuery(MusicPageQueryDTO musicPageQueryDTO) {
        log.info("音乐推送的分页查询：{}", musicPageQueryDTO);
        PageResult pageResult =musicService.pageQuery(musicPageQueryDTO);
        return Result.success(pageResult);
    }

    //查询具体的Music
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("根据id获取Music:{}",id);
        MusicItemVO musicItemVO=musicService.getById(id);
        return Result.success(musicItemVO);
    }

    //查询分类
    @GetMapping("/category")
    public Result getCategory() {
        log.info("获取音乐的分类");
        List<String> list = musicService.getCategory();
        return Result.success(list);
    }
}
