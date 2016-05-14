package com.action.profiling;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.ProfilingHelper;
import com.helper.AuditLogUtil;
import com.helper.FileModel;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Upload_UserImageAction extends ActionSupport 
implements ModelDriven<FileModel>, ServletRequestAware, SessionAware {

	private FileModel upiModel = new FileModel();
	private Map<String, Object> userSession;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {

		ProfilingHelper session_Profiling= new ProfilingHelper();
		GenericHelper g_helper = new GenericHelper();
		Users uModel = new Users();
		try {
			String serverPath = request.getServletContext().getRealPath("/");
			if(upiModel.hasUploadedUserImage(serverPath)){
				
				uModel = (Users) userSession.get(Utilities.user_sessionName);
				uModel.setPictureUrl(upiModel.getUrl());
				session_Profiling.updatePicture(uModel);
				AuditLog auditLog = new AuditLog(AuditLogUtil.uploadAction, AuditLogUtil.imageType, uModel);
				g_helper.AddAuditLog(auditLog);
				return SUCCESS;
			}else{
				return INPUT;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}
	}

	@Override
	public FileModel getModel() {
		// TODO Auto-generated method stub
		return upiModel;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
