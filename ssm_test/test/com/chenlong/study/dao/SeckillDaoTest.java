package com.chenlong.study.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenlong.study.dao.SeckillDao;
import com.chenlong.study.entity.Seckill;

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
public class SeckillDaoTest {
    //注入dao实现类
    @Resource
    private SeckillDao seckillDao;
    

    
    
    @Test
    public void testQueryById() throws Exception{
        long id = 1;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }
    
    @Test
    public void testQueryAll() throws Exception{
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for(Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }
    @Test
    public void testReduceNumber() throws Exception{
        int count = seckillDao.reduceNumber(1, new Date());
        System.out.println(count);
    }
}
