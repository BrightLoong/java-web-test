package com.chenlong.study.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenlong.study.utils.PropertyUtil;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author 陈龙
 * @version 1.0 
 * @date 2016年11月9日
 */

//整合junit和spring，让junit在启动时候加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring-dao.xml,classpath:spring-web.xml,classpath:spring-service.xml," })
public class PropertyUtilsTest {
  @Test
  public void testGetProperty() {
      String username = (String) PropertyUtil.getProperty("jdbc.username");
      System.out.println(username);
  }
}
