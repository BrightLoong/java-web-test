package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.StudentsDAO;
import db.MyHibernatSessionFactory;
import entity.Students;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月24日
 */
public class StudentsDAOImpl implements StudentsDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Students> queryAllStudents() {
        Transaction tx = null;
        List<Students> list = null;
        String hql = "";
        try {
            Session session = MyHibernatSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from Students";
            Query query = session.createQuery(hql);
            list =  query.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tx.commit();
            return list;
        } finally {
            if(tx != null){
                tx = null;
            }
        }
    }

    @Override
    public Students queryStudentsBySid(String sid) {
        Transaction tx = null;
        Students s = new Students();
        try {
            Session session = MyHibernatSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            s = (Students) session.get(Students.class, sid);
            tx.commit();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            tx.commit();
            return s;
        } finally {
            if(tx != null){
                tx = null;
            }
        }
    
    }

    @Override
    public boolean addStudents(Students s) {
        Transaction tx = null;
        try {
            Session session = MyHibernatSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(s);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.commit();
            return false;
        } finally {
            if(tx != null){
                tx = null;
            }
        }
    }

    @Override
    public boolean updateStudents(Students s) {
        Transaction tx = null;
        try {
            Session session = MyHibernatSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(s);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.commit();
            return false;
        } finally {
            if(tx != null){
                tx = null;
            }
        }
    }

    @Override
    public boolean deleteStudents(String sid) {
        Transaction tx = null;
        try {
            Session session = MyHibernatSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Students students = (Students) session.get(Students.class, sid);
            session.delete(students);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.commit();
            return false;
        } finally {
            if(tx != null){
                tx = null;
            }
        }
    }

    @Override
    public String queryMaxSid() {
        Transaction tx = null;
        String hql = "";
        String maxSidString="";
        try {
            Session session = MyHibernatSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "select max(sid) from Students";
            Query query = session.createQuery(hql);
            maxSidString = (String) query.uniqueResult();
            tx.commit();
            return maxSidString;
        } catch (Exception e) {
            e.printStackTrace();
            tx.commit();
            return maxSidString;
        } finally {
            if(tx != null){
                tx = null;
            }
        }
    }

}
