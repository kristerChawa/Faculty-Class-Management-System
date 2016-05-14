package com.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.helper.Utilities;

@Entity
public class Projects {

	public Projects(){}

	public Projects(String projectName,String date,ProfessorProfile professorProfile){
		setProjectName(projectName);
		setProjectDate(date); 
		setProfessorProfile(professorProfile);
		setTimestamp(LocalDateTime.now().format(Utilities.formatter).toString());
	}

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
	
	@Column(name="Timestamp")
	private String timestamp;

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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
