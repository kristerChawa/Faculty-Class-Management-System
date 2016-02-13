package com.action.profiling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.ProfilingHelper;
import com.helper.Utilities;
import com.model.ProfessorProfile;
import com.model.Researches;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class View_Researches extends ActionSupport implements SessionAware{
	
	private List<Researches> rList = new ArrayList<Researches>();
	private Map<String, Object> userSession;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ProfilingHelper p_helper = new ProfilingHelper();
		ProfessorProfile p_profile = new ProfessorProfile();
		Users uModel = new Users();
		try {
			
			uModel = (Users) userSession.get(Utilities.user_sessionName);
			p_profile.setPpID(uModel.getUserID());
			rList = p_helper.viewResearches(p_profile);
		} catch (Exception e) {
			// TODO: handle exception
			return INPUT;
		}
		return SUCCESS;
	}
	
	public List<Researches> getrList() {
		return rList;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
