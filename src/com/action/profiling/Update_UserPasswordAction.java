package com.action.profiling;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.ProfilingHelper;
import com.helper.AuditLogUtil;
import com.helper.HelperClass;
import com.model.AuditLog;
import com.model.Password;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Update_UserPasswordAction extends ActionSupport implements SessionAware {

	private Password pModel = new Password();
	private Map<String, Object> userSession;
	 
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Users uModel = new Users();
		ProfilingHelper profiling_helper=new ProfilingHelper();
		GenericHelper g_helper = new GenericHelper();
		uModel = (Users) userSession.get("usersModel");
		
		
		
		//Check muna kung tama yung currentPassword sa database then kung tama..
		
		if(true){
			if(HelperClass.verify_password(pModel.getNewPassword(), pModel.getNewPassword_Verify())){
				pModel.setPassword(HelperClass.encrypt(pModel.getNewPassword()));
				
				profiling_helper.updatePassword(pModel, uModel);
				
				AuditLog auditLog = new AuditLog(AuditLogUtil.updateAction, AuditLogUtil.passwordType, uModel);
				g_helper.AddAuditLog(auditLog);
				return SUCCESS;
			}
		}
		return INPUT;
	}

	public Password getpModel() {
		return pModel;
	}

	public void setpModel(Password pModel) {
		this.pModel = pModel;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
	
}
