package com.action.extras;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.helper.AuditLogUtil;
import com.helper.SendEmail;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Feedback;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class SendFeedback extends ActionSupport implements SessionAware {

	private Feedback feedback = new Feedback();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		GenericHelper g_helper = new GenericHelper();
		SendEmail send_email = new SendEmail();
		
		try {
			Users user = (Users) userSession.get(Utilities.user_sessionName);
			feedback.setUser(user);
			
			AuditLog auditLog = new AuditLog(AuditLogUtil.emailAction, AuditLogUtil.emailType, user);
			
			g_helper.saveFeedback(feedback);
			g_helper.AddAuditLog(auditLog);
			send_email.sendFeedback(feedback);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
