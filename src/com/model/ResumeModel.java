package com.model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.DropboxService.DropBoxService;

public class ResumeModel {

	private File file;
	private String fileFileName;
	private String fileContentType;
	private String url;
	
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
	public String geturl() {
		return url;
	}
	public void seturl(String url) {
		this.url = url;
	}
	public void doUpload(String serverPath) {

		try
		{
			File tempFile = new File(serverPath, fileFileName);
			FileUtils.copyFile(file, tempFile);
			String url = dbService.uploadResume(tempFile.getPath());
			url = url;
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}



}
