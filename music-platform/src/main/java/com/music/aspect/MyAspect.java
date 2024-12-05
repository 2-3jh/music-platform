package com.music.aspect;


import com.alibaba.fastjson.JSON;
import com.music.annotation.Log;
import com.music.constant.Constant;
import com.music.utils.MyContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class MyAspect {

    /**
     * 定义切点
     * save
     */
    @Pointcut("execution(* com.music.mapper.*.*(..)) && @annotation(com.music.annotation.Save)")
    public void insertPointcut() {
    }

    /**
     * Log切点
     */
    @Pointcut("@annotation(com.music.annotation.Log)")
    public void logPointcut() {}

    /**
     * 定义通知方法
     * save
     */
    @Before("insertPointcut()")
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

    /**
     * 定义Log的通知方法
     */
    @Around("logPointcut()")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        //执行结果
        Object ret;

        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            log.info("========END========" + System.lineSeparator());
        }

        return ret;
    }

    private void handleAfter(Object ret) {

        // 打印出参
//       log.info("Response : {}",JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取执行方法的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Log myLog = signature.getMethod().getAnnotation(Log.class);

        //打印
        log.info("========Start========");
        // 打印请求 URL
        log.info("URL : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName : {}",myLog.doingName());
        // 打印 Http method
        log.info("HTTP Method : {}",request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method : {}.{}", signature.getDeclaringTypeName(), signature.getName());
        // 打印请求的 IP
        log.info("IP : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args : {}", JSON.toJSONString(joinPoint.getArgs()));
    }
}
