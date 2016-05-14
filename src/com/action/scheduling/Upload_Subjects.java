package com.action.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.GenericHelper;
import com.HibernateUtil.SchedulingHelper;
import com.helper.AuditLogUtil;
import com.helper.FileModel;
import com.helper.SchedulingHelperClass;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Expertise;
import com.model.Schedule;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Upload_Subjects extends ActionSupport implements ModelDriven<FileModel>, SessionAware {
	
	private FileModel fModel = new FileModel();
	private Map<String, Object> userSession;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		SchedulingHelper s_helper = new SchedulingHelper();
		List<Schedule> schedList = new ArrayList<Schedule>();
		List<Expertise> expList = new ArrayList<Expertise>();
		Users uModel = (Users)userSession.get(Utilities.user_sessionName);
		GenericHelper g_helper = new GenericHelper();
		
		schedList = SchedulingHelperClass
					.readUploadedSubjects(fModel);
		
		if( !schedList.isEmpty()){
			//init
			s_helper.renewScheduleTable();
		
			for(Schedule sObj : schedList){
				if(s_helper.is_ISSubject(sObj.getSubjects())){
					s_helper.addSchedule(sObj);
					expList.addAll(s_helper.getTableExpertise(sObj));
				}
			}
		
			fModel.setResponse(expList);
			userSession.put("expList", expList);
			AuditLog auditLog = new AuditLog(AuditLogUtil.uploadAction, AuditLogUtil.subjectsType, uModel);
			g_helper.AddAuditLog(auditLog);
			
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	@Override
	public FileModel getModel() {
		// TODO Auto-generated method stub
		return fModel;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.userSession = session;
	}
	
}
