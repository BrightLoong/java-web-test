package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Title: hibernate Session工厂<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月22日
 */
public class MyHibernatSessionFactory {
    private static SessionFactory sessionFactory;
    
    //private保证单例模式
    private MyHibernatSessionFactory() {
        
    }
    
    //获得会话工厂
    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null) {
            Configuration config = new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } else {
           return sessionFactory; 
        }
    }
}
