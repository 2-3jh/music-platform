package com.music.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.music.constant.Constant;
import com.music.exception.MyException;
import com.music.mapper.MVMapper;
import com.music.dto.MVPageQueryDTO;
import com.music.entity.MV;
import com.music.result.PageResult;
import com.music.service.MVService;
import com.music.utils.BeanCopyUtils;
import com.music.vo.MVItemVO;
import com.music.vo.MVCrudeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MVServiceImpl implements MVService {

    @Autowired
    private MVMapper mvMapper;

    //实现对MV的分页查询
    @Override
    public PageResult pageQuery(MVPageQueryDTO mvPageQueryDTO) {
        //开启分页查询
        PageHelper.startPage(mvPageQueryDTO.getPage(),mvPageQueryDTO.getPageSize());
        Page<MV> page=mvMapper.pageQuery(mvPageQueryDTO);
        //查询结果的类型转换
        List<MVCrudeVO> list=BeanCopyUtils.copyBeanList(page.getResult(), MVCrudeVO.class);
        return new PageResult(page.getTotal(),list);
    }


    //查询具体的MV信息
    @Override
    public MVItemVO getById(Integer id) {

        //查询
        MV mv = mvMapper.getById(id);

        //封装
        if(mv==null){
            throw new MyException(Constant.MV_NOT_FOUND);
        }
        MVItemVO mvItemVO = BeanCopyUtils.copyBean(mv, MVItemVO.class);
        return mvItemVO;
    }

    //根据name模糊查询

    @Override
    public List<MVCrudeVO> getByName(String name) {
        List<MV> list = mvMapper.getByName(name);
        List<MVCrudeVO> mvCrudeVOS = BeanCopyUtils.copyBeanList(list, MVCrudeVO.class);
        return mvCrudeVOS;
    }
}
