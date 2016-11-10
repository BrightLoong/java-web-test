package com.chenlong.study.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenlong.study.entity.SuccessKilled;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月13日
 */
//整合junit和spring，让junit在启动时候加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SuccessKiledlDaoTest {
    //注入dao实现类
    @Resource
    private SuccessKilledDao successKilledDao;
    
    @Test
    public void testInsertSuccessKilled(){
        int count = successKilledDao.insertSuccessKilled(1, 6666);
        System.out.println(count);
    }
    
    @Test
    public void testQueryByIdWithSeckill(){
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1, 6666);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}
