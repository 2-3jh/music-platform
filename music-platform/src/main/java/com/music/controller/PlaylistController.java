package com.music.controller;


import com.music.annotation.Log;
import com.music.dto.*;
import com.music.result.Result;
import com.music.service.PlaylistService;
import com.music.utils.MyContext;
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
    @Log(doingName = "save playlist")
    public Result save(@RequestBody PlaylistSaveDTO playlistSaveDTO){
        MyContext.setCurrentId(playlistSaveDTO.getUserId());
        playlistService.save(playlistSaveDTO);
        return Result.success();
    }


    /**
     * 查询已有的歌单
     * @return
     */
    @GetMapping("/list")
    @Log(doingName = "查询用户的歌单")
    public Result getPlaylist(UserIdDTO userIdDTO){
        MyContext.setCurrentId(userIdDTO.getUserId());
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
    @Log(doingName = "delete playlist")
    public Result deletePlaylist(@RequestBody PlaylistDelDTO playlistDelDTO){
        MyContext.setCurrentId(playlistDelDTO.getUserId());
        playlistService.delete(playlistDelDTO.getId());
        return Result.success();
    }


    /**
     * 获取具体歌单的所有音乐
     * @param listIdDTO
     * @return
     */
    @GetMapping("/music")
    @Log(doingName = "get music")
    public Result getMusic(ListIdDTO listIdDTO){
        MyContext.setCurrentId(listIdDTO.getUserId());
        //获取音乐信息
        List<MusicCrudeVO> list = playlistService.getMusic(listIdDTO.getListId());
        return Result.success(list);
    }


    /**
     * 向歌单中添加音乐
     * @param playlistMusicDTO
     * @return
     */
    @PutMapping("/music")
    @Log(doingName = "add music to playlist")
    public Result addMusic(@RequestBody PlaylistMusicDTO playlistMusicDTO){

        MyContext.setCurrentId(playlistMusicDTO.getUserId());
        playlistService.addMusic(playlistMusicDTO);
        return Result.success();
    }

    /**
     * 删除歌单中的音乐
     * @param playlistMusicDTO
     * @return
     */
    @DeleteMapping("/music")
    @Log(doingName = "delete music from playlist")
    public Result deleteMusic(@RequestBody PlaylistMusicDTO playlistMusicDTO){
        MyContext.setCurrentId(playlistMusicDTO.getUserId());
        playlistService.deleteMusic(playlistMusicDTO);
        return Result.success();
    }
}
