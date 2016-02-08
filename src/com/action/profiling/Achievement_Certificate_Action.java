package com.action.profiling;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.model.Achievement_Certificate_Model;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Achievement_Certificate_Action extends ActionSupport 
		implements ModelDriven<Achievement_Certificate_Model>, ServletContextAware {
	
	private Achievement_Certificate_Model acModel = new Achievement_Certificate_Model();
	private ServletContext context;
	
	@Override
	public String execute() throws Exception {
		
//		try{
			context = ServletActionContext.getServletContext();
			String serverPath = context.getRealPath("/");
			System.out.println(serverPath);
			acModel.doUpload(serverPath);
		
		
//		ProfilingHelper session_Helper = new ProfilingHelper();
//		
//		Achievements achievements = new 
//					Achievements(acModel.getAchievement_Certificate_Name(), acModel.getAchievement_Certificate_Url());
//		
//		session_Helper.addAchievements(achievements);
//		session_Helper.viewAchievements();
//		}catch(Exception e){
//			return INPUT;
//			
//		}
		
		return SUCCESS;
	}

	@Override
	public Achievement_Certificate_Model getModel() {
		// TODO Auto-generated method stub
		return acModel;
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}
	
	

}
