package com.action.profiling;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.ProfilingHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.ProfessorProfile;
import com.model.Projects;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Insert_Project extends ActionSupport implements SessionAware {
	
	private Projects pModel = new Projects();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception 
	{
		ProfilingHelper session_Helper = new ProfilingHelper();
		Users uModel = new Users();
		GenericHelper g_helper = new GenericHelper();
		try{
			uModel = (Users) userSession.get(Utilities.user_sessionName);
			
			ProfessorProfile professorProfile=new ProfessorProfile();
			professorProfile.setPpID(uModel.getUserID());
			pModel.setProfessorProfile(professorProfile);
			pModel.setTimestamp(LocalDateTime.now().format(Utilities.formatter).toString());
			
			session_Helper.addProjects(pModel);
			AuditLog auditLog = new AuditLog(AuditLogUtil.addAction, AuditLogUtil.projectType, uModel);
			g_helper.AddAuditLog(auditLog);
		}
		catch(Exception e){
			return INPUT;
		}
		return SUCCESS;
	}

	public Projects getpModel() {
		return pModel;
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
