package com.action.profiling;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.HibernateUtil.ProfilingHelper;
import com.model.Resume;
import com.model.ResumeModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ResumeAction extends ActionSupport implements ModelDriven<ResumeModel>, ServletRequestAware{
	
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ResumeModel rModel = new ResumeModel();
	private Resume resume;
	ProfilingHelper session_Helper=new ProfilingHelper();
	
	
	@Override
	public String execute() throws Exception 
	{
		
		String serverPath = request.getServletContext().getRealPath("/");
		rModel.doUpload(serverPath);
		
		resume=new Resume(rModel.getResumeUrl());
		session_Helper.addResume(resume);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ResumeModel rModel = new ResumeModel();
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String serverPath = request.getServletContext().getRealPath("/");
		rModel.doUpload(serverPath);
		System.out.println(rModel.getResumeUrl());
		return SUCCESS;
	}
	
	@Override
	public ResumeModel getModel() {
		// TODO Auto-generated method stub
		return rModel;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}

}
