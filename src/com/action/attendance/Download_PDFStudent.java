package com.action.attendance;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.HibernateUtil.AttendanceHelper;
import com.HibernateUtil.GenericHelper;
import com.helper.AuditLogUtil;
import com.helper.PDFGenerator;
import com.helper.Utilities;
import com.model.Attendance;
import com.model.AuditLog;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Download_PDFStudent extends ActionSupport implements ServletContextAware, SessionAware{
	
	private int userID = 0;
	private ServletContext context;
	private InputStream inputStream;
	private String inputStreamName; 
	private Map<String, Object> userSession;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Users uModel = new Users();
		uModel.setIdNo(String.valueOf(userID));
		
		AttendanceHelper a_helper = new AttendanceHelper();
		GenericHelper g_helper = new GenericHelper();
		List<Attendance> aList = a_helper.displayAttendancetoPDF(uModel);
		
		PDFGenerator pdf = new PDFGenerator();
		context = ServletActionContext.getServletContext();
		String serverPath = context.getRealPath("/");	
		File file = new File(pdf.generateStudentPDF(serverPath, aList));
		inputStream = new FileInputStream(file);
		inputStreamName = file.getName();
		
		
		Users user = (Users) userSession.get(Utilities.user_sessionName);
		AuditLog auditLog = new AuditLog(AuditLogUtil.downloadAction, AuditLogUtil.pdfType, user);
		g_helper.AddAuditLog(auditLog);
		
		return SUCCESS;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getInputStreamName() {
		return inputStreamName;
	}

	public void setInputStreamName(String inputStreamName) {
		this.inputStreamName = inputStreamName;
	}

	public int getUserID() {
		return userID;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
	
	
}
