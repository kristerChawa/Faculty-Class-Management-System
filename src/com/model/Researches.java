package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Researches {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RID")
	private int rID;
	
	@ManyToOne
	@JoinColumn(name="PPID")
	private ProfessorProfile professorProfile;
	
	@Column(name="ResearchName")
	private String researchName;
	
	@Column(name="Date")
	private String researchDate;
	
	
	
	
	public String getResearchDate() {
		return researchDate;
	}
	public void setResearchDate(String researchDate) {
		this.researchDate = researchDate;
	}
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}
	public ProfessorProfile getProfessorProfile() {
		return professorProfile;
	}
	public void setProfessorProfile(ProfessorProfile professorProfile) {
		this.professorProfile = professorProfile;
	}
	public String getResearchName() {
		return researchName;
	}
	public void setResearchName(String researchName) {
		this.researchName = researchName;
	}

	
	public Researches(){}
	
	public Researches(String researchName,String date,ProfessorProfile professorProfile)
	{
		setResearchName(researchName);
		setResearchDate(date);
		setProfessorProfile(professorProfile);
	}
}
