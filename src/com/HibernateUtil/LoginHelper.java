package com.HibernateUtil;

import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.buf.UDecoder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Users;

public class LoginHelper {

	static SessionFactory sessionFactory = null;
	Session session = null;
	static
	{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public int getID() //overload String userName
	{
		int i = 0;
		try
		{
			
			session=sessionFactory.openSession();
			session.beginTransaction();
			
			Query query=session.createQuery("from Users where username=:uName");
			query.setParameter("uName", "Panda");
			
			Users users=(Users) query.uniqueResult();
			
			i=users.getUserID();
			session.getTransaction().commit();
			session.close();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return i;
	
	}
}
