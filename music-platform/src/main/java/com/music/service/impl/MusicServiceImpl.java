package com.music.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.music.constant.Constant;
import com.music.dto.MusicPageQueryDTO;
import com.music.entity.Music;
import com.music.exception.MyException;
import com.music.mapper.MusicMapper;
import com.music.result.PageResult;
import com.music.service.MusicService;
import com.music.utils.BeanCopyUtils;
import com.music.vo.MusicItemVO;
import com.music.vo.MusicCrudeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    MusicMapper musicMapper;

    //进行音乐的分页查询
    @Override
    public PageResult pageQuery(MusicPageQueryDTO musicPageQueryDTO) {
        //开启分页查询
        PageHelper.startPage(musicPageQueryDTO.getPage(),musicPageQueryDTO.getPageSize());
        Page<Music> page = musicMapper.pageQuery(musicPageQueryDTO);
        List<MusicCrudeVO> list=BeanCopyUtils.copyBeanList(page.getResult(), MusicCrudeVO.class);
        return new PageResult(page.getTotal(),list);
    }

    @Override
    public MusicItemVO getById(Integer id) {
        //查询
        Music music = musicMapper.getById(id);

        //封装
        if(music==null){
            throw new MyException(Constant.MUSIC_NOT_FOUND);
        }
        MusicItemVO musicItemVO = BeanCopyUtils.copyBean(music,MusicItemVO.class);
        return musicItemVO;
    }

    @Override
    public List<String> getCategory() {
        //查询分类
        List<String> list = musicMapper.getCategory();

        return list;
    }
}
