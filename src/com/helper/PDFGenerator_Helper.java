package com.helper;

import java.net.URL;
import java.util.List;

import com.HibernateUtil.ProfilingHelper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.model.Achievements;
import com.model.ProfessorProfile;
import com.model.Projects;
import com.model.Users;

public class PDFGenerator_Helper {

	static Font BodyName = null,
			link = null,
			date = null,
			title = null,
			subContent = null;
	static{
		BodyName = new Font(FontFamily.HELVETICA , 16, Font.BOLD);
		link = new Font(FontFamily.HELVETICA, 8);
		date = new Font(FontFamily.HELVETICA, 8);
		title = new Font(FontFamily.HELVETICA  , 12, Font.BOLD);
		subContent = new Font(FontFamily.HELVETICA , 11, Font.BOLD);       
		link.setColor(BaseColor.GREEN.darker());
		date.setColor(BaseColor.GREEN.darker());
		subContent.setColor(BaseColor.GREEN.darker());
	}

	private static ProfilingHelper p_helper = new ProfilingHelper();
	
	public static Image getUserImage(Users user){
		Image image = null;
		try{
			image = Image.getInstance(new URL(user.getPictureUrl()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return image;
	}

	public static String getUserName(Users user){
		return user.getFirstName() + " " + user.getLastName();
	}

	public static PdfPTable getAchievements(ProfessorProfile profile){

		PdfPTable achievementsTable = new PdfPTable(5);
		//		    image1.scaleAbsolute(50f, 50f);
		List<Achievements> aList = p_helper.viewAchievements(profile);

		for(Achievements achieve : aList){
			PdfPCell achievementCell = new PdfPCell();
			//	        	achievementCell.addElement(image1);
			achievementCell.addElement(new Paragraph(achieve.getAchievement_Certificate_Name().toUpperCase(), title));
			achievementCell.addElement(new Paragraph(achieve.getAchievement_Certificate_Url(), link));
			achievementCell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
			achievementCell.setBorderColor(BaseColor.WHITE);
			achievementCell.setPaddingTop(40);
			achievementsTable.addCell(achievementCell);   
		}

		achievementsTable.getDefaultCell().setBorder(0);
		achievementsTable.completeRow();	 
		return achievementsTable;
	}

	public static PdfPTable getProjects(ProfessorProfile profile){

		List<Projects> pList = p_helper.viewProjects(profile);

		PdfPTable projectsContentTable = new PdfPTable(1);

		for(Projects projects : pList){
			PdfPCell projectsCell = new PdfPCell();
			projectsCell.addElement(new Paragraph("\u2022 " + projects.getProjectName(), title));
			projectsCell.addElement(new Paragraph("      ["+projects.getProjectDate()+"]", date));
			projectsCell.setBorderColor(BaseColor.WHITE);
			projectsCell.setPaddingBottom(20f);
			projectsContentTable.addCell(projectsCell);
		}


		return projectsContentTable;
	}

}
