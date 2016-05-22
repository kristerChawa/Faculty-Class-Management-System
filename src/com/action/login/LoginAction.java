package com.action.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.LoginHelper;
import com.helper.HelperClass;
import com.helper.Utilities;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	private Users usersModel = new Users();
	private String JSESSIONID;
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		LoginHelper loginHelper = new LoginHelper();
		GenericHelper g_helper = new GenericHelper();
		try{
			
			usersModel = loginHelper.loginUser(usersModel);
			
			if(usersModel != null){
				
				setSession(usersModel);
				JSESSIONID = HelperClass.setJSESSIONID(userSession);
				
				if(usersModel.getUsername().equalsIgnoreCase(Utilities.adminUsername)){
					g_helper.setAdminLoginAuditLog(usersModel);
				}
				return SUCCESS;
			}
			return INPUT;
			
		}catch(Exception e){
			e.printStackTrace();
			return INPUT;
		}
	
	}
	
	public void clearSession(){
		userSession.clear();
		userSession.remove(Utilities.user_sessionName);
	}
	
	public void setSession(Users usersModel){
		clearSession(); //Before creating a login session, be sure to clear it first.
		userSession.put(Utilities.user_sessionName, usersModel);
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
	
	public String getJSESSIONID() {
		return JSESSIONID;
	}
	
}
