package com.action.profiling;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.ProfilingHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Projects;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Delete_Project extends ActionSupport implements SessionAware{
	
	private Projects pModel = new Projects();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		ProfilingHelper p_helper = new ProfilingHelper();
		GenericHelper g_helper = new GenericHelper();
		try {
			Users uModel = (Users) userSession.get(Utilities.user_sessionName);
			p_helper.deleteProjects(pModel);
			AuditLog auditLog = new AuditLog(AuditLogUtil.deleteAction, AuditLogUtil.projectType, uModel);
			g_helper.AddAuditLog(auditLog);
			return SUCCESS;
		} catch (Exception e) {
			return INPUT;
		}
	}
	
	public void setpModel(Projects pModel) {
		this.pModel = pModel;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
