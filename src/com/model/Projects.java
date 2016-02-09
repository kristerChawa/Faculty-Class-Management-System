package com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projects {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int prID;
	
	@Column(name="ProjectName")
	private String projectName;
	
	@Column(name="Date")
	private String projectDate;
	
	@ManyToOne
	@JoinColumn(name="PPID")
	private ProfessorProfile professorProfile;
	
	
	
	public ProfessorProfile getProfessorProfile() {
		return professorProfile;
	}
	public void setProfessorProfile(ProfessorProfile professorProfile) {
		this.professorProfile = professorProfile;
	}
	public int getPrID() {
		return prID;
	}
	public void setPrID(int prID) {
		this.prID = prID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(String projectDate) {
		this.projectDate = projectDate;
	}
	
	public Projects(){}
	
	public Projects(String projectName,String date,ProfessorProfile professorProfile)
	{
		setProjectName(projectName);
		setProjectDate(date); 
		setProfessorProfile(professorProfile);
	}
	
	
}
