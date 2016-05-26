package com.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.helper.HelperClass;
import com.helper.Utilities;
import com.model.AuditLog;
import com.model.Feedback;
import com.model.Password;
import com.model.Users;

public class GenericHelper {


	public boolean resetPassword(Users user){

		Session session = null;
		Transaction trans = null;
		try {
			session = HibernateFactory.getSession().openSession();
			trans = session.beginTransaction();
			Integer userID = (Integer) session.createSQLQuery("Select UserID from Users where username = :un")
					.setParameter("un", HelperClass.decrypt(user.getUsername()))
					.uniqueResult(); 
			if(userID == null){
				return false;
			}else{
				Password pObj = (Password) session.get(Password.class, userID);
				String password = user.getUsername().length() > 5 ? 
						HelperClass.encrypt(Utilities.password) : HelperClass.encrypt(Utilities.password1);
						pObj.setPassword(password);
						session.update(pObj);
						trans.commit();
						return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			if(trans != null){
				trans.rollback();
			}
			return false;
		} finally{
			session.close();
		}
	}

	public void AddAuditLog(AuditLog auditLog){

		Session session = null;
		Transaction trans = null;

		try {
			session = HibernateFactory.getSession().openSession();
			trans = session.beginTransaction();
			session.save(auditLog);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(trans != null)
				trans.rollback();
		} finally {
			session.close();
		}
	}

	public List<AuditLog> viewMyActivityLog(Users user){

		Session session = null;
		Transaction trans = null;
		List<AuditLog> list = new ArrayList<AuditLog>();
		LoginHelper l_helper = new LoginHelper();

		try {
			session = HibernateFactory.getSession().openSession();
			trans = session.beginTransaction();

			int id = l_helper.getID(user.getUsername());
			System.out.println(id);

			Query query = session.createQuery("from AuditLog where userID = :uID")
					.setParameter("uID", id);
			list = query.list();
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(trans != null)
				trans.rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	public void saveFeedback(Feedback feedback){
		
		Session session = null;
		Transaction trans = null;
		try {
			session = HibernateFactory.getSession().openSession();
			trans = session.beginTransaction();
			session.save(feedback);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	

}	
