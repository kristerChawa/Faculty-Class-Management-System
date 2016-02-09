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
	final String resume_path = folderPath + "Resume/";
	final String userImage_path = folderPath + "UserImages/";
	
	
	/**
	 * 
	 * Upload a file to DropBox then return the url of the uploaded file as String.
	 * @return
	 */
	public String upload_Achievement_Certificate(String filepath);
	public String uploadResume(String filepath);
	public List<Metadata> listFolders();
	public String uploadUserImage(String filepath);
	
	
	

}
