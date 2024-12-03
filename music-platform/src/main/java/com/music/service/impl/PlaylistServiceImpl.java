package com.music.service.impl;

import com.music.constant.Constant;
import com.music.dto.PlaylistSaveDTO;
import com.music.entity.Playlist;
import com.music.exception.MyException;
import com.music.mapper.PlaylistMapper;
import com.music.service.PlaylistService;
import com.music.utils.BeanCopyUtils;
import com.music.utils.MyContext;
import com.music.vo.PlaylistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class PlaylistServiceImpl implements PlaylistService {


    @Autowired
    PlaylistMapper playlistMapper;


    /**
     * 新增歌单
     * @param playlistSaveDTO
     */
    @Override
    public void save(PlaylistSaveDTO playlistSaveDTO) {

        //判断name是否为空
        if(playlistSaveDTO.getName()==null|| !StringUtils.hasText(playlistSaveDTO.getName())){
            throw new MyException(Constant.NAME_IS_EMPTY);
        }
        //判断歌单是否已经存在
        Playlist playlist = playlistMapper.getByName(playlistSaveDTO.getName());

        if(playlist!=null && (int)playlist.getCreateUser() == (long)MyContext.getCurrentId()){
            throw new MyException(Constant.PLAYLIST_EXIST);
        }


        //转换类型
        playlist = BeanCopyUtils.copyBean(playlistSaveDTO, Playlist.class);
        playlist.setCreateTime(new Date());
        playlist.setCreateUser(1);

        //创建歌单
        playlistMapper.save(playlist);
    }

    //查询当前用户的歌单
    @Override
    public List<PlaylistVO> getPlaylist() {
        //获取歌单
        List<Playlist> list = playlistMapper.getPlaylist(MyContext.getCurrentId());

        //进行封装
        List<PlaylistVO> playlistVOS = BeanCopyUtils.copyBeanList(list, PlaylistVO.class);

        return playlistVOS;

    }
}
