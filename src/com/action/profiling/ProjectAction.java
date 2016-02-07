package com.action.profiling;

import com.HibernateUtil.ProfilingHelper;
import com.model.Projects;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectAction extends ActionSupport {
	
	private Projects pModel = new Projects();
	
	@Override
	public String execute() throws Exception 
	{
		ProfilingHelper session_Helper=new ProfilingHelper();
		
		try{
		System.out.println(pModel.getProjectName());
//		session_Helper.addProjects(pModel);
		
//		session_Helper.viewProjects();
		}catch(Exception e){
			return INPUT;
		}
		return SUCCESS;
	}


//	@Override
//	public Projects getModel() {
//		// TODO Auto-generated method stub
//		return pModel;
//	}


	public Projects getpModel() {
		return pModel;
	}


	public void setpModel(Projects pModel) {
		this.pModel = pModel;
	}
	
}
