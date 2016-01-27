package com.action.profiling;

import com.model.profiling.ProjectModel;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectAction extends ActionSupport {
	
	private ProjectModel pModel = new ProjectModel();
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(pModel.getProjectName());
		
		return SUCCESS;
	}
	
	public ProjectModel getpModel() {
		return pModel;
	}
	public void setpModel(ProjectModel pModel) {
		this.pModel = pModel;
	}
}
