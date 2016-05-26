package com.action.attendance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.HibernateUtil.AttendanceHelper;
import com.model.Attendance;
import com.model.Schedule;
import com.opensymphony.xwork2.ActionSupport;

public class View_AttendanceDates extends ActionSupport {

	private Schedule schedObj = new Schedule();
	private Set<Attendance> aList = new HashSet<Attendance>();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		AttendanceHelper a_helper = new AttendanceHelper();
		try {
			int assignID = a_helper.getAssignID(schedObj.getSubjects().getCourseCode(), schedObj.getSection());
			aList = a_helper.viewAttendanceDates(assignID);
			
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
	
	public Schedule getSchedObj() {
		return schedObj;
	}
	
	public void setaList(Set<Attendance> aList) {
		this.aList = aList;
	}
	public Set<Attendance> getaList() {
		return aList;
	}
	
}
