package com.action.attendance;

import java.util.ArrayList;
import java.util.List;

import com.HibernateUtil.AttendanceHelper;
import com.model.Attendance;
import com.model.Schedule;
import com.opensymphony.xwork2.ActionSupport;

public class View_Attendance extends ActionSupport{
	
	private Schedule schedObj = new Schedule();
	private List<Attendance> aList = new ArrayList<Attendance>();
	private String date;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		AttendanceHelper a_helper = new AttendanceHelper();
		Attendance attendance = new Attendance();
		
		try {
			attendance.setDate(date);
			int assignID = a_helper.getAssignID(schedObj.getSubjects().getCourseCode(), schedObj.getSection());
			aList = a_helper.viewAttendance(attendance.getDate(), assignID);

			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public void setSchedObj(Schedule schedObj) {
		this.schedObj = schedObj;
	}
	
	public List<Attendance> getaList() {
		return aList;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
