package com.chenlong.study.exception;

/**
 * Title: 秒杀关闭异常<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月13日
 */
public class SeckillCloseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
        // TODO 自动生成构造函数存根
    }

    public SeckillCloseException(String message) {
        super(message);
        // TODO 自动生成构造函数存根
    }

    
}
