package com.action.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.SchedulingHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.FacultyAssign;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Assign_Faculty extends ActionSupport implements SessionAware{
	
	private List<FacultyAssign> fModel = new ArrayList<FacultyAssign>();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		SchedulingHelper s_helper = new SchedulingHelper();
		GenericHelper g_helper = new GenericHelper();
		Users uModel = (Users) userSession.get(Utilities.user_sessionName);
		try {
			s_helper.addFacultyAssign(fModel);
			AuditLog auditLog = new AuditLog(AuditLogUtil.assignAction, AuditLogUtil.assignFacultyType, uModel);
			g_helper.AddAuditLog(auditLog);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public void setfModel(List<FacultyAssign> fModel) {
		this.fModel = fModel;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
}
