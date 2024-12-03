package com.music.controller;


import com.music.dto.PlaylistSaveDTO;
import com.music.result.Result;
import com.music.service.PlaylistService;
import com.music.vo.PlaylistVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@Slf4j
public class PlaylistController {


    @Autowired
    PlaylistService playlistService;

    /**
     * 新建歌单
     * @param playlistSaveDTO
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody PlaylistSaveDTO playlistSaveDTO){
        log.info("save playlist:{}", playlistSaveDTO);
        playlistService.save(playlistSaveDTO);
        return Result.success();
    }


    /**
     * 查询已有的歌单
     * @return
     */
    @GetMapping()
    public Result getPlaylist(){
        log.info("查询用户的歌单");

        List<PlaylistVO> list = playlistService.getPlaylist();

        return Result.success(list);
    }
}
