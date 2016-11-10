package com.chenlong.study.exception;

/**
 * Title: 重复秒杀异常<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月13日
 */
public class RepeatException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatException(String message) {
        super(message);
    }
    
}
