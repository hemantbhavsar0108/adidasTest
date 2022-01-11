package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.CommonFunction;
import constant.Constant;
import constant.IFrameworkConstants.ConfigKeyWords;
import constant.IFrameworkConstants.TestExecutionStatus;
import global.GlobalDeclaration;

public class ReportLib {
//	CommonFunction common= new CommonFunction();
	static Logger logger = Logger.getLogger(ReportLib.class.getName());  
	public void CreateDetailHTMLReport()
    { 
		logger.info("CreateDetailHTMLReport method execution started");
			
	   int intStepCounter = 1;
	   int intPasscount = 0;
	   int intFailcount = 0;
	
	   
	   String strDetailHTMLReportFileName = System.getProperty("user.dir")+Constant.reportLocation + GlobalDeclaration.testcaseName.trim() + ".html";
	   File file = new File(strDetailHTMLReportFileName);
       try {
           if (!file.exists()) {
               file.createNewFile();
               ////System.out.println("File is created");
           } else {
               file.delete();
               file.createNewFile();
               
           }
       } catch (IOException e) {
    	   e.printStackTrace();
    	   reportData(TestExecutionStatus.FAIL.toString(), "Error occured in CreateDetailHTMLReport method");
    	   logger.info("Error occured in CreateDetailHTMLReport method");
    	   System.exit(1);
       }
	   
        try {
            //define a HTML String Builder
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            htmlStringBuilder.append("<HTML><Header><Title>	Detail Report for " + GlobalDeclaration.testcaseName  + "  </Title></Header>");
    	 	
       //	 'Write HTML Header part
       		htmlStringBuilder.append("<Header><Title>	Execution Result for " + GlobalDeclaration.testcaseName  + " </Title></Header>");
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("	<Body>");
       		htmlStringBuilder.append("		<Table Border =1 CellPadding = 1 CellSpacing = 1 width = 80% >");
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Right width=50%><B><font color = white> Script_Name </font></B> </TD>");
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white> " + GlobalDeclaration.testcaseID + "-" + GlobalDeclaration.testcaseName  + "</font></B> </TD>");
       		htmlStringBuilder.append("			</TR>");
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("			<TR>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Right width=50%><B><font color = white> Environment </font></B> </TD>");
//       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + common.readPropertyValue(ConfigKeyWords.CONFIGPROPERTY.toString(),ConfigKeyWords.ENVIRONMENT.toString()) +"</font></B> </TD>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + "Test" +"</font></B> </TD>");
       	  
       		htmlStringBuilder.append("			</TR>");
       		htmlStringBuilder.append("");
       		
       		htmlStringBuilder.append("			<TR>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Right width=50%><B><font color = white> Release </font></B> </TD>");
//       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + common.readPropertyValue(ConfigKeyWords.CONFIGPROPERTY.toString(),ConfigKeyWords.RELEASE.toString()) + "</font></B> </TD>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + "1.0" + "</font></B> </TD>");
       	  
       		htmlStringBuilder.append("			</TR>");
      
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("			<TR>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Right width=50%><B><font color = white>User_Name </font></B> </TD>");
//       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + common.readPropertyValue(ConfigKeyWords.CONFIGPROPERTY.toString(),ConfigKeyWords. USER.toString()) + "</font></B> </TD>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + "TestUser" + "</font></B> </TD>");
       	  
       		htmlStringBuilder.append("			</TR>");
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("			<TR>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Right width=50%><B><font color = white>Browser </font></B> </TD>");
//       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + common.readPropertyValue(ConfigKeyWords.CONFIGPROPERTY.toString(),ConfigKeyWords.BROWSER.toString()) +"</font></B> </TD>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white>" + "Chrome" +"</font></B> </TD>");
       	  
       		htmlStringBuilder.append("			</TR>");
       		htmlStringBuilder.append("");
      
       		htmlStringBuilder.append("");
       		htmlStringBuilder.append("			<TR>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Right width=50%><B><font color = white>Start_Time </font></B> </TD>");
       		htmlStringBuilder.append("				<TD bgcolor = grey align=Left width=50%><B><font color = white> " + "Date and time" +" </B> </TD>");
       		htmlStringBuilder.append("			</TR>");
       		htmlStringBuilder.append("		</Table>");
       		
       	// Test Details part
    		
    		htmlStringBuilder.append("");
    		htmlStringBuilder.append(""); 
    		htmlStringBuilder.append( "		<Table Border =1 CellPadding = 1 CellSpacing = 1 width = 100%>");
    		htmlStringBuilder.append( "			<TR>");
    		htmlStringBuilder.append( "				<TH bgcolor = Black Width = 10%><B><Font color = white> Serial No# </Font></B> </TH>");
    		htmlStringBuilder.append(""); 
    		htmlStringBuilder.append( "				<TH bgcolor = Black Width = 25%><B><Font color = white> Time Stamp</Font></B> </TH>");
    		htmlStringBuilder.append( "");
    		htmlStringBuilder.append( "				<TH bgcolor = Black Width = 15%><B><Font color = white> Step Description</Font></B> </TH>");
    		htmlStringBuilder.append(""); 
    		htmlStringBuilder.append( "				<TH bgcolor = Black Width = 15%><B><Font color = white>Status</Font></B> </TH>");
    		htmlStringBuilder.append(""); 
    		htmlStringBuilder.append( "				<TH bgcolor = Black Width = 15%><B><Font color = white>Screen Shot</Font></B> </TH>");
    		htmlStringBuilder.append(""); 
    		
    		htmlStringBuilder.append(""); 
    		htmlStringBuilder.append( "			</TR>");
    		htmlStringBuilder.append( "");
    		
    		//Print the details table
    		int intArraySize = GlobalDeclaration.arrStepDescription.size();
    		String strColor = "";
    		
    		for(int intArrayRow=0;intArrayRow<intArraySize;intArrayRow++)
    		{
    			String strTextExecutionStatus = GlobalDeclaration.arrTestExecutionStatus.get(intArrayRow).trim();
    			if(strTextExecutionStatus.equalsIgnoreCase("Failed"))
    			{
    				strColor="Red";
    				intFailcount = intFailcount+1;
    			}
    			else if(strTextExecutionStatus.equalsIgnoreCase("Passed"))
    			{
    				strColor="Green";
    				intPasscount = intPasscount+1;
    			}
    			else if(strTextExecutionStatus.equalsIgnoreCase("NotCompleted"))
    			{
    				strColor="Brown";
    			}
    			htmlStringBuilder.append( "			<TR>");
    					htmlStringBuilder.append( "				<TD width = 10%><Font color ="  + strColor + ">" + "Step " + intStepCounter + "</Font></TD>");
    					htmlStringBuilder.append("");
    					htmlStringBuilder.append( "				<TD width = 25%><Font color ="  + strColor + ">" + GlobalDeclaration.arrTimeStamp.get(intArrayRow).trim()+"</Font></TD>");
    					htmlStringBuilder.append("");
    					htmlStringBuilder.append( "				<TD width = 25%><Font color = "  + strColor + ">" + GlobalDeclaration.arrStepDescription.get(intArrayRow).trim()+ "</Font></TD>");
    					htmlStringBuilder.append("");
    					htmlStringBuilder.append( "				<TD width = 10% align=Center><Font color =  "  + strColor + "><B>"+ GlobalDeclaration.arrTestExecutionStatus.get(intArrayRow).trim()+ "</B></Font></TD>");
    					htmlStringBuilder.append("");
    					htmlStringBuilder.append( "				<TD width = 10%><Font color =  "  + strColor + "><A target=blank href=" + "Screen Shot"+" >" +  GlobalDeclaration.arrScreenshotLink.get(intArrayRow).trim() + "</A> </Font></TD>");
    					htmlStringBuilder.append(""); 
    					htmlStringBuilder.append( "			</TR>");
    					htmlStringBuilder.append("");
    					
    					intStepCounter = intStepCounter +1;
    		}
    		
    		//clsGlobalVariable.EndTime = clsGlobalVariable.date.getTime();
  			//clsGlobalVariable.EndTimeTimestamp = new Timestamp(clsGlobalVariable.EndTime);
  			
  			
  			//clsGlobalVariable.TimeDiffInMilliseconds = clsGlobalVariable.EndTimeTimestamp - clsGlobalVariable.StartTimeTimestamp;
  			//clsGlobalVariable.TimeDiffInseconds = (int) clsGlobalVariable.TimeDiffInMilliseconds / 1000;
  			
  			//clsGlobalVariable.start
  			
    		htmlStringBuilder.append( "</Table>");
    		//'' Conclusion 
    		htmlStringBuilder.append("");
    		htmlStringBuilder.append( "		<Table Border =1 CellPadding = 1 CellSpacing = 1 width = 80%>");
    		htmlStringBuilder.append(""); 
    		htmlStringBuilder.append( "			<TR>");
    		htmlStringBuilder.append( "				<TD width=25% bgcolor = blue align=Right><B><font color = white> Execution End Time </font></B> </TD>");
    		htmlStringBuilder.append( "");
    		htmlStringBuilder.append( "				<TD align=Left><B>" + "Date and Time"   +" </B> </TD>");
    		htmlStringBuilder.append( "			</TR>");
    		htmlStringBuilder.append( "");
    		htmlStringBuilder.append( "			<TR>");
    		htmlStringBuilder.append( "				<TD width=25% bgcolor = blue align=Right><B><font color = white> Total Time taken </font></B> </TD>");
    		htmlStringBuilder.append(""); 		
    		htmlStringBuilder.append( "				<TD align=Left><B>" + "Need to mention time difference here"  + " Seconds" +"</B> </TD>");
    		htmlStringBuilder.append( "			</TR>");
    		htmlStringBuilder.append("");
    		htmlStringBuilder.append( "			<TR>");
    		htmlStringBuilder.append( "				<TD width=25% bgcolor =  Green algn=Right><B><font color = white> Total Steps Passed </font></B> </TD>");
    		htmlStringBuilder.append(""); 
    		htmlStringBuilder.append( "				<TD align=Left><B>" + intPasscount  + "</B> </TD>");
    		htmlStringBuilder.append( "			</TR>");
    		htmlStringBuilder.append( "");
    		htmlStringBuilder.append( "			<TR>");
    		htmlStringBuilder.append( "				<TD width=25% bgcolor =  Red align=Right><B><font color = white> Total Steps Failed</font></B> </TD>");
    		htmlStringBuilder.append( "");	
    		htmlStringBuilder.append( "				<TD align=Left><B>" + intFailcount + "</B> </TD>");
    		htmlStringBuilder.append( "		</TR>");
    		htmlStringBuilder.append( "		</Table>");
       		
       		htmlStringBuilder.append("		</Body>");
       		htmlStringBuilder.append("		</HTML>");
            // Create New File
            // U:\SeleniumFramework\Report_Detail_HTML
          //  File newFileName = new File("U:\\SeleniumFramework\\Report_Detail_HTML\\testfile.html");
           // newFileName.createNewFile();
            //write html string content to a file
            OutputStream outputStream = new FileOutputStream(strDetailHTMLReportFileName);
            Writer writer=new OutputStreamWriter(outputStream);
            writer.write(htmlStringBuilder.toString());
            writer.close();
            reportData(TestExecutionStatus.PASS.toString(), "HTML report generated");
     	   	
            
           // WriteToFile(htmlStringBuilder.toString(),"testfile.html");
        } catch (Exception e) {
        	e.printStackTrace();
     	   	reportData(TestExecutionStatus.FAIL.toString(), "Error occured in CreateDetailHTMLReport method");
     	   	logger.info("Error occured in CreateDetailHTMLReport method");
     	   	System.exit(1);
        }
    } // // End point - CreateHTML()
//--------------------------------------------------------------------------------------------------------
  public void reportData(String testExecutionStatus,String stepDescription) {
	  { 
		  try 
		  {   
			  logger.info("CreateDetailHTMLReport method execution started");
			  GlobalDeclaration.arrTestExecutionStatus.add(testExecutionStatus);
			  GlobalDeclaration.arrStepDescription.add(stepDescription);
			  GlobalDeclaration.arrTimeStamp.add(getDateTime());
			  GlobalDeclaration.arrScreenshotLink.add("");
		  }catch(Exception e) {
			  
			e.printStackTrace();
			reportData(TestExecutionStatus.FAIL.toString(), "Error occured in CreateDetailHTMLReport method");
			logger.info("Error occured in getConfigProperty method");
			System.exit(1);
	  }
	  }
	  	
  }
  
