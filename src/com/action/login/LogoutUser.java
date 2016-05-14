package com.action.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutUser extends ActionSupport implements SessionAware {
	
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			GenericHelper g_helper = new GenericHelper();
			Users uModel = (Users) userSession.get(Utilities.user_sessionName);
			
			if(!uModel.getUsername().equals(Utilities.adminUsername)){
				AuditLog auditLog = new AuditLog(AuditLogUtil.logoutAction, AuditLogUtil.logoutType, uModel);
				g_helper.AddAuditLog(auditLog);
			}
			
			
			userSession.clear();
			userSession.remove(Utilities.user_sessionName);
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}
		
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
