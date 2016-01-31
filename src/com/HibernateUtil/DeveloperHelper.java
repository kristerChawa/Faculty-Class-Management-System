package com.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Password;
import com.model.ProfessorProfile;
import com.model.Users;

public class DeveloperHelper {

	static SessionFactory sessionFactory = null;
	Session session = null;
	static
	{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public void addUser(Users users)
	{
		
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(users);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	
	}
	
	public void addPassword(Password password)
	{
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(password);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public void addProfessorProfile(ProfessorProfile professorProfile)
	{
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(professorProfile);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
}
