package com.chenlong.study.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenlong.study.entity.Seckill;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0
 * @date 2016年10月17日
 */
public class RedisDao {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private JedisPool jedisPool;
    
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //序列化 ，使用protostuff自定义序列化
                byte[] bytes = jedis.get(key.getBytes());
                //缓存中获取到
                if (bytes != null) {
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //seckill被反序列
                    return seckill;
                }
            } finally  {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        return null;
    }
    
    public String putSeckill(Seckill seckill) {

        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60*60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                    return result;
            } finally  {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 
        return null;
    }
}
