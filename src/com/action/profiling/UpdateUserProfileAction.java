package com.action.profiling;

import java.util.ArrayList;
import java.util.List;

import com.HibernateUtil.ProfilingHelper;
import com.helper.HelperClass;
import com.model.Expertise;
import com.model.ProfessorProfile;
import com.model.Subjects;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserProfileAction extends ActionSupport {
	
	private Users uModel = new Users();
	private List<Subjects> subjects = new ArrayList<Subjects>();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(uModel.getUsername() + uModel.getFirstName());
		//Before to add the uModel use the helperClass for the username.
		//Subjects are already retrieved. 
		ProfilingHelper profiling_helper = new ProfilingHelper();
		ProfessorProfile pProf_helper = new ProfessorProfile();
		Expertise expertise = new Expertise();
		
		
		
		uModel.setUsername(HelperClass.CreateUsername(uModel.getFirstName(), uModel.getLastName()));
		
		profiling_helper.updateUserProfile(uModel);
//		pProf_helper.setUsers(uModel);
//		subjects.forEach(i -> expertise.setSubjects(i));
//		expertise.setProfessorProfile(pProf_helper);
//		profiling_helper.addExpertise(expertise);
		
		
		return SUCCESS;
	}
	
	
	public Users getuModel() {
		return uModel;
	}
	public void setuModel(Users uModel) {
		this.uModel = uModel;
	}
	
	public List<Subjects> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}
}