  //-----------------------------------------------------------------
  
  public static void UpdateExecutionStatusFlag(String pExecutionStatusFlag)
  { 
	  logger.info("UpdateExecutionStatusFlag method execution started");
	  try 
		{ //// Start point for try
		   //String strDetailHTMLReportFileName = System.getProperty("user.dir")+Constant.reportLocation + GlobalDeclaration.testcaseName.trim() + ".html";
				   
		   File src= new File(System.getProperty("user.dir")+Constant.reportLocation);
		   System.out.println("UpdateExecutionStatusFlag file path :" + System.getProperty("user.dir")+Constant.reportLocation);
		   FileInputStream fis=new FileInputStream(src);
		   // load the workbook
		   XSSFWorkbook wb=new XSSFWorkbook(fis);
		  // get the sheet which you want to modify or create
		   XSSFSheet sh1= wb.getSheetAt(0);
		   int intGlobalFileRecordCount = sh1.getLastRowNum();
				
		   for (int intGlobalFileRow =1;intGlobalFileRow<=intGlobalFileRecordCount ; intGlobalFileRow++)
				{ //for loop
					String strFlag= sh1.getRow(intGlobalFileRow).getCell(0).getStringCellValue().trim();
					////System.out.println("strFlag :" + strFlag);
					
					if (strFlag.equals("1"))
						
					{
						System.out.println("UpdateExecutionStatusFlag True condition");
						sh1.getRow(intGlobalFileRow).getCell(28).setCellValue(pExecutionStatusFlag);
						
						System.out.println("Test Execution Flag is updated");
						  break;
					}
				}
				try
				  {
					FileOutputStream outputstream = new FileOutputStream(src);
					wb.write(outputstream);
					System.out.println("write successful");
				  }
				
				catch(Exception e)
				{ 
					e.printStackTrace();
					System.out.println("write unsuccessful");
				}  
		}
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   logger.info("Error occured in getConfigProperty method");
		   System.exit(1);
	   }
  } 
  
  public String getDateTime()
  { 
	 logger.info("getDateTime method execution started");
	 String formattedDate="";
	 try {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 dateFormat.setTimeZone(TimeZone.getTimeZone("<local-time-zone>"));
		 Date date = new Date();
		 formattedDate= dateFormat.format(date);
	 }catch(Exception e) {
		e.printStackTrace();
		reportData(TestExecutionStatus.FAIL.toString(), "Error occured in readPropertyValue method");
		logger.info("Error occured in readPropertyValue method");
		System.exit(1);
	 }
	return formattedDate;
  }

}
