package com.chenlong.study.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenlong.study.dto.Exposer;
import com.chenlong.study.dto.SeckillExecution;
import com.chenlong.study.dto.SeckillResult;
import com.chenlong.study.entity.Seckill;
import com.chenlong.study.enums.SeckillStateEnum;
import com.chenlong.study.exception.RepeatException;
import com.chenlong.study.exception.SeckillCloseException;
import com.chenlong.study.service.SeckillService;
import com.chenlong.study.utils.PropertyUtil;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年10月14日
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
    
    @RequestMapping (value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> seckills = seckillService.getSeckillList();
        model.addAttribute("list", seckills);
        String username = (String) PropertyUtil.getProperty("name");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++" +username);
        return "list";
    }
    
    @RequestMapping (value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckilId, Model model){
        if (seckilId == null) {
            return "redirect:/seckill/list";//重定向
        }
        Seckill seckill = seckillService.getSeckillById(seckilId);
        if (seckill == null) {
            return "forward:/seckill/list";//转发
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }
    
    @RequestMapping (value = "/{seckillId}/exposer", method = RequestMethod.POST
            ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }
    
    @RequestMapping (value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST
            ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId,
        @PathVariable("md5")String md5, 
        @CookieValue(value = "killPhone", required = false)Long userPhone){
        //当验证较多的时候使用spring valid
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } 
        catch (SeckillCloseException e1) {
            logger.error(e1.getMessage());
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatException e2) {
            logger.error(e2.getMessage());
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true, execution);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, execution);
        }
    }
    
    @RequestMapping (value = "/time/now", method = RequestMethod.GET
            ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
