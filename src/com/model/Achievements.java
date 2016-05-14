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
public class Achievements {
	
	public Achievements(){}
	
	
	public Achievements(String achievement_Certificate_Name, String setAchievement_Certificate_Url,ProfessorProfile professorProfile){
		setAchievement_Certificate_Name(achievement_Certificate_Name);
		setAchievement_Certificate_Url(setAchievement_Certificate_Url);
		setProfessorProfile(professorProfile);
		setTimestamp(LocalDateTime.now().format(Utilities.formatter).toString());
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AID")
	private int aID;

	
	@Column(length=30,name="Achievement_Certificate_Name")
	private String Achievement_Certificate_Name;
	@Column(length=300,name="Achievement_Certificate_Url")
	private String Achievement_Certificate_Url;
	
	@ManyToOne
	@JoinColumn(name="PPID")
	private ProfessorProfile ProfessorProfile;
	
	@Column(name="Timestamp")
	private String timestamp;
	
	public ProfessorProfile getProfessorProfile() {
		return ProfessorProfile;
	}
	public void setProfessorProfile(ProfessorProfile professorProfile) {
		this.ProfessorProfile = professorProfile;
	}
	public int getaID() {
		return aID;
	}
	public void setaID(int aID) {
		this.aID = aID;
	}
	
	public String getAchievement_Certificate_Name() {
		return Achievement_Certificate_Name;
	}
	public void setAchievement_Certificate_Name(String achievement_Certificate_Name) {
		Achievement_Certificate_Name = achievement_Certificate_Name;
	}
	public String getAchievement_Certificate_Url() {
		return Achievement_Certificate_Url;
	}
	public void setAchievement_Certificate_Url(String achievement_Certificate_Url) {
		Achievement_Certificate_Url = achievement_Certificate_Url;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
}
