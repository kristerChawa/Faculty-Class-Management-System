package com.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Schedule;
import com.model.Subjects;

public class SchedulingHelper {

	//static SessionFactory sessionFactory = null;
	Session session = null;
//	static
//	{
//		sessionFactory=new Configuration().configure().buildSessionFactory();
//	}
	
	
	public Subjects addSubjects(Subjects subjects){
		
		Subjects subjectSetID=new Subjects();
		Transaction trans = null;
		try
		{
			session=HibernateFactory.getSession();
			trans = session.beginTransaction();
			
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
			
			trans.commit();
			session.getTransaction().commit();
			
			
		}
		catch(Exception ex)
		{
			if(trans != null){
				trans.rollback();
			}
			ex.printStackTrace();
		}
		session.close();
		return subjectSetID;
	}
	
	public void addSchedules(Schedule schedule)
	{
		try
		{
			
			session=HibernateFactory.getSession();
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
