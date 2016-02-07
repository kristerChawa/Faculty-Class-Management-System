package com.action.developer;

import java.util.ArrayList;
import java.util.List;

import com.HibernateUtil.DeveloperHelper;
import com.model.AccountType;
import com.model.Password;
import com.model.ProfessorProfile;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class DeveloperAction extends ActionSupport {
	
	private List<Users> users = new ArrayList<Users>();
	
	
	@Override
	public String execute() throws Exception {
		
		DeveloperHelper session_Helper = new DeveloperHelper();	
		try{
			for (Users uModel : users)
			{ 
				
				//add user
				session_Helper.addUser(uModel);
				
				//add password
				Password password = new Password(uModel);
				session_Helper.addPassword(password);
				
				for(AccountType acType : uModel.getAccountType()){
					AccountType accountType = new AccountType(acType.getAccountType(), uModel);
					session_Helper.addAccountType(accountType);
				}
				
				
				
			//	add professorprofile
				ProfessorProfile professorProfile=new ProfessorProfile(uModel);
				session_Helper.addProfessorProfile(professorProfile);
				session_Helper.viewAllProfessors();
			}
		}catch(Exception e){
			return ERROR;
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
