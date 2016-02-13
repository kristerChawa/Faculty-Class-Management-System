package com.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Achievements;
import com.model.Expertise;
import com.model.Password;
import com.model.ProfessorProfile;
import com.model.Projects;
import com.model.Researches;
import com.model.Resume;
import com.model.Users;

public class ProfilingHelper {

	Session session = null;

	
	public void addResearches(Researches researches)
	{
		try
		{
			session = HibernateFactory.getSession();
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
			session=HibernateFactory.getSession();
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
			session=HibernateFactory.getSession();
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
	
	public void addResume(Resume resume, ProfessorProfile profile)
	{
		Transaction trans = null;
		
		try
		{
			List<Resume> resumeList = null;
			session=HibernateFactory.getSession();
			trans = session.beginTransaction();
			
			int PPID = profile.getPpID();
			profile = (ProfessorProfile) session.get(ProfessorProfile.class, PPID);
			resumeList = profile.getResume();
			
			if(resumeList.isEmpty() || resumeList == null){
				session.save(resume);
			}else{
				
				int resumeID = resumeList.get(0).getRID();
				
				Resume rObj = (Resume) session.get(Resume.class, resumeID);
				rObj.setResumeUrl(resume.getResumeUrl());
				session.update(rObj);
			}
			
			trans.commit();
		}
		catch(Exception ex)
		{
			if(trans != null){
				trans.rollback();
			}
			ex.printStackTrace();
		}
		session.close();
	}
	
	public void updatePicture(Users users)
	{
		
		Transaction trans = null;
		try
		{
			session=HibernateFactory.getSession();
			trans = session.beginTransaction();
			
			Users updateUser=(Users)session.get(Users.class, users.getUserID());
			updateUser.setPictureUrl(users.getPictureUrl());
			
			session.update(updateUser);
			trans.commit();
			
		}
		catch(Exception ex)
		{
			if(trans != null){
				trans.rollback();
			}
			ex.printStackTrace();
		}
		session.close();
	}
	
	public void updateUserProfile(Users user)
	{
		LoginHelper login_helper = new LoginHelper();
		Transaction trans = null;
		
		try
		{
			session = HibernateFactory.getSession();
			trans = session.beginTransaction();
			
			int userID = login_helper.getUserID(user.getUsername());
			System.out.println(userID);
			
			Users uModel = (Users) session.get(Users.class , userID);
			uModel.setFirstName(user.getFirstName());
			uModel.setLastName(user.getLastName());
			
			session.update(uModel);
			trans.commit();
			
		}
		catch(Exception e)
		{	
			if(trans != null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		session.close();
	}
	
	public void updatePassword(Password password, Users users)
	{
		Transaction trans = null;	
		try
		{
			
			session=HibernateFactory.getSession();
			trans = session.beginTransaction();
			
			int userID = users.getUserID();
			
			Password updatePassword=(Password)session.get(Password.class, userID);
			updatePassword.setPassword(password.getPassword());
			
			updatePassword.setNewPassword(password.getNewPassword());
			updatePassword.setNewPassword_Verify(password.getNewPassword_Verify());
			
			session.update(updatePassword);
			trans.commit();
			
		}
		catch(Exception ex)
		{
			if(trans != null){
				trans.rollback();
			}
			ex.printStackTrace();
		}
		session.close();
	}
	
	
	
	
	public List<Researches>viewResearches(ProfessorProfile professor) //overload id from loginHelper
	{
		session=HibernateFactory.getSession();
		session.beginTransaction();
		List<Researches> list = null;
		ProfessorProfile professorProfile=(ProfessorProfile) session.get(ProfessorProfile.class, professor.getPpID());
		list = professorProfile.getResearches();
		list.forEach(i -> System.out.println(i.getResearchName()));
		session.getTransaction().commit();
		session.close();
		return list;
		
	}
	
	public List<Projects>viewProjects(ProfessorProfile profile) //overload id from loginHelper
	{
		session=HibernateFactory.getSession();
		session.beginTransaction();
		
		List<Projects>list=null;
		ProfessorProfile professorProfile=(ProfessorProfile) session.get(ProfessorProfile.class, profile.getPpID());
		
		list=professorProfile.getProjects();
		list.forEach(i -> System.out.println(i.getProjectName()));
		
		
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public List<Resume>viewResume(ProfessorProfile profile)   //overload id from loginHelper
	{
		session=HibernateFactory.getSession();
		session.beginTransaction();
		List<Resume>list=null;
		ProfessorProfile professorProfile = (ProfessorProfile) session.get(ProfessorProfile.class, profile.getPpID());
		list=professorProfile.getResume();
		list.forEach(i -> System.out.println(i.getResumeUrl()));
		session.getTransaction().commit();
		session.close();
		return list;
		
	}
	public List<Achievements>viewAchievements(ProfessorProfile profile)  //overload id from loginHelper
	{
		
		session=HibernateFactory.getSession();
		session.beginTransaction();
		List<Achievements>list=null;
		ProfessorProfile professorProfile=(ProfessorProfile) session.get(ProfessorProfile.class, profile.getPpID());
		list=professorProfile.getAchievements();
		list.forEach(i -> System.out.println(i.getAchievement_Certificate_Url()));
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	
	public void deleteResearches(Researches researches)
	{

		try
		{
			session=HibernateFactory.getSession();
			session.beginTransaction();
			
			Researches rObj=(Researches)session.get(Researches.class, researches.getrID());
			session.delete(rObj);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void deleteAchievements(Achievements achievements)
	{
		Transaction trans = null;
		try
		{
			session=HibernateFactory.getSession();
			trans = session.beginTransaction();
			
			
			Achievements aObj=(Achievements)session.get(Achievements.class, achievements.getaID());
			session.delete(aObj);
			trans.commit();
			
		}
		catch(Exception ex)
		{
			if(trans != null){
				trans.rollback();
			}
			ex.printStackTrace();
		}
		session.close();
	}
	
	public void deleteProjects(Projects projects)
	{
		Transaction trans = null;
		try
		{
			session=HibernateFactory.getSession();
			trans = session.beginTransaction();
			
			Projects pObj=(Projects)session.get(Projects.class, projects.getPrID());
			session.delete(pObj);
			
			trans.commit();
			
		}
		catch(Exception ex)
		{
			if(trans != null){
				trans.rollback();
			}
			ex.printStackTrace();
		}
		session.close();
	}
	
	public void deleteResume(Resume resume)
	{
		Transaction trans = null;
		try
		{
			session=HibernateFactory.getSession();
			trans = session.beginTransaction();
			
			Resume rObj = (Resume) session.get(Resume.class, resume.getRID());
			session.delete(rObj);
			
			trans.commit();
			
		}
		catch(Exception ex)
		{
			if(trans != null){
				trans.rollback();
			}
			ex.printStackTrace();
		}
		session.close();
		
	}
	
	
	public void addExpertise(Expertise expertise)
	{
		try
		{
			session = HibernateFactory.getSession();
			session.beginTransaction();
			session.save(expertise);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
