package com.helper;

import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.model.Email;
import com.model.Feedback;
import com.model.Users;

public class SendEmail {

	public static void sendForgotPassword(Users uObj, String path){
		//if sending yourself a gmail, make sure you  turn on your gmail authentication
		try{
			Email email = new Email();

			//Not Working
			email.setFromAddress(EmailConfig.getSystemEmailAddress());
			email.setFromPassword(EmailConfig.getSystemEmailPassword());				    
			email.setToAddress("fcmsteamavengers@gmail.com");

			Properties emailProperties = EmailConfig.emailProperties();

			Session session = Session.getInstance(emailProperties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							email.getFromAddress(), email.getFromPassword());
				}
			});

			try{
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email.getFromAddress(), "Faculty Class Management System"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email.getToAddress()));
				message.setSubject("Change Password Request");
				message.setText("Welcome, " + uObj.getUsername() + "! To reset your password kindly click this link: " + createConfirmationLink(path, uObj));

				Transport transport = session.getTransport("smtp");
				transport.connect("smtp.gmail.com", email.getFromAddress(), email.getFromPassword());

				message.saveChanges();
				Transport.send(message);
				transport.close();

			}catch(MessagingException e){
				throw new RuntimeException(e);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static String createConfirmationLink(String path, Users uObj){

		return path + "Reset_Password?username=" + HelperClass.encrypt(uObj.getUsername());
	}
	
	public void sendFeedback(Feedback feedback){
		try{
			Email email = new Email();

			//Not Working
			email.setFromAddress(EmailConfig.getSystemEmailAddress());
			email.setFromPassword(EmailConfig.getSystemEmailPassword());				    
			email.setToAddress("fcmsteamavengers@gmail.com");

			Properties emailProperties = EmailConfig.emailProperties();

			Session session = Session.getInstance(emailProperties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							email.getFromAddress(), email.getFromPassword());
				}
			});

			try{
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email.getFromAddress(), "Faculty Class Management System"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email.getToAddress()));
				message.setSubject("System feedback");
				String msgText = "Our user <b>" + feedback.getUser().getLastName() + ", " + feedback.getUser().getFirstName() + "</b> has send a feedback "
						+ "at " + LocalDateTime.now() + ". <br/> Message content: <br/><b>" + feedback.getMessage() + "</b>";
				
				message.setContent(msgText, "text/html");
				
				Transport transport = session.getTransport("smtp");
				transport.connect(EmailConfig.getSystemEmailHost(), email.getFromAddress(), email.getFromPassword());

				message.saveChanges();
				Transport.send(message);
				transport.close();

			}catch(MessagingException e){
				throw new RuntimeException(e);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

