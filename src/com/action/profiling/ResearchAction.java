package com.action.profiling;

import com.model.profiling.ResearchModel;
import com.opensymphony.xwork2.ActionSupport;

public class ResearchAction extends ActionSupport {
	
	private ResearchModel rModel = new ResearchModel();
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(rModel.getResearchName());
		return SUCCESS;
	}
	
	public ResearchModel getrModel() {
		return rModel;
	}
	
	public void setrModel(ResearchModel rModel) {
		this.rModel = rModel;
	}
}
