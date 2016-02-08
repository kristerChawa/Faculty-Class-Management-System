package com.action.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.LoginHelper;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	
	private Users usersModel = new Users();
	private Map<String, Object> userSession;
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		LoginHelper loginHelper = new LoginHelper();
		try{
			String username = usersModel.getUsername(),
					password = usersModel.getPassword().get(0).getPassword();
			
			if(!loginHelper.loginUser(username, password)){
				return INPUT;
			}
			usersModel = loginHelper.getUserDetails(usersModel.getUsername());
			
			userSession.put("usersModel", usersModel);
			return SUCCESS;
		}catch(Exception e){
			return INPUT;
		}
	
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
