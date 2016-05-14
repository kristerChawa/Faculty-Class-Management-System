package com.action.extras;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class MyActivityLog extends ActionSupport implements SessionAware {
	
	private List<AuditLog> auditLogList = new ArrayList<AuditLog>(); 
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		GenericHelper g_helper = new GenericHelper();
		try {
			Users uModel = (Users) userSession.get(Utilities.user_sessionName);
			auditLogList = g_helper.viewMyActivityLog(uModel);
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}
		
	}
	
	public List<AuditLog> getAuditLogList() {
		return auditLogList;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
