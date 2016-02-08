package com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Achievements {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AID")
	private int aID;

	
	@Column(length=30,name="Certifications")
	private String certifications;
	@Column(length=300,name="AttachmentsUrl")
	private String attachmentUrl;
	
	@ManyToOne
	@JoinColumn(name="PPID")
	private ProfessorProfile ProfessorProfile;
	
	
	
	
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
	
	public String getCertifications() {
		return certifications;
	}
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
	public Achievements(){}
	
	
	public Achievements(String certifications,String attachmentUrl)
	{
		setAttachmentUrl(attachmentUrl);
		setCertifications(certifications);
		//setProfessorProfile(professorProfile);
	}
	
}
