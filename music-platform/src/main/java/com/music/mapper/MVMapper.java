package com.music.mapper;


import com.github.pagehelper.Page;
import com.music.dto.MVPageQueryDTO;
import com.music.entity.MV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MVMapper {

    //MV的分页查询
    Page<MV> pageQuery(MVPageQueryDTO mvPageQueryDTO);

    @Select("select * from mv where id=#{id}")
    MV getById(Integer id);

    List<MV> getByName(String name);
}
