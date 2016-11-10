package com.chenlong.study.enums;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月13日
 */
public enum SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-3,"内部错误"),
    DATA_REWRITE(-4,"数据篡改");
    
    private int state;
    
    private String stateInfoString;

    private SeckillStateEnum(int state, String stateInfoString) {
        this.state = state;
        this.stateInfoString = stateInfoString;
    }

    public int getState() {
        return state;
    }

    public String getStateInfoString() {
        return stateInfoString;
    }
    
    public static SeckillStateEnum stateOf(int index){
        for (SeckillStateEnum state : values()) {
           if (state.getState() == index) {
               return state;
           }
        }
        return null;
    }
}
