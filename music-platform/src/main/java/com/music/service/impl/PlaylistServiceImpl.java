package com.music.service.impl;

import com.music.constant.Constant;
import com.music.dto.AddMusicDTO;
import com.music.dto.PlaylistSaveDTO;
import com.music.entity.Music;
import com.music.entity.Playlist;
import com.music.entity.PlaylistSong;
import com.music.exception.MyException;
import com.music.mapper.MusicMapper;
import com.music.mapper.PlaylistMapper;
import com.music.mapper.PlaylistMusicMapper;
import com.music.service.PlaylistService;
import com.music.utils.BeanCopyUtils;
import com.music.utils.MyContext;
import com.music.vo.MusicCrudeVO;
import com.music.vo.PlaylistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;


@Service
public class PlaylistServiceImpl implements PlaylistService {


    @Autowired
    PlaylistMapper playlistMapper;

    @Autowired
    PlaylistMusicMapper playlistMusicMapper;

    @Autowired
    MusicMapper musicMapper;

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


    /**
     * 删除歌单
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //判断歌单是否为当前用户的和存在
        Playlist  playlist= playlistMapper.getById(id);

        //不是抛出错误
        if(playlist ==null || playlist.getCreateUser()!=MyContext.getCurrentId()){
            throw new MyException(Constant.PLAYLIST_NOT_EXIST);
        }

        //是的话删除
        playlistMapper.deleteById(id);

        //删除歌单关联的音乐记录
        playlistMusicMapper.deleteMusicById(id);
    }


    /**
     * 获取歌单的Music
     * @param id
     * @return
     */
    @Override
    public List<MusicCrudeVO> getMusic(Integer id) {
        //判断歌单是否为当前用户的和存在
        Playlist  playlist= playlistMapper.getById(id);

        //不是抛出错误
        if(playlist ==null || playlist.getCreateUser()!=MyContext.getCurrentId()){
            throw new MyException(Constant.PLAYLIST_NOT_EXIST);
        }

        //查寻音乐的id
        List<Integer> list = playlistMusicMapper.getMusicId(id);

        //判断是否为空
        if(list==null || list.size()==0){
            throw new MyException(Constant.HAVENT_MUSIC);
        }

        //不为空获取音乐信息
        List<Music> musicList = musicMapper.getByIdBatch(list);

        //封装
        List<MusicCrudeVO> musicVOS = BeanCopyUtils.copyBeanList(musicList, MusicCrudeVO.class);

        return musicVOS;

    }

    @Override
    public void addMusic(AddMusicDTO addMusicDTO) {
        //判断歌单是否为当前用户的和存在
        Playlist  playlist= playlistMapper.getById(addMusicDTO.getPlaylistId());

        //不是抛出错误
        if(playlist ==null || playlist.getCreateUser()!=MyContext.getCurrentId()){
            throw new MyException(Constant.PLAYLIST_NOT_EXIST);
        }

        //判断是否有该音乐
        if(musicMapper.getById(addMusicDTO.getMusicId())==null){
            throw new MyException(Constant.MUSIC_NOT_FOUND);
        }

        //判断歌单中是否存在此音乐
        PlaylistSong  playlistSong = playlistMusicMapper.getByPlaylistMusicId(addMusicDTO);

        if(playlistSong != null){
            throw new MyException(Constant.MUSIC_EXIST_IN_PLAYLIST);
        }

        //不存在添加
        playlistMusicMapper.addMusic(addMusicDTO);

    }
}
