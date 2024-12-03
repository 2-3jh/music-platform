package com.music.controller;


import com.music.dto.PlaylistDelDTO;
import com.music.dto.PlaylistSaveDTO;
import com.music.result.Result;
import com.music.service.PlaylistService;
import com.music.vo.MusicCrudeVO;
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

    /**
     * 删除歌单
     *
     * @param playlistDelDTO
     * @return
     */
    @DeleteMapping()
    public Result deletePlaylist(@RequestBody PlaylistDelDTO playlistDelDTO){
        log.info("delete playlist:{}", playlistDelDTO);
        playlistService.delete(playlistDelDTO.getId());
        return Result.success();
    }


    /**
     * 获取具体歌单的所有音乐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getMusic(@PathVariable Integer id){
        log.info("get music:{}", id);
        //获取音乐信息
        List<MusicCrudeVO> list = playlistService.getMusic(id);
        return Result.success(list);
    }
}
