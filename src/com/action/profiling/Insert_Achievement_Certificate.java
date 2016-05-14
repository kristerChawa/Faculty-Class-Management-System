package com.action.profiling;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.ProfilingHelper;
import com.helper.AuditLogUtil;
import com.helper.FileModel;
import com.helper.Utilities;
import com.model.Achievements;
import com.model.AuditLog;
import com.model.ProfessorProfile;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Insert_Achievement_Certificate extends ActionSupport 
implements ModelDriven<FileModel>, ServletContextAware, SessionAware {

	private FileModel acModel = new FileModel();

	private ServletContext context;
	private Map<String, Object> userSession;

	@Override
	public String execute() throws Exception {

		ProfilingHelper p_Helper = new ProfilingHelper();	
		ProfessorProfile professorProfile = new ProfessorProfile();
		
		GenericHelper g_helper = new GenericHelper();
		Users uModel = new Users();
		try{
			context = ServletActionContext.getServletContext();
			String serverPath = context.getRealPath("/");

			if(acModel.hasUploadedAchievements(serverPath)){
				
				uModel = (Users) userSession.get(Utilities.user_sessionName);
				professorProfile.setPpID(uModel.getUserID());
				Achievements achievements = new 
						Achievements(acModel.getDataName(), acModel.getUrl(),
								professorProfile);
				p_Helper.addAchievements(achievements);
				
				AuditLog auditLog = new AuditLog(AuditLogUtil.addAction, AuditLogUtil.achievementType, uModel);
				g_helper.AddAuditLog(auditLog);
				
				
				return SUCCESS;
			}

			return INPUT;
		}
		catch(Exception e){
			return INPUT;
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}

	@Override
	public FileModel getModel() {
		// TODO Auto-generated method stub
		return acModel;
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}
}
