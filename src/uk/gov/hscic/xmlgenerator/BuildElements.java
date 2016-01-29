package uk.gov.hscic.xmlgenerator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BuildElements {

	public static Element createElements(Connection conn, Document doc, Element root ) throws DOMException, SQLException, ParserConfigurationException{
			ArrayList<String> tables = new ArrayList<String>();
			tables.add(0,"CYP000");
			tables.add(1,"CYP001");
			tables.add(2,"CYP002");
			tables.add(3,"CYP003");
			tables.add(4,"CYP101");
			tables.add(5,"CYP102");
			tables.add(6,"CYP103");
			tables.add(7,"CYP104");
			tables.add(8,"CYP105");
			tables.add(9,"CYP201");
			tables.add(10,"CYP202");
			tables.add(11,"CYP301");
			tables.add(12,"CYP401");
			tables.add(13,"CYP402");
			tables.add(14,"CYP403");
			tables.add(15,"CYP404");
			tables.add(16,"CYP501");
			tables.add(17,"CYP502");
			tables.add(18,"CYP601");
			tables.add(19,"CYP602");
			tables.add(20,"CYP603");
			tables.add(21,"CYP604");
			tables.add(22,"CYP605");
			tables.add(23,"CYP606");
			tables.add(24,"CYP607");
			tables.add(25,"CYP608");
			tables.add(26,"CYP609");
			tables.add(27,"CYP610");
			tables.add(28,"CYP611");
			tables.add(29,"CYP612");
			tables.add(30,"CYP613");
			tables.add(31,"CYP901");
			
			Statement statement = conn.createStatement();
			
			String sqlCYP001 = "select * from " + tables.get(1);
			ResultSet CYP001rs = statement.executeQuery(sqlCYP001);
			ResultSetMetaData meta001 = CYP001rs.getMetaData();
			int colCount = meta001.getColumnCount();
			
			
			while (CYP001rs.next())
			{
				
				Element CYP001 = doc.createElement("CYP001");
		        String currentPatient = CYP001rs.getString("C001901");
			    for (int col=1; col <= colCount; col++) 
			    {
			    	String cn = meta001.getColumnName(col);
			        String value = CYP001rs.getString(col);
			        if (value == null){value = "";} 
			        Element elem = doc.createElement(cn);
			        elem.setTextContent(value);
			        CYP001.appendChild(elem);
			        
			    }  
			    
			        //CYP101
			    	Statement statement101 = conn.createStatement();
			    	Element CYP101 = doc.createElement("CYP101");
			        String sqlCYP101 = "select * from " + tables.get(4) + " WHERE C101901= '"+currentPatient+"'";
					ResultSet CYP101rs = statement101.executeQuery(sqlCYP101);
					ResultSetMetaData meta101 = CYP101rs.getMetaData();
					int col101Count = meta101.getColumnCount();
					while (CYP101rs.next())
					{
						String serviceRequestID = CYP101rs.getString("C101902");

					    for (int col101=1; col101 <= col101Count; col101++) 
					    {
					    	String cn101 = meta101.getColumnName(col101);
					        String value101 = CYP101rs.getString(col101);
					        
					        if (value101 == null){value101 = "";} 
					        Element elem101 = doc.createElement(cn101);
					        elem101.setTextContent(value101);
					        CYP101.appendChild(elem101);
					    }
					    CYP001.appendChild(CYP101);
					    
						    //CYP201
					    	Statement statement201 = conn.createStatement();
					    	Element CYP201 = doc.createElement("CYP201");
					        String sqlCYP201 = "select * from " + tables.get(9) + " WHERE C201902= '"+serviceRequestID+"'";
							ResultSet CYP201rs = statement201.executeQuery(sqlCYP201);
							ResultSetMetaData meta201 = CYP201rs.getMetaData();
							int col201Count = meta201.getColumnCount();
							while (CYP201rs.next())
							{
								String serviceRequestID201 = CYP201rs.getString("C201903");
							    for (int col201=1; col201 <= col201Count; col201++) 
							    {
							    	String cn201 = meta201.getColumnName(col201);
							        String value201 = CYP201rs.getString(col201);
							        
							        if (value201 == null){value201 = "";} 
							        Element elem201 = doc.createElement(cn201);
							        elem201.setTextContent(value201);
							        CYP201.appendChild(elem201);
							    }
							    CYP101.appendChild(CYP201);
							    
							    
									    //CYP202
								    	Statement statement202 = conn.createStatement();
								    	Element CYP202 = doc.createElement("CYP202");
								        String sqlCYP202 = "select * from " + tables.get(10) + " WHERE C202903= '"+serviceRequestID201+"'";
										ResultSet CYP202rs = statement202.executeQuery(sqlCYP202);
										ResultSetMetaData meta202 = CYP202rs.getMetaData();
										int col202Count = meta202.getColumnCount();
										while (CYP202rs.next())
										{
											String serviceRequestID202 = CYP202rs.getString("C202904");
										    for (int col202=1; col202 <= col202Count; col202++) 
										    {
										    	String cn202 = meta202.getColumnName(col202);
										        String value202 = CYP202rs.getString(col202);
										        
										        if (value202 == null){value202 = "";} 
										        Element elem202 = doc.createElement(cn202);
										        elem202.setTextContent(value202);
										        CYP202.appendChild(elem202);
										    }
										    CYP201.appendChild(CYP202);
										    
										    
												    //610 - 612
												    for(int x=27; x <= 29; x++) {
												    	String colname = "C" + tables.get(x).toString().substring(3, 6) + "904";
												    	
												    	Statement statement6 = conn.createStatement();
												    	Element CYP6 = doc.createElement(tables.get(x).toString());
												        String sqlCYP6 = "select * from " + tables.get(x) + " WHERE " +colname+" ='"+serviceRequestID202+"'";
														ResultSet CYP6rs = statement6.executeQuery(sqlCYP6);
														ResultSetMetaData meta6 = CYP6rs.getMetaData();
														int col6Count = meta6.getColumnCount();
														while (CYP6rs.next())
														{
														    for (int col6=1; col6 <= col6Count; col6++) 
														    {
														    	String cn6 = meta6.getColumnName(col6);
														        String value6 = CYP6rs.getString(col6);
														        
														        if (value6 == null){value6 = "";} 
														        Element elem6 = doc.createElement(cn6);
														        elem6.setTextContent(value6);
														        CYP6.appendChild(elem6);
														    }
														    CYP202.appendChild(CYP6);
												    	
														}
												    	
		
												    }
										    
										}//CYP202rs.next())
							    
							}//CYP201rs.next())
							
							
						    //CYP102 - CYP105
						    for(int x=5; x <= 8; x++) {
						    	String colname = "C" + tables.get(x).toString().substring(3, 6) + "902";
						    	
						    	Statement statementa = conn.createStatement();
						    	Element CYPa = doc.createElement(tables.get(x).toString());
						        String sqlCYPa = "select * from " + tables.get(x) + " WHERE " +colname+" ='"+serviceRequestID+"'";
								ResultSet CYPars = statementa.executeQuery(sqlCYPa);
								ResultSetMetaData metaa = CYPars.getMetaData();
								int colaCount = metaa.getColumnCount();
								while (CYPars.next())
								{
								    for (int cola=1; cola <= colaCount; cola++) 
								    {
								    	String cna = metaa.getColumnName(cola);
								        String valuea = CYPars.getString(cola);
								        
								        if (valuea == null){valuea = "";} 
								        Element elema = doc.createElement(cna);
								        elema.setTextContent(valuea);
								        CYPa.appendChild(elema);
								    }
								    CYP101.appendChild(CYPa);
						    	
								}
						    	

						    }
							
							
						  //CYP606 to CYP609
						    for(int x=23; x <= 26; x++) {
						    	String colname = "C" + tables.get(x).toString().substring(3, 6) + "902";
						    	
						    	Statement statementa = conn.createStatement();
						    	Element CYPa = doc.createElement(tables.get(x).toString());
						        String sqlCYPa = "select * from " + tables.get(x) + " WHERE " +colname+" ='"+serviceRequestID+"'";
								ResultSet CYPars = statementa.executeQuery(sqlCYPa);
								ResultSetMetaData metaa = CYPars.getMetaData();
								int colaCount = metaa.getColumnCount();
								while (CYPars.next())
								{
								    for (int cola=1; cola <= colaCount; cola++) 
								    {
								    	String cna = metaa.getColumnName(cola);
								        String valuea = CYPars.getString(cola);
								        
								        if (valuea == null){valuea = "";} 
								        Element elema = doc.createElement(cna);
								        elema.setTextContent(valuea);
								        CYPa.appendChild(elema);
								    }
								    CYP101.appendChild(CYPa);
						    	
								}
						    }
							
					}//CYP101rs.next())
					
					 //CYP002
			    	Statement statement002 = conn.createStatement();
			    	Element CYP002 = doc.createElement("CYP002");
			        String sqlCYP002 = "select * from " + tables.get(2) + " WHERE C002901= '"+currentPatient+"'";
					ResultSet CYP002rs = statement002.executeQuery(sqlCYP002);
					ResultSetMetaData meta002 = CYP002rs.getMetaData();
					int col002Count = meta002.getColumnCount();
					while (CYP002rs.next())
					{
					    for (int col002=1; col002 <= col002Count; col002++) 
					    {
					    	String cn002 = meta002.getColumnName(col002);
					        String value002 = CYP002rs.getString(col002);
					        
					        if (value002 == null){value002 = "";} 
					        Element elem002 = doc.createElement(cn002);
					        elem002.setTextContent(value002);
					        CYP002.appendChild(elem002);
					    }
					    CYP001.appendChild(CYP002);
					}
					
					
					//CYP003
			    	Statement statement003 = conn.createStatement();
			    	Element CYP003 = doc.createElement("CYP003");
			        String sqlCYP003 = "select * from " + tables.get(3) + " WHERE C003901= '"+currentPatient+"'";
					ResultSet CYP003rs = statement003.executeQuery(sqlCYP003);
					ResultSetMetaData meta003 = CYP003rs.getMetaData();
					int col003Count = meta003.getColumnCount();
					while (CYP003rs.next())
					{
					    for (int col003=1; col003 <= col003Count; col003++) 
					    {
					    	String cn003 = meta003.getColumnName(col003);
					        String value003 = CYP003rs.getString(col003);
					        
					        if (value003 == null){value003 = "";} 
					        Element elem003 = doc.createElement(cn003);
					        elem003.setTextContent(value003);
					        CYP003.appendChild(elem003);
					    }
					    CYP001.appendChild(CYP003);
					}
					
					
					
					  //CYP401 - CYP605
				    for(int x=12; x <= 22; x++) {
				    	String colname = "C" + tables.get(x).toString().substring(3, 6) + "901";
//				    	System.out.println("col= "+colname);
				    	
				    	Statement statementb = conn.createStatement();
				    	Element CYPb = doc.createElement(tables.get(x).toString());
				        String sqlCYPb = "select * from " + tables.get(x) + " WHERE " +colname+" ='"+currentPatient+"'";
						ResultSet CYPbrs = statementb.executeQuery(sqlCYPb);
						ResultSetMetaData metab = CYPbrs.getMetaData();
						int colbCount = metab.getColumnCount();
						while (CYPbrs.next())
						{
						    for (int colb=1; colb <= colbCount; colb++) 
						    {
						    	String cnb = metab.getColumnName(colb);
						        String valueb = CYPbrs.getString(colb);
						        
						        if (valueb == null){valueb = "";} 
						        Element elemb = doc.createElement(cnb);
						        elemb.setTextContent(valueb);
						        CYPb.appendChild(elemb);
						    }
						    CYP001.appendChild(CYPb);
				    	
						}
				    }
				    
			root.appendChild(CYP001);
			}//CYP001rs.next())
			
			   //CYP301
	    	Statement statement301 = conn.createStatement();
	    	Element CYP301 = doc.createElement("CYP301");
	        String sqlCYP301 = "select * from " + tables.get(11);
			ResultSet CYP301rs = statement301.executeQuery(sqlCYP301);
			ResultSetMetaData meta301 = CYP301rs.getMetaData();
			int col301Count = meta301.getColumnCount();
			while (CYP301rs.next())
			{
			    for (int col301=1; col301 <= col301Count; col301++) 
			    {
			    	String cn301 = meta301.getColumnName(col301);
			        String value301 = CYP301rs.getString(col301);
			        
			        if (value301 == null){value301 = "";} 
			        Element elem301 = doc.createElement(cn301);
			        elem301.setTextContent(value301);
			        CYP301.appendChild(elem301);
			    }
			    root.appendChild(CYP301);
			}
		    
			
			//CYP613
	    	Statement statement613 = conn.createStatement();
	    	Element CYP613 = doc.createElement("CYP613");
	        String sqlCYP613 = "select * from " + tables.get(30);
			ResultSet CYP613rs = statement613.executeQuery(sqlCYP613);
			ResultSetMetaData meta613 = CYP613rs.getMetaData();
			int col613Count = meta613.getColumnCount();
			while (CYP613rs.next())
			{
			    for (int col613=1; col613 <= col613Count; col613++) 
			    {
			    	String cn613 = meta613.getColumnName(col613);
			        String value613 = CYP613rs.getString(col613);
			        
			        if (value613 == null){value613 = "";} 
			        Element elem613 = doc.createElement(cn613);
			        elem613.setTextContent(value613);
			        CYP613.appendChild(elem613);
			    }
			    root.appendChild(CYP613);
			}
			
			
			//CYP901
	    	Statement statement901 = conn.createStatement();
	    	Element CYP901 = doc.createElement("CYP901");
	        String sqlCYP901 = "select * from " + tables.get(31);
			ResultSet CYP901rs = statement901.executeQuery(sqlCYP901);
			ResultSetMetaData meta901 = CYP901rs.getMetaData();
			int col901Count = meta901.getColumnCount();
			while (CYP901rs.next())
			{
			    for (int col901=1; col901 <= col901Count; col901++) 
			    {
			    	String cn901 = meta901.getColumnName(col901);
			        String value901 = CYP901rs.getString(col901);
			        
			        if (value901 == null){value901 = "";} 
			        Element elem901 = doc.createElement(cn901);
			        elem901.setTextContent(value901);
			        CYP901.appendChild(elem901);
			    }
			    root.appendChild(CYP901);
			}
			
			return null;
			
		}
}
