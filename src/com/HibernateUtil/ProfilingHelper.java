package com.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Achievements;
import com.model.Password;
import com.model.ProfessorProfile;
import com.model.Projects;
import com.model.Researches;
import com.model.Resume;
import com.model.Users;

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
	
	
	
	
	public List<Researches>viewResearches() //overload id from loginHelper
	{
		session=sessionFactory.openSession();
		session.beginTransaction();
		List<Researches> list = null;
		ProfessorProfile professorProfile=(ProfessorProfile) session.get(ProfessorProfile.class, 1);
		list = professorProfile.getResearches();
		list.forEach(i -> System.out.println(i.getResearchName()));
		session.getTransaction().commit();
		session.close();
		return list;
		
	}
	
	public List<Projects>viewProjects() //overload id from loginHelper
	{
		session=sessionFactory.openSession();
		session.beginTransaction();
		
		List<Projects>list=null;
		ProfessorProfile professorProfile=(ProfessorProfile) session.get(ProfessorProfile.class, 1);
		
		list=professorProfile.getProjects();
		list.forEach(i -> System.out.println(i.getProjectName()));
		
		
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public List<Resume>viewResume()   //overload id from loginHelper
	{
		session=sessionFactory.openSession();
		session.beginTransaction();
		List<Resume>list=null;
		ProfessorProfile professorProfile=(ProfessorProfile) session.get(ProfessorProfile.class, 1);
		list=professorProfile.getResume();
		list.forEach(i -> System.out.println(i.getResumeUrl()));
		session.getTransaction().commit();
		session.close();
		return list;
		
	}
	public List<Achievements>viewAchievements()  //overload id from loginHelper
	{
		session=sessionFactory.openSession();
		session.beginTransaction();
		List<Achievements>list=null;
		ProfessorProfile professorProfile=(ProfessorProfile) session.get(ProfessorProfile.class, 1);
		list=professorProfile.getAchievements();
		list.forEach(i -> System.out.println(i.getAttachmentUrl()));
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
}
