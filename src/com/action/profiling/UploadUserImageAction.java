package com.action.profiling;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.HibernateUtil.ProfilingHelper;
import com.model.UploadUserImageModel;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UploadUserImageAction extends ActionSupport 
			implements ModelDriven<UploadUserImageModel>, ServletRequestAware {
	
	
	private UploadUserImageModel upiModel = new UploadUserImageModel();
	private Users users;
	private ProfilingHelper session_Profiling= new ProfilingHelper();
	private HttpServletRequest request;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		//Save to database with accordance to the current User
	
		
		String serverPath = request.getServletContext().getRealPath("/");
		upiModel.doUpload(serverPath);
		
		
		users=new Users(upiModel.getUrl(), 1); //sessionAware
		session_Profiling.updatePicture(users);
		
		return SUCCESS;
	}
	
	@Override
	public UploadUserImageModel getModel() {
		// TODO Auto-generated method stub
		return upiModel;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
