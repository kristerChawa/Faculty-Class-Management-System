package com.action.profiling;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.ProfilingHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Resume;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Delete_Resume extends ActionSupport implements SessionAware {
	
	private Resume rModel = new Resume();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		ProfilingHelper p_helper = new ProfilingHelper();
		GenericHelper g_helper = new GenericHelper();
		try {
			Users uModel = (Users)userSession.get(Utilities.user_sessionName);
			p_helper.deleteResume(rModel);
			AuditLog auditLog = new AuditLog(AuditLogUtil.deleteAction, AuditLogUtil.resumeType, uModel);
			g_helper.AddAuditLog(auditLog);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return INPUT;
		}
		
	}
	
	public void setrModel(Resume rModel) {
		this.rModel = rModel;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}	
