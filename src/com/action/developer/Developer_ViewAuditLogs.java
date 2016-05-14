package com.action.developer;

import java.util.ArrayList;
import java.util.List;

import com.HibernateUtil.DeveloperHelper;
import com.model.AuditLog;
import com.opensymphony.xwork2.ActionSupport;

public class Developer_ViewAuditLogs extends ActionSupport {
	
	private List<AuditLog> auditLogList = new ArrayList<AuditLog>();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		DeveloperHelper d_helper = new DeveloperHelper();
		try {
			auditLogList = d_helper.viewAuditLogs();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return INPUT;
		}
		
	}
	
	public List<AuditLog> getAuditLogList() {
		return auditLogList;
	}
}
