package com.music.aspect;


import com.music.annotation.Save;
import com.music.constant.Constant;
import com.music.utils.MyContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
public class InsertAspect {

    /**
     * 定义切点
     */
    @Pointcut("execution(* com.music.mapper.*.*(..)) && @annotation(com.music.annotation.Save)")
    public void insertPointCut() {
    }


    /**
     * 定义通知方法
     */
    @Before("insertPointCut()")
    public void before(JoinPoint joinPoint) {


        //获取插入的参数
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];

        //获取创建人和当前的时间
        Date now = new Date();
        Integer currentId = MyContext.getCurrentId();

        //赋值
        try {
            Method setCreateTime = entity.getClass().getDeclaredMethod(Constant.SET_CREATE_TIME, Date.class);
            Method setCreateUser = entity.getClass().getDeclaredMethod(Constant.SET_CREATE_USER, Integer.class);

            //进行赋值
            setCreateTime.invoke(entity, now);
            setCreateUser.invoke(entity, currentId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
