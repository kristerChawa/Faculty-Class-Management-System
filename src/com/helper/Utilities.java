package com.helper;

import java.time.format.DateTimeFormatter;

public interface Utilities {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
	
	String user_sessionName = "usersModel";
	String adminUsername = "developer01",
			adminPassword = "developer01",
			adminAccountType = "developer";
	
	String secUsername = "secretary01",
			secPassword = "secretary01",
			secAccountType = "secretary";
	
	String PROFESSOR = "Professor",
			CHAIRPERSON = "Chairperson",
			STUDENT = "Student",
			SECRETARY = "Secretary",
			ACADEMIC_ADIVSER = "Academic Adviser";
	
	String defaultImage = "resources/img/avatar.png";
	
	String password = "qwerty",	
			password1 = "changeme";
	
	long maximumFileSizeMB = 1000000; //1 mb = 1000000 byte
	
	String achievementType = "achievement",
			resumeType = "resume",
			projectType = "project",
			researchType = "research";
	
	String imageType = "image",
			documentType = "document";
	
}
