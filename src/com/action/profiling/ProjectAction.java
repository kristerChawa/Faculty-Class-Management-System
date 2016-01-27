package com.action.profiling;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Projects;
import com.model.profiling.ProjectModel;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectAction extends ActionSupport {
	
	private Projects pModel = new Projects();
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(pModel.getProjectName());
		
		
		session.beginTransaction();
		
		session.save(pModel);
		
		session.getTransaction().commit();
		session.close();
		
		return SUCCESS;
	}
	
	public Projects getpModel() {
		return pModel;
	}
	public void setpModel(Projects pModel) {
		this.pModel = pModel;
	}
}
