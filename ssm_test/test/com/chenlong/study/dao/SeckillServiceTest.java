package com.chenlong.study.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenlong.study.dto.Exposer;
import com.chenlong.study.dto.SeckillExecution;
import com.chenlong.study.entity.Seckill;
import com.chenlong.study.exception.RepeatException;
import com.chenlong.study.exception.SeckillCloseException;
import com.chenlong.study.service.SeckillService;

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
@ContextConfiguration({ "classpath:spring-dao.xml",
        "classpath:spring-service.xml" })
public class SeckillServiceTest {
    @Autowired
    private SeckillService seckillService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testGetSeckillList() {
        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info("list={}", seckills);
    }

    @Test
    public void testGetSeckillById() {
        Seckill seckill = seckillService.getSeckillById(1);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testSeckillLogic() {
        Exposer exposer = seckillService.exportSeckillUrl(1);
        logger.info("exposer={}", exposer);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(
                    1, 1324568456L, md5);
                logger.info("result={}", seckillExecution);
            } catch (SeckillCloseException e1) {
                logger.error(e1.getMessage());
            } catch (RepeatException e2) {
                logger.error(e2.getMessage());
            }
        } else {
            logger.warn("exposer={}", exposer);
        }
    }
}