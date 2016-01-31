package com.action.profiling;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.HibernateUtil.ProfilingHelper;
import com.model.Researches;
import com.opensymphony.xwork2.ActionSupport;

public class ResearchAction extends ActionSupport {
	
	private Researches rModel = new Researches();
	ProfilingHelper session_Helper=new ProfilingHelper();
	
	

	@Override
	public String execute() throws Exception
	{
		
        session_Helper.addResearches(rModel);
		session_Helper.viewResearches();
		return SUCCESS;
	}
	
	public Researches getrModel() {
		return rModel;
	}

	
	public void setrModel(Researches rModel) {
		this.rModel = rModel;
	}

	
}
