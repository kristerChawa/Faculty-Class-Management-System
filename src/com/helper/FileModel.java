package com.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.DropboxService.DropBoxService;

public class FileModel {
 
	private File file;
	private String fileFileName; //auto map struts
	private String fileContentType; //auto map struts
	private String Url;
	private String dataName;
	private Object response;
	private String request;
		
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
	public String getUrl() {
		return Url;
	}
	public void setUrl(String Url) {
		this.Url = Url;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
	public boolean hasUploadedAchievements(String serverPath){
		try {
			File tempFile = new File(serverPath, fileFileName);

			if(HelperClass.isFileContentTypeImage(fileContentType) 
					&& HelperClass.isFileSizeCorrect(tempFile)){

				FileUtils.copyFile(file, tempFile);
				this.Url = dbService.upload_Achievement_Certificate(tempFile.getPath());

				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return false;
	}
	
	public boolean hasUploadedResume(String serverPath) {

		try{
			File tempFile = new File(serverPath, fileFileName);
			
			if(HelperClass.isFileContentTypeResume(fileFileName, fileContentType)){
				System.out.println(fileContentType);
				FileUtils.copyFile(file, tempFile);
				String Url = dbService.uploadResume(tempFile.getPath());
				this.Url = Url;
				
				return true;
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean hasUploadedUserImage(String serverPath){
		try {
			File tempFile = new File(serverPath, fileFileName);

			if(HelperClass.isFileContentTypeImage(fileContentType) 
					&& HelperClass.isFileSizeCorrect(tempFile)){

				FileUtils.copyFile(file, tempFile);
				this.Url = dbService.uploadUserImage(tempFile.getPath());

				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return false;
	}
}
