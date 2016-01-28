package com.action.developer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class DeveloperAction extends ActionSupport {
	
	
	List<Users> users=getUsers();
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	
	
	
	
	@Override
	public String execute() throws Exception {
		
		session.beginTransaction();
		for (Users uModel : getUsers())
		{ 
			session.save(uModel); 
		}

		
		session.getTransaction().commit();
		session.close();
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
