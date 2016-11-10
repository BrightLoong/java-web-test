package com.chenlong.study.service;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年11月10日
 */
@Aspect
@Component
public class MethodCost {
    public static final String POINT = "execution (* com.chenlong.study.service.impl..*.*(..))"; 
    @Around(POINT)
    public Object cost(ProceedingJoinPoint jionPoint) throws Throwable {
        Long now = new Date().getTime();
        Object object = jionPoint.proceed();
        Long after = new Date().getTime();
        System.out.println((after-now) + "ms");
        return object;
    }
}
