package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Resume {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int RID;
	@Column(length=250,name="ResumeUrl")
	private String resumeUrl;
	
	@ManyToOne
	@JoinColumn(name="PPID")
	private ProfessorProfile ProfessorProfile;
	
	public ProfessorProfile getProfessorProfile() {
		return ProfessorProfile;
	}
	public void setProfessorProfile(ProfessorProfile professorProfile) {
		this.ProfessorProfile = professorProfile;
	}
	public int getRID() {
		return RID;
	}
	public void setRID(int rID) {
		RID = rID;
	}
	public String getResumeUrl() {
		return resumeUrl;
	}
	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}
	
	public Resume(){}
	
	public Resume(String resumeUrl,ProfessorProfile professorProfile)
	{
		setResumeUrl(resumeUrl);
		setProfessorProfile(professorProfile);
	}

}
