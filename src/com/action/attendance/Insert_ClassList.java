package com.action.attendance;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.HibernateUtil.AttendanceHelper;
import com.HibernateUtil.GenericHelper;
import com.helper.AttendanceHelperClass;
import com.helper.AuditLogUtil;
import com.helper.FileModel;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Classlist;
import com.model.FacultyAssign;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Insert_ClassList extends ActionSupport implements ModelDriven<FileModel>, SessionAware {
	
	private FileModel fModel = new FileModel();
	private Map<String, Object> userSession;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		AttendanceHelperClass a_helperClass = new AttendanceHelperClass();
		AttendanceHelper a_helper = new AttendanceHelper();
		FacultyAssign facAssign = new FacultyAssign();
		List<Users> uList = new ArrayList<Users>();
		GenericHelper g_helper = new GenericHelper();
		Users uModel = (Users) userSession.get(Utilities.user_sessionName);
		
		try {
			String subject = fModel.getRequest().split(",")[0],
					section = fModel.getRequest().split(",")[1];
			
		    int assignID = a_helper.getAssignID(subject, section);
		    facAssign.setAssignID(assignID);
		    
			uList = a_helperClass.readUploadedClasslist(fModel);
			if( !uList.isEmpty()){
				uList.forEach(i -> {
					if(a_helper.isStudentNotAdded(i)){
						a_helperClass.Student(i);
					}
					a_helper.addClassList(new Classlist(i, facAssign));
				});
				
				AuditLog auditLog = new AuditLog(AuditLogUtil.addAction, AuditLogUtil.classListType, uModel);
				g_helper.AddAuditLog(auditLog);
				
				return SUCCESS;
			}else{
				return INPUT;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

