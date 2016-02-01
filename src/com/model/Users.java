package com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="Users")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserID")
	private int userID;
	
	@Column(length=25,name="IDNo")
	private String idNo;
	@Column(length=25,name="FirstName")
	private String firstName;
	@Column(length=25,name="LastName")
	private String lastName;
	@Column(length=25,name="UserName")
	private String username;
	

	@OneToMany(mappedBy="users")
	private List<AccountType> accountType;
	
	@OneToMany(mappedBy="users")
	private List<Password> password;
	
	
	@OneToMany(mappedBy="users")
	private List<ProfessorProfile> professorProfile;
	
	@OneToMany(mappedBy="users")
	private List<Classlist> classlist;
	
	@OneToMany(mappedBy="users")
	private List<Attendance> attendance;
	
	
	
	public List<Attendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
	public List<Classlist> getClasslist() {
		return classlist;
	}
	public void setClasslist(List<Classlist> classlist) {
		this.classlist = classlist;
	}
	public List<ProfessorProfile> getProfessorProfile() {
		return professorProfile;
	}
	public void setProfessorProfile(List<ProfessorProfile> professorProfile) {
		this.professorProfile = professorProfile;
	}
	public List<Password> getPassword() {
		return password;
	}
	public void setPassword(List<Password> password) {
		this.password = password;
	}
	public List<AccountType> getAccountType() {
		return accountType;
	}
	public void setAccountType(List<AccountType> accountType) {
		this.accountType = accountType;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

	public Users(){}
	
	public Users(String idNo,String firstName,String lastName,String userName)
	{
		setIdNo(idNo);
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(userName);
	}
	

}
