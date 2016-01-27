package com.action.developer;

import java.util.List;

import com.model.developer.DeveloperModel;
import com.opensymphony.xwork2.ActionSupport;

public class DeveloperAction extends ActionSupport {
	
	
	List<DeveloperModel> users;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		users.forEach(i -> System.out.println(i.getFirstName() + i.getLastName() + i.getIdNo() + i.getUsername()));
		return SUCCESS;
	}
	
	public List<DeveloperModel> getUsers() {
		return users;
	}
	public void setUsers(List<DeveloperModel> users) {
		this.users = users;
	}
}
