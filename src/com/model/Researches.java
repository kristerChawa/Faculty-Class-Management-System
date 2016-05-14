package com.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.helper.Utilities;

@Entity
public class Researches {

	public Researches(){}

	public Researches(String researchName,String date,ProfessorProfile professorProfile){
		setResearchName(researchName);
		setResearchDate(date);
		setProfessorProfile(professorProfile);
		setTimestamp(LocalDateTime.now().format(Utilities.formatter).toString());
	}


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

	@Column(name="Timestamp")
	private String timestamp;

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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
