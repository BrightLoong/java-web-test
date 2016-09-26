package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernatSessionFactory;
import entity.Users;
import service.UsersDAO;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月22日
 */
public class UsersDAOImpl implements UsersDAO {
    
    /**
     * 用户登录
     * @param u
     * @return
     */
    @Override
    public boolean usersLogin(Users u) {
        //事务对象
        Transaction tx = null;
        String hql;
       try {
        Session  session = MyHibernatSessionFactory.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();//开启事务
        hql = "from Users where username = ? and password = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0,u.getUsername());
        query.setParameter(1, u.getPassword());
        @SuppressWarnings("unchecked")
        List<Users> userList = query.list();
        tx.commit(); //提交事务
        if(userList.size() > 0){
            return true;
        } else {
            return false;
        }
                } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        if(tx != null) {
            //tx.commit();
            tx = null;
        }
    }
    }

}
