package com.action.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.LoginHelper;
import com.helper.HelperClass;
import com.helper.Utilities;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Update_CurrentSessionAction extends ActionSupport implements SessionAware{
	 
	private Users usersModel = new Users();
	private String JSESSIONID;
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			usersModel = (Users) userSession.get(Utilities.user_sessionName);
			LoginHelper loginHelper = new LoginHelper();
			
			if(usersModel.getUsername().equals(Utilities.adminUsername)){
				usersModel = HelperClass.Admin();
			}else if(usersModel.getUsername().equals(Utilities.secUsername)){
				usersModel = HelperClass.Secretary();
			}else{
				usersModel = loginHelper.getUserDetails(usersModel.getUserID());
				JSESSIONID = HelperClass.setJSESSIONID(userSession);
			}
		}catch(Exception e){
			return INPUT;
		}
		return SUCCESS;
	}
	
	public Users getUsersModel() {
		return usersModel;
	}
	public void setUsersModel(Users usersModel) {
		this.usersModel = usersModel;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
	public String getJSESSIONID() {
		return JSESSIONID;
	}
	
	
	
}
