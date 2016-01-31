package com.action.developer;

import java.util.List;

import com.HibernateUtil.DeveloperHelper;
import com.model.Password;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class DeveloperAction extends ActionSupport {
	
	DeveloperHelper session_Helper=new DeveloperHelper();
	List<Users> users=getUsers();
	private Password password;
	
	
	
	
	@Override
	public String execute() throws Exception {
		
		for (Users uModel : getUsers())
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
	
	
	/*
	Iterator<Users> i = users.iterator();
	while(i.hasNext()){
		uModel = new Users();
		uModel.setIdNo(i.next().getIdNo());
		uModel.setFirstName(i.next().getFirstName());
		uModel.setLastName(i.next().getLastName());
		uModel.setUsername(i.next().getUsername());
		
		System.out.println(uModel.getFirstName());
		System.out.println(uModel.getLastName());
		System.out.println(uModel.getUsername());
		System.out.println(uModel.getIdNo());
	}
*/
	
}
