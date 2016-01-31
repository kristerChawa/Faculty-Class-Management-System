package com.action.developer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.HibernateUtil.DeveloperHelper;
import com.HibernateUtil.LoginHelper;
import com.model.Password;
import com.model.ProfessorProfile;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class DeveloperAction extends ActionSupport {
	
	DeveloperHelper session_Helper=new DeveloperHelper();
	LoginHelper login_Helper=new LoginHelper();
	List<Users> users=getUsers();
	
	
	
	
	@Override
	public String execute() throws Exception {
		
		for (Users uModel : getUsers())
		{ 
			//add Users
			session_Helper.addUser(uModel);
			
			//add passwords
			Password password=new Password(uModel);
			session_Helper.addPassword(password);
			
			//add ProfessorProfile
			ProfessorProfile professorProfile=new ProfessorProfile(uModel);
			session_Helper.addProfessorProfile(professorProfile);
			
			login_Helper.getID();
			
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
