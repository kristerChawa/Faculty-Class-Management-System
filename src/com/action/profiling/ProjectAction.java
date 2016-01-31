package com.action.profiling;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.HibernateUtil.ProfilingHelper;
import com.model.Projects;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectAction extends ActionSupport {
	
	private Projects pModel = new Projects();
	ProfilingHelper session_Helper=new ProfilingHelper();
	@Override
	public String execute() throws Exception 
	{
		
		session_Helper.addProjects(pModel);
		session_Helper.viewProjects();
		return SUCCESS;
	}


	public Projects getpModel() {
		return pModel;
	}


	public void setpModel(Projects pModel) {
		this.pModel = pModel;
	}
	
	
}
