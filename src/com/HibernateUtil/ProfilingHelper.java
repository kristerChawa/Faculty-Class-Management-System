package com.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Achievements;
import com.model.Projects;
import com.model.Researches;
import com.model.Resume;

public class ProfilingHelper {

	static SessionFactory sessionFactory = null;
	Session session = null;
	static
	{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public void addResearches(Researches researches)
	{
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(researches);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void addProjects(Projects projects)
	{
	
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(projects);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public void addAchievements(Achievements achievements)
	{
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(achievements);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void addResume(Resume resume)
	{
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(resume);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
