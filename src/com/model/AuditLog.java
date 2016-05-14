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
public class AuditLog {
	
	public AuditLog() {
		// TODO Auto-generated constructor stub
	}
	
	public AuditLog(String auditAction, String auditType, Users user) {
		// TODO Auto-generated constructor stub
		setAuditAction(auditAction);
		setAuditType(auditType);
		setUsers(user);
		setTimestamp(LocalDateTime.now().format(Utilities.formatter).toString());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="AuditAction")
	private String AuditAction;
	
	@Column(name="AuditType")
	private String AuditType;
	
	@Column(name="Timestamp")
	private String timestamp;
	
	@ManyToOne
	@JoinColumn(name="UserID")
	private Users users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuditAction() {
		return AuditAction;
	}

	public void setAuditAction(String auditAction) {
		AuditAction = auditAction;
	}

	public String getAuditType() {
		return AuditType;
	}

	public void setAuditType(String auditType) {
		AuditType = auditType;
	}
	
	

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	
}
