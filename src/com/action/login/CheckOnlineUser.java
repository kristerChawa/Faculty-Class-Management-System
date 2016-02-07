package com.action.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class CheckOnlineUser extends ActionSupport implements SessionAware {
	
	private Map<String, Object> userSession;
	
	private Users usersModel = new Users();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		//Get currentSession
		usersModel =  (Users)userSession.get("usersModel");
		if(usersModel.getUsername() == null || usersModel.getUsername().isEmpty()){
			return INPUT;
		}
		System.out.println("Checking... ");
		System.out.println(usersModel.getUsername());
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
	public Users getUsersModel() {
		return usersModel;
	}
	public void setUsersModel(Users usersModel) {
		this.usersModel = usersModel;
	}
}
