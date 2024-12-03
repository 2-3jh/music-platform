package com.music.mapper;

import com.github.pagehelper.Page;
import com.music.dto.MusicPageQueryDTO;
import com.music.entity.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MusicMapper {

    Page<Music> pageQuery(MusicPageQueryDTO musicPageQueryDTO);

    @Select("select * from music where id=#{id}")
    Music getById(Integer id);

    @Select("select distinct category from music")
    List<String> getCategory();

    List<Music> getByIdBatch(List<Integer> list);
}
