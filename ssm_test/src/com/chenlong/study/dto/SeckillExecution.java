package com.chenlong.study.dto;

import com.chenlong.study.entity.SuccessKilled;
import com.chenlong.study.enums.SeckillStateEnum;

/**
 * Title: 秒杀执行后的结果<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月13日
 */
public class SeckillExecution {
    private long seckillId;
    
    private int state;
    
    private String stateInfo;
    
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, 
            SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfoString();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfoString();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
                + ", stateInfo=" + stateInfo + ", successKilled="
                + successKilled + "]";
    }
    
}
