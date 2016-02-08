package com.action.profiling;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.ProfilingHelper;
import com.model.ProfessorProfile;
import com.model.Researches;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ResearchAction extends ActionSupport implements SessionAware {
	
	private Researches rModel = new Researches();
	
	private ProfessorProfile professorProfile=new ProfessorProfile();
	ProfilingHelper session_Helper=new ProfilingHelper();
	
	private Map<String, Object> userSession;
	

	@Override
	public String execute() throws Exception
	{
		try{
			
			System.out.println(userSession.get("usersModel"));
			
			System.out.println(rModel.getResearchName());
			//professorProfile.setPpID(userSession.);
			
			session_Helper.addResearches(rModel);
			//session_Helper.viewResearches();
		}catch(Exception e){
			return INPUT;
		}
		return SUCCESS;
	}
	
	public Researches getrModel() {
		return rModel;
	}


	public void setrModel(Researches rModel) {
		this.rModel = rModel;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.userSession = session;
		
	}
}
