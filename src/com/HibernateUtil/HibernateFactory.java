package com.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
	
	private static SessionFactory sessionFactory = null;
	static{
		
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public static Session getSession(){
		Session session = null;
		
		if(session == null){
			session = sessionFactory.openSession();
		}
		
		return session;
	}
}
