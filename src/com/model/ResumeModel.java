package com.model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.DropboxService.DropBoxService;

public class ResumeModel {

	private File file;
	private String fileFileName;
	private String fileContentType;
	private String resumeUrl;
	
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
	public String getResumeUrl() {
		return resumeUrl;
	}
	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}
	public void doUpload(String serverPath) {

<<<<<<< 31e3ba1d682ad4144441b0b70d9ab393d78d4f87
		try
		{
=======
		try {
>>>>>>> Resume
			File tempFile = new File(serverPath, fileFileName);
			FileUtils.copyFile(file, tempFile);
			String url = dbService.uploadResume(tempFile.getPath());
			resumeUrl = url;
<<<<<<< 31e3ba1d682ad4144441b0b70d9ab393d78d4f87
			
		} 
		catch (IOException e) 
		{
=======
		} catch (IOException e) {
>>>>>>> Resume
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}



}
