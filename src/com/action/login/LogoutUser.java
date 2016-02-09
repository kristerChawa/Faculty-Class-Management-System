package com.action.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.helper.Utilities;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutUser extends ActionSupport implements SessionAware, ServletRequestAware {
	
	private Map<String, Object> userSession;
	private HttpServletRequest request;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Trying to logout...");
		userSession.forEach( (k,v) -> System.out.println("key= " + k + " - " + "value= " + v));
		userSession.clear();
		userSession.remove(Utilities.user_sessionName);
		System.out.println(request.getSession(false).getCreationTime());
		
		request.getSession(false).invalidate();
		System.out.println(request.getSession(false).getCreationTime());
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
