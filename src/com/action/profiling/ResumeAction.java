package com.action.profiling;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.ProfilingHelper;
import com.helper.Utilities;
import com.model.Resume;
import com.model.ResumeModel;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ResumeAction extends ActionSupport implements ModelDriven<ResumeModel>, ServletRequestAware, SessionAware{
	
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ResumeModel rModel = new ResumeModel();
	private Resume resume;

	private Map<String, Object> userSession;
	
	
	@Override
	public String execute() throws Exception 
	{
		ProfilingHelper session_Helper=new ProfilingHelper();
		Users uModel = new Users();
		uModel = (Users)userSession.get(Utilities.user_sessionName);
		
		String serverPath = request.getServletContext().getRealPath("/");
		rModel.doUpload(serverPath);
		
		resume=new Resume(rModel.getUrl());
		session_Helper.addResume(resume);
		session_Helper.viewResume();
		return SUCCESS;
	}
	
	
	
	@Override
	public ResumeModel getModel() {
		return rModel;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	

}
