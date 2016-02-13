package com.helper;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Achievements;
import com.model.ProfessorProfile;
import com.model.Projects;
import com.model.Researches;
import com.model.Users;

public class PDFGenerator 
{
	private ProfessorProfile professorProfile = new ProfessorProfile();
	private Users user = new Users();
	
	public PDFGenerator(Users user, ProfessorProfile profile){
		setProfessorProfile(profile);
		setUser(user);
	}
	
	public ProfessorProfile getProfessorProfile() {
		return professorProfile;
	}
	public void setProfessorProfile(ProfessorProfile professorProfile) {
		this.professorProfile = professorProfile;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

		public void generateProfessorPDF()
		{
			  try
			  {
			    	Document document = new Document();
			        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jm\\Downloads\\Professor_Profile.pdf"));
			        document.open();
			        
			        float[] headerColumnWidths = {1f, 2f};
				       
			        PdfPTable table = new PdfPTable(2);
			        table.setWidths(headerColumnWidths);
			        table.setWidthPercentage(100); //Width 100%
			        table.setSpacingBefore(10f); //Space before table
			        table.setSpacingAfter(10f); //Space after table

			        Font headerName = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
			        Font subheader = new Font(FontFamily.HELVETICA, 14);
			        subheader.setColor(BaseColor.GREEN.darker());
			     
			       
			        
			        //PROFILE PIC
			        Image image1 = Image.getInstance("C:\\Users\\Jm\\Desktop\\Files\\Java\\Workspace_EclipseMars\\Faculty-Class-Management-System\\web\\resources\\img\\faculty_mock\\Sir buboy.jpg");
			        //Fixed Positioning
			        image1.setAbsolutePosition(70f, 700f);
			        //Scale to new height and new width of image
			        image1.scaleAbsolute(150, 150);
			        
			        //ISP LOGO
			        Image image2 = Image.getInstance("C:\\Users\\Jm\\Desktop\\Files\\Java\\Workspace_EclipseMars\\Faculty-Class-Management-System\\web\\resources\\img\\faculty_mock\\Sir buboy.jpg");
			        //Fixed Positioning
			        image2.setAbsolutePosition(70f, 700f);
			        //Scale to new height and new width of image
			        image2.scaleAbsolute(50, 50);
			        
			       //SMIT LOGO
			        Image image3 = Image.getInstance("C:\\Users\\Jm\\Desktop\\Files\\Java\\Workspace_EclipseMars\\Faculty-Class-Management-System\\web\\resources\\img\\faculty_mock\\Sir buboy.jpg");
			        
			        //Fixed Positioning
			        image3.setAbsolutePosition(280f, 665f);
			        //Scale to new height and new width of image
			        image3.scaleAbsolute(70, 50);
			        
			        image1.scaleAbsolute(150, 150);
			        PdfPCell headerCell1 = new PdfPCell(image1);
			        headerCell1.setBorderColor(BaseColor.WHITE);
			        headerCell1.setPaddingLeft(10);
			        headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			        headerCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       
			        PdfPCell headerCell2 = new PdfPCell();
			        headerCell2.addElement(new Paragraph(PDFGenerator_Helper.getUserName(user), headerName));
			        headerCell2.addElement(new Paragraph("Information Systems Program Faculty Member", subheader));
			        headerCell2.addElement(new Paragraph(" "));
			        headerCell2.addElement(image2);
			        document.add(image3);
			        headerCell2.setBorderColor(BaseColor.WHITE);      
			        headerCell2.setPaddingLeft(10);

			        table.addCell(headerCell1);
			        table.addCell(headerCell2);
			      
				    document.add(table);
				    document.add(new Paragraph(" "));
				    document.add(new Paragraph(" "));
			
			        Font BodyName = new Font(FontFamily.HELVETICA , 16, Font.BOLD);
			        Font link = new Font(FontFamily.HELVETICA, 8);
			        Font date = new Font(FontFamily.HELVETICA, 8);
			        Font title = new Font(FontFamily.HELVETICA  , 12, Font.BOLD);
			        Font subContent = new Font(FontFamily.HELVETICA , 11, Font.BOLD);       
			        link.setColor(BaseColor.GREEN.darker());
			        date.setColor(BaseColor.GREEN.darker());
			        subContent.setColor(BaseColor.GREEN.darker());
			    
				    
			        //--------------ACHEIVEMENTS--------------//
			        //Achievements Header
		        	table = new PdfPTable(2);
		        	table.setWidths(headerColumnWidths);
		        	table.setWidthPercentage(100); //Width 100%
				    
		        	PdfPCell acheivementsCell1 = new PdfPCell(new Paragraph("ACHIEVEMENTS", BodyName));
				    acheivementsCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    acheivementsCell1.setBorderColor(BaseColor.WHITE);   
				    acheivementsCell1.setPaddingLeft(10);
				
				    PdfPCell acheivementsCell2 = new PdfPCell(new Paragraph("", BodyName));
				    acheivementsCell2.setBorderColor(BaseColor.WHITE);   
				    
				    table.addCell(acheivementsCell1);
				    table.addCell(acheivementsCell2);
				    document.add(table);
					    
					  //Achievements Content
					   
				    document.add(PDFGenerator_Helper.getAchievements(getProfessorProfile()));
				 
					 
					
				  //--------------PROJECTS--------------//
			            //Projects Header
		        	PdfPTable projectsTable = new PdfPTable(2);
		        	projectsTable.setWidths(headerColumnWidths);
		        	projectsTable.setWidthPercentage(100); //Width 100%
		        	projectsTable.setSpacingBefore(50f); //Space before table
		        	projectsTable.setSpacingAfter(10f); //Space before table
				    
				    PdfPCell projectsHeaderCell = new PdfPCell(new Paragraph("PROJECTS", BodyName));
				
				    projectsHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    projectsHeaderCell.setBorderColor(BaseColor.WHITE);   
				    projectsHeaderCell.setPaddingLeft(10);
				
				    PdfPCell projectsHeaderCell2 = new PdfPCell(new Paragraph("", BodyName));
				    projectsHeaderCell2.setBorderColor(BaseColor.WHITE);   
				    
				    projectsTable.addCell(projectsHeaderCell);
				    projectsTable.addCell(projectsHeaderCell2);
				    document.add(projectsTable);
				    
				  //Projects Content			    
				
				    document.add(PDFGenerator_Helper.getProjects(getProfessorProfile()));
					    
					    
				    //--------------PROJECTS--------------// 
					    
					    //--------------PREFERRED SUBJECT TO TEACH--------------//
					  //PSTT Header
		        	PdfPTable psttTable = new PdfPTable(1);
		        	//psttTable.setWidths(headerColumnWidths);
		        	psttTable.setWidthPercentage(100); //Width 100%
		        	psttTable.setSpacingBefore(10f); //Space before table
		        	psttTable.setSpacingAfter(10f); //Space before table
				    
				    PdfPCell psttHeaderCell = new PdfPCell(new Paragraph("PREFERRED SUBJECTS TO TEACH", BodyName));
				    psttHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    psttHeaderCell.setBorderColor(BaseColor.WHITE);   
				    psttHeaderCell.setPaddingLeft(10);
				
				 
				    
				    psttTable.addCell(psttHeaderCell);

				    document.add(psttTable);
					    
					    
				  //Preferred Subjects to teach Content			    
					
				    PdfPTable psttContentTable = new PdfPTable(1);
				    
				 	for(int cell=1; cell <=3; cell++)
				 	{	    
					    PdfPCell psttCell = new PdfPCell();
					    psttCell.addElement(new Paragraph(cell + ") " + "Subjects", title));
					    psttCell.setBorderColor(BaseColor.WHITE);
					    psttCell.setPaddingBottom(20f);
					    psttContentTable.addCell(psttCell);
				 	}
				 	
				    document.add(psttContentTable);
				   
				    document.close();
			        writer.close();
			        
			    }
			  
			  	catch (Exception e)
			    {
			        e.printStackTrace();
			    
			    }
		}


}
