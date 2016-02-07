package com.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.AccountType;
import com.model.Password;
import com.model.ProfessorProfile;
import com.model.Projects;
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
	
	public void addAccountType(AccountType accountType)
	{
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(accountType);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public List<Users>viewAllProfessors() 
	{
	
		session=sessionFactory.openSession();
		session.beginTransaction();
		
		Query query=session.createQuery("From Users");
		
		List<Users>list=query.list();
	
		session.getTransaction().commit();
		session.close();
		return list;
		
	}
	
	
}
