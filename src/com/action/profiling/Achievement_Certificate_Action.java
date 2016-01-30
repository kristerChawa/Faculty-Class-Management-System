package com.action.profiling;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.HibernateUtil.ProfilingHelper;
import com.model.Achievement_Certificate_Model;
import com.model.Achievements;

import com.model.Achievement_Certificate_Model;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Achievement_Certificate_Action extends ActionSupport 
		implements ModelDriven<Achievement_Certificate_Model>, ServletRequestAware {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private Achievement_Certificate_Model acModel = new Achievement_Certificate_Model();
	
	private Achievements achievements;
	ProfilingHelper session_Helper=new ProfilingHelper();

	@Override
	public String execute() throws Exception {
		
		String serverPath = request.getServletContext().getRealPath("/");
		
		acModel.doUpload(serverPath);
		achievements=new Achievements(acModel.getAchievement_Certificate_Name(), acModel.getAchievement_Certificate_Url());
		
		session_Helper.addAchievements(achievements);
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}
	@Override
	public Achievement_Certificate_Model getModel() {
		// TODO Auto-generated method stub
		return acModel;
	}
	
	

}
