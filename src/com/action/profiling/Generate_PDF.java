package com.action.profiling;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.helper.PDFGenerator;
import com.helper.Utilities;
import com.model.ProfessorProfile;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Generate_PDF extends ActionSupport implements SessionAware {
	
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ProfessorProfile professorProfile = new ProfessorProfile();
		Users uModel = (Users) userSession.get(Utilities.user_sessionName);
		professorProfile.setPpID(uModel.getUserID());
		
		PDFGenerator pdf = new PDFGenerator(uModel, professorProfile);
		pdf.generateProfessorPDF();
		
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
