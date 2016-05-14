package com.action.extras;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.HibernateUtil.GenericHelper;
import com.helper.SendEmail;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Edit_Password extends ActionSupport implements ServletRequestAware{
	
	private Users uObj = new Users();
	private HttpServletRequest request;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			
			String path = request.getHeader("referer");
			
			SendEmail.sendForgotPassword(uObj, path);

			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return INPUT;
		}
	}
	
	public void setuObj(Users uObj) {
		this.uObj = uObj;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 this.request = request;
	}
}
