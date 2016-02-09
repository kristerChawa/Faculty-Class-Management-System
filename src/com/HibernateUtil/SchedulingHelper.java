package com.HibernateUtil;

import java.util.HashSet;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Schedule;
import com.model.Subjects;
import com.model.Users;

public class SchedulingHelper {

	static SessionFactory sessionFactory = null;
	Session session = null;
	static
	{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	
	public Subjects addSubjects(Subjects subjects){
		
		Subjects subjectSetID=new Subjects();
		try
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			
			String courseCode=subjects.getCourseCode();
			Query query=session.createQuery("from Subjects where CourseCode=:cc");
			query.setParameter("cc", courseCode);
			
			List<Subjects>checkSubjects=query.list();
			
			 if (checkSubjects != null && !checkSubjects.isEmpty())
			 {	
				for(Subjects subjects2 : checkSubjects)
				{
					
//					schedule.setSubjects(subjects2);
					subjectSetID.setSubjID(subjects2.getSubjID());
				}
			 }
			 
			 else
			 {
				 subjectSetID=subjects;
				 session.save(subjects);
			 }
			
			
			session.getTransaction().commit();
			session.close();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return subjectSetID;
	}
	
	public void addSchedules(Schedule schedule)
	{
		try
		{
			
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(schedule);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	
}
