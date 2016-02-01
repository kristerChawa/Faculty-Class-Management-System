package com.action.developer;

import java.util.ArrayList;
import java.util.List;

import com.HibernateUtil.DeveloperHelper;
import com.model.Password;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class DeveloperAction extends ActionSupport {
	
	private List<Users> users = new ArrayList<Users>();
	
	@Override
	public String execute() throws Exception {
		
		DeveloperHelper session_Helper = new DeveloperHelper();
		Password password = null;
		
		for (Users uModel : users)
		{ 
			session_Helper.addUser(uModel);
			password=new Password(uModel);
			session_Helper.addPassword(password);
		}
		
		return SUCCESS;
	}
	
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
}
