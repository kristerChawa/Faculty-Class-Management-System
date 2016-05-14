package com.action.attendance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.AttendanceHelper;
import com.HibernateUtil.GenericHelper;
import com.helper.AuditLogUtil;
import com.helper.Utilities;
import com.model.Attendance;
import com.model.AuditLog;
import com.model.Schedule;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class Save_Attendance extends ActionSupport implements SessionAware {
	
	private List<Users> absentList = new ArrayList<Users>();
	private List<Users> lateList = new ArrayList<Users>();
	private List<Users> presentList = new ArrayList<Users>();
	private Schedule schedObj = new Schedule();
	private String date;
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
	
		// TODO Auto-generated method stub
		try {
			AttendanceHelper a_helper = new AttendanceHelper();
			Attendance aObj = new Attendance();
			GenericHelper g_helper = new GenericHelper();
			Users uModel = (Users) userSession.get(Utilities.user_sessionName);
			aObj.setDate(date);
			int aID = a_helper.getAssignID(schedObj.getSubjects().getCourseCode(), schedObj.getSection());
		
			
			absentList.forEach(i -> {
				aObj.setAttendance("A");
				a_helper.addAttendance(aID, i, aObj);
			});
			
			lateList.forEach(i -> {
				aObj.setAttendance("L");
				a_helper.addAttendance(aID, i, aObj);
			});
			
			presentList.forEach(i -> {
				aObj.setAttendance("P");
				a_helper.addAttendance(aID, i, aObj);
			});
			
			AuditLog auditLog = new AuditLog(AuditLogUtil.saveAction, AuditLogUtil.attendanceType, uModel);
			g_helper.AddAuditLog(auditLog);
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return INPUT;
		}
	}
	
	public void setAbsentList(List<Users> absentList) {
		this.absentList = absentList;
	}
	public void setLateList(List<Users> lateList) {
		this.lateList = lateList;
	}
	public void setPresentList(List<Users> presentList) {
		this.presentList = presentList;
	}
	
	public void setSchedObj(Schedule schedObj) {
		this.schedObj = schedObj;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
}
