package com.action.attendance;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.AttendanceHelper;
import com.HibernateUtil.GenericHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Classlist;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Delete_Student extends ActionSupport implements SessionAware {
	
	private Classlist clObj = new Classlist();
	private Map<String, Object> userSession;
	 
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		AttendanceHelper a_helper = new AttendanceHelper();
		GenericHelper g_helper = new GenericHelper();
		Users uModel = (Users) userSession.get(Utilities.user_sessionName);
		
		try {
			a_helper.deleteStudent(clObj);
			
			AuditLog auditLog = new AuditLog(AuditLogUtil.deleteAction, AuditLogUtil.studentType, uModel);
			g_helper.AddAuditLog(auditLog);
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return INPUT;
		}

	}
	
	public void setClObj(Classlist clObj) {
		this.clObj = clObj;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
