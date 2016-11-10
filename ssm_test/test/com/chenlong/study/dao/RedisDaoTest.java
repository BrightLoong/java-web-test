package com.chenlong.study.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenlong.study.dao.cache.RedisDao;
import com.chenlong.study.entity.Seckill;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0
 * @date 2016年10月18日
 */
//整合junit和spring，让junit在启动时候加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring-dao.xml" })
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;
    
    @Autowired
    private SeckillDao seckillDao;
    
    private long id = 1;

    @Test
    public void testSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        } 
    }

}
