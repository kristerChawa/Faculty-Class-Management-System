package com.action.profiling;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.ProfilingHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.Achievements;
import com.model.AuditLog;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Delete_Achievements_Certifications extends ActionSupport implements SessionAware {
	
	private Achievements aModel = new Achievements();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		ProfilingHelper p_helper = new ProfilingHelper();
		GenericHelper g_helper = new GenericHelper();
		try{
			Users uModel = (Users)userSession.get(Utilities.user_sessionName);
			p_helper.deleteAchievements(aModel);
			AuditLog auditLog = new AuditLog(AuditLogUtil.deleteAction, AuditLogUtil.achievementType, uModel);
			g_helper.AddAuditLog(auditLog);
			return SUCCESS;
		}catch(Exception e){
			return INPUT;
		}
	}
	public void setaModel(Achievements aModel) {
		this.aModel = aModel;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
}
