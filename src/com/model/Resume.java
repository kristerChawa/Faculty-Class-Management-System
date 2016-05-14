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
public class Resume {

	public Resume(){}

	public Resume(String resumeUrl)
	{
		setResumeUrl(resumeUrl);
		//setProfessorProfile(professorProfile);
		setTimestamp(LocalDateTime.now().format(Utilities.formatter).toString());
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int RID;
	@Column(length=250,name="ResumeUrl")
	private String resumeUrl;

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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
