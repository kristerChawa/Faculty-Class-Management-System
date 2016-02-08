package com.action.profiling;

import com.helper.HelperClass;
import com.model.Password;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserPasswordAction extends ActionSupport {
	
	private Users uModel = new Users();
	private Password pModel = new Password();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(uModel.getUsername());
		System.out.println(pModel.getCurrentPassword());
		System.out.println(pModel.getNewPassword());
		System.out.println(pModel.getNewPassword_Verify());
		
		//Check muna kung tama yung currentPassword sa database then kung tama..
		
		if(true){
			if(HelperClass.verify_password(pModel.getNewPassword(), pModel.getNewPassword_Verify())){
				pModel.setPassword(HelperClass.encrypt(pModel.getNewPassword()));
				//Add now to database..
				return SUCCESS;
			}
		}
		return INPUT;
		
		
		
		
	}
	
	public Users getuModel() {
		return uModel;
	}
	public void setuModel(Users uModel) {
		this.uModel = uModel;
	}
	
	public Password getpModel() {
		return pModel;
	}
	public void setpModel(Password pModel) {
		this.pModel = pModel;
	}
}
