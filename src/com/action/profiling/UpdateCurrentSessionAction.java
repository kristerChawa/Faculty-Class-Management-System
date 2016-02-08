package com.action.profiling;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.LoginHelper;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCurrentSessionAction extends ActionSupport implements SessionAware{
	
	private Users uModel = new Users();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			uModel = (Users) userSession.get("usersModel");
			LoginHelper loginHelper = new LoginHelper();
			
			uModel = loginHelper.getUserDetails(uModel.getUsername());
		}catch(Exception e){
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	public Users getUser() {
		return uModel;
	}
	public void setUser(Users user) {
		this.uModel = user;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
}
