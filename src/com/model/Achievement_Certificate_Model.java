package com.model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.DropboxService.DropBoxService;

public class Achievement_Certificate_Model {
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String Achievement_Certificate_Name;
	private String Achievement_Certificate_Url;
	
	
	private DropBoxService dbService = new DropBoxService();
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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
	
	
	public void doUpload(String serverPath) {
		
		try {
			File tempFile = new File(serverPath, fileFileName);
			FileUtils.copyFile(file, tempFile);
			String url = dbService.upload_Achievement_Certificate(tempFile.getPath());
			Achievement_Certificate_Url = url;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
