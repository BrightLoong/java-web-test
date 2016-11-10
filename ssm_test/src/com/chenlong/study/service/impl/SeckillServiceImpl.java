package com.chenlong.study.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.chenlong.study.dao.SeckillDao;
import com.chenlong.study.dao.SuccessKilledDao;
import com.chenlong.study.dao.cache.RedisDao;
import com.chenlong.study.dto.Exposer;
import com.chenlong.study.dto.SeckillExecution;
import com.chenlong.study.entity.Seckill;
import com.chenlong.study.entity.SuccessKilled;
import com.chenlong.study.enums.SeckillStateEnum;
import com.chenlong.study.exception.RepeatException;
import com.chenlong.study.exception.SeckillCloseException;
import com.chenlong.study.exception.SeckillException;
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
@Service
public class SeckillServiceImpl implements SeckillService {
    
    @Autowired
    private SeckillDao seckillDao;
    
    @Autowired 
    RedisDao redisDao;
    
    @Autowired
    private SuccessKilledDao successKilledDao;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public final String slat = "asfjka234218(*?<POMNHt™！@#$*UH";//混淆md5,盐值
    
    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getSeckillById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null){
             seckill = seckillDao.queryById(seckillId);
             if (seckill == null) {
                 return new Exposer(false, seckillId);
             } else {
                 redisDao.putSeckill(seckill);
             }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    @Transactional
    /**
     * 事务方法的执行时间尽可能短；不要穿插其他网络操作RPC/HTTP请求
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatException
     * @throws SeckillCloseException
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone,
            String md5) throws SeckillException, RepeatException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑：减库存 + 记录购买行为
        try {
            //记录购买行为
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            if (insertCount <= 0) {
                throw new RepeatException("seckill repeat");
            } else {
                int updateCount = seckillDao.reduceNumber(seckillId, new Date());
                if (updateCount <= 0) {
                    throw new SeckillException("seckill is closed"); 
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        
        }  catch (SeckillCloseException e1) {
            throw e1;
         }
        catch (RepeatException e2) {
            throw e2;
         }
        catch (Exception e) {
           logger.error(e.getMessage(), e);
           throw new SeckillException("seckill inner error" + e.getMessage());
        }
    }

    /**
     * 获取md5值
     * @param seckillId
     * @return
     */
    private String getMd5(long seckillId){
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
