package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="Password")
public class Password {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PassID")
	private int passID;
	
	@Column(name="Password",insertable=false, updatable = true, nullable = false,
			columnDefinition="varchar(25) default 'qwerty'")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="UserID")
	private Users users;

	public int getPassID() {
		return passID;
	}

	public void setPassID(int passID) {
		this.passID = passID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Password(){}
	
	public Password(Users users)
	{
		//setPassword(password);
		setUsers(users);
	}
	
	
}
