package com.action.extras;

import com.HibernateUtil.GenericHelper;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Reset_Password extends ActionSupport implements ModelDriven<Users>{
	
	private Users uObj = new Users();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		GenericHelper g_helper = new GenericHelper();

		return g_helper.resetPassword(uObj) ? SUCCESS : INPUT;
		
	}
	
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return uObj;
	}
}
