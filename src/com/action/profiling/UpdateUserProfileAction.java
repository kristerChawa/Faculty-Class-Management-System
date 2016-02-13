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
		
		
		System.out.println(uModel.getUsername() + uModel.getFirstName());
//		subjects.forEach(i -> System.out.println(i.getCourseCode()));
		
		
		//Before to add the uModel use the helperClass for the username.
		//Subjects are already retrieved. 
		ProfilingHelper profiling_helper = new ProfilingHelper();
		ProfessorProfile pProf_helper = new ProfessorProfile();
		Expertise expertise = null;
		
		
		//Update Name
		uModel.setUsername(HelperClass.CreateUsername(uModel.getFirstName(), uModel.getLastName()));
		profiling_helper.updateUserProfile(uModel);
		
		
		//Hindi ko ma add yung subjects sa expertise haha
		pProf_helper.setUsers(uModel);

		
		for(Subjects subj : subjects){
			expertise = new Expertise();
			expertise.setSubjects(subj);
			expertise.setProfessorProfile(pProf_helper);
			profiling_helper.addExpertise(expertise);
		}
		
		
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
