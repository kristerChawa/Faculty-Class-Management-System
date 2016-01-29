package com.DropboxService;

import java.util.List;

import com.dropbox.core.v2.Files.Metadata;

public interface DropboxApi {
	
	final String dropBoxToken = "nuVzW0pOwgIAAAAAAAAAZs2Kt5n3C5JgW8-UoCsS0wvqk8HC3gZlCPZhTHvKr3Me",
	appKey = "wqsnwjh11qeavi5",
	appSecret = "qtiwme041b1otfk",
	appName = "Faculty Class Management System",
	folderPath = "/Faculty Class Management System/",
	locale = "en_US";
	
	
	final String achievements_certificates_path = folderPath + "Achievements&Certifications/";
<<<<<<< 31e3ba1d682ad4144441b0b70d9ab393d78d4f87
<<<<<<< 2793fc1397d801b36b82b260abd4fc507180dc31
	final String resume_path = folderPath + "Resume/";
=======
>>>>>>> missing db
=======
	final String resume_path = folderPath + "Resume/";
>>>>>>> Resume
	
	
	/**
	 * 
	 * Upload a file to DropBox then return the url of the uploaded file as String.
	 * @return
	 */
	public String upload_Achievement_Certificate(String filepath);
<<<<<<< 31e3ba1d682ad4144441b0b70d9ab393d78d4f87
<<<<<<< 2793fc1397d801b36b82b260abd4fc507180dc31
	public String uploadResume(String filepath);
=======
//	public String uploadResume(String filepath);
>>>>>>> missing db
=======
	public String uploadResume(String filepath);
>>>>>>> Resume
	public List<Metadata> listFolders();
	
	
	

}
