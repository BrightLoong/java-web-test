package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月21日
 */
public class TestStudentsExport {
    @Test
    public void testSchemaExport(){
        //创建配置对象
        Configuration config = new Configuration().configure();
        //创建服务注册对象
        //ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        //创建sessionfactory
        //SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
        //创建session对象
       // Session session = sessionFactory.getCurrentSession();
        //创建SchemaExport对象
        SchemaExport exprot = new SchemaExport(config);
        
        exprot.create(true, true);
    }
    
    @Test
    public void testSaveStrdents(){
      //创建配置对象
        Configuration config = new Configuration().configure();
        //创建服务注册对象
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        //创建sessionfactory
        SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
        //创建session对象
       Session session = sessionFactory.getCurrentSession();
       Transaction tx = session.beginTransaction();
       
       Students s1 = new Students("S0000001", "张三", "男", new Date(), "武当");
       Students s2 = new Students("S0000002", "小白", "女", new Date(), "峨眉");
       Students s3 = new Students("S0000003", "小李", "男", new Date(), "华山");
       
       session.save(s1);
       session.save(s2);
       session.save(s3);
       tx.commit();
       sessionFactory.close();
    }
}

