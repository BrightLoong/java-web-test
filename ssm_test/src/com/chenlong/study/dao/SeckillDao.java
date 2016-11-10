package com.chenlong.study.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chenlong.study.entity.Seckill;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月12日
 */
public interface SeckillDao {
    
    /**
     * 减库存操作
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
    
    /**
     * 根据id查询秒杀商品
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);
    
    /**
     * 分页查询秒杀商品列表
     * @param offect
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
