package com.action.profiling;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Researches;
import com.opensymphony.xwork2.ActionSupport;

public class ResearchAction extends ActionSupport {
	
	private Researches rModel = new Researches();
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	
	

	@Override
	public String execute() throws Exception {
		
		System.out.println(rModel.getResearchName());
		session.beginTransaction();
		
		session.save(rModel);
		
		session.getTransaction().commit();
		session.close();
		return SUCCESS;
	}
	
	public Researches getrModel() {
		return rModel;
	}


	public void setrModel(Researches rModel) {
		this.rModel = rModel;
	}

	
}
