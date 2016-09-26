package service.impl;

import org.junit.Assert;
import org.junit.Test;

import service.UsersDAO;
import entity.Users;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月22日
 */
public class TestUsersDaoImpl {

    @Test
    public void testUsersLogin(){
        Users u = new Users(1, "chenlong", "123456");
        UsersDAO usersDAO = new UsersDAOImpl();
        Assert.assertEquals(true, usersDAO.usersLogin(u));
    }
}
