package com.chenlong.study.service;

import java.util.List;

import com.chenlong.study.dto.Exposer;
import com.chenlong.study.dto.SeckillExecution;
import com.chenlong.study.entity.Seckill;
import com.chenlong.study.exception.RepeatException;
import com.chenlong.study.exception.SeckillCloseException;
import com.chenlong.study.exception.SeckillException;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0
 * @date 2016年10月13日
 */
public interface SeckillService {
    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(long seckillId);

    /**
     * 输出秒杀地址
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatException, SeckillCloseException;
}
