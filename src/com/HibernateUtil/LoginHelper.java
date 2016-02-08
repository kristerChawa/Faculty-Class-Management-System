package com.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Users;

public class LoginHelper {

	static SessionFactory sessionFactory = null;
	Session session = null;
	static
	{
		sessionFactory=new Configuration().configure().buildSessionFactory();
		
	}
	
	public int getUserID(String username) //Jm was here :)
	{
		//Handle the exception when the the username is not found in the database.
		//Exception: NullPointerException
		
		int userID = 0;
		try
		{
			
			session=sessionFactory.openSession();
			session.beginTransaction();
			
			Query query=session.createQuery("from Users where username=:uName");
			query.setParameter("uName", username);
			
			Users users=(Users) query.uniqueResult();
			
			userID = users.getUserID();
			session.getTransaction().commit();
			session.close();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return userID;
	
	}
	
	public boolean loginUser(String username, String password){
		
		boolean isAuthenticated = false;
		try{ 
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Users where username = :uName");
			query.setParameter("uName", username);
			Users users = (Users) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			
			if(users != null){
				if(users.getUsername().equalsIgnoreCase(username) 
						&& users.getPassword().get(0).getPassword().equals(password)){
					isAuthenticated = true;
				}
			}else{
				isAuthenticated = false;
			}
			
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		
		return isAuthenticated;
	}
	
	public Users getUserDetails(String username){
		Users usersModel = new Users();
		
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
		
			//But I don't want to get the password for security reasons.
			Query query = session.createQuery("from Users where username = :uName");
			query.setParameter("uName", username);
			
			//Single result
			usersModel = (Users) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return usersModel;
		
	}
}
