package com.music.controller;


import com.music.annotation.Log;
import com.music.dto.MusicPageQueryDTO;
import com.music.result.PageResult;
import com.music.result.Result;
import com.music.service.MusicService;
import com.music.vo.MVItemVO;
import com.music.vo.MusicCrudeVO;
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
    @Log(doingName = "音乐推送的分页查询")
    public Result musicPageQuery(MusicPageQueryDTO musicPageQueryDTO) {
        PageResult pageResult =musicService.pageQuery(musicPageQueryDTO);
        return Result.success(pageResult);
    }

    //查询具体的Music
    @GetMapping("/{id}")
    @Log(doingName = "根据id获取Music")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("根据id获取Music:{}",id);
        MusicItemVO musicItemVO=musicService.getById(id);
        return Result.success(musicItemVO);
    }

    //查询分类
    @GetMapping("/category")
    @Log(doingName = "获取音乐和MV的分类")
    public Result getCategory() {
        List<String> list = musicService.getCategory();
        return Result.success(list);
    }

    /**
     * 名字模糊搜索
     */
    @GetMapping("/search")
    @Log(doingName = "根据名字搜索music")
    public Result getByName(String name) {
        List<MusicCrudeVO> list = musicService.getByName(name);
        return Result.success(list);
    }
}
