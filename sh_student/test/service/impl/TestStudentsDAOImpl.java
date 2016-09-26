package service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import service.StudentsDAO;
import entity.Students;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月25日
 */
public class TestStudentsDAOImpl {
    @Test
    public void testQueryAllStudents(){
        StudentsDAO sDao = new StudentsDAOImpl();
        List<Students> list = sDao.queryAllStudents();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    
    @Test
    public void testAdd(){
        StudentsDAO sDao = new StudentsDAOImpl();
        Students students = new Students("S0000004", "小二", "男", new Date(), "同福客栈");
        Assert.assertEquals(true, sDao.addStudents(students));
    }
    
    @Test
    public void testQueryMaxSid(){
        StudentsDAO sDao = new StudentsDAOImpl();
        Assert.assertEquals("S0000004", sDao.queryMaxSid());
    }
    
    @Test
    public void testQueryStudentsBySid(){
        StudentsDAO sDao = new StudentsDAOImpl();
        System.out.println(sDao.queryStudentsBySid("S0000004"));
    }
}
