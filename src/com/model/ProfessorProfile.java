package com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProfessorProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PPID")
	private int ppID;
	
	@ManyToOne
	@JoinColumn(name="UserID")
	private Users users;
	
	@OneToMany(mappedBy="ProfessorProfile", fetch = FetchType.EAGER)
	private List<Achievements> achievements;

	
	@OneToMany(mappedBy="ProfessorProfile", fetch = FetchType.EAGER)
	private List<Resume> resume;
	
	@OneToMany(mappedBy="professorProfile", fetch = FetchType.EAGER)
	private List<Researches> researches;
	
	@OneToMany(mappedBy="professorProfile", fetch = FetchType.EAGER)
	private List<Projects> projects;
	
	
	@OneToMany(mappedBy="professorProfile", fetch = FetchType.EAGER)
	private List<FacultyAssign> facultyAssign;
	
	
	
	
	public List<FacultyAssign> getFacultyAssign() {
		return facultyAssign;
	}

	public void setFacultyAssign(List<FacultyAssign> facultyAssign) {
		this.facultyAssign = facultyAssign;
	}

	public List<Researches> getResearches() {
		return researches;
	}

	public void setResearches(List<Researches> researches) {
		this.researches = researches;
	}

	public List<Projects> getProjects() {
		return projects;
	}

	public void setProjects(List<Projects> projects) {
		this.projects = projects;
	}

	public List<Resume> getResume() {
		return resume;
	}

	public void setResume(List<Resume> resume) {
		this.resume = resume;
	}

	public List<Achievements> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievements> achievements) {
		this.achievements = achievements;
	}

	public int getPpID() {
		return ppID;
	}

	public void setPpID(int ppID) {
		this.ppID = ppID;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	public ProfessorProfile(){}
	
	
	public ProfessorProfile(Users users)
	{
		setUsers(users);
	}
}
