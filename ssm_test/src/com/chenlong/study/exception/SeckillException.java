package com.chenlong.study.exception;

/**
 * Title: 秒杀业务相关异常<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月13日
 */
public class SeckillException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }

   
    
}
