package common;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constant.IFrameworkConstants;
import io.restassured.internal.support.FileReader;
import report.ReportLib;
import constant.*;

public class CommonFunction implements IFrameworkConstants {
	static Logger logger = Logger.getLogger(CommonFunction.class.getName());  
	ReportLib report = new ReportLib();
	
	
	/*
	 * getConfigProperty method read property from config property
	 */
	public String getConfigProperty(String propertyName) {
		String propertyValue="";
		logger.info("getConfigProperty method execution started");
		try {
			File file=new File(getProjectPath()+ConfigKeyWords.CONFIG_PROPERTIES_PATH.toString());
			FileInputStream fis = new FileInputStream(file);
			//FileReader reader=new FileReader(getProjectPath()+ConfigKeyWords.CONFIG_PROPERTIES_PATH.toString());  
		    Properties property=new Properties();  
		    property.load(fis);
		    System.out.println(property.getProperty(propertyName));  
		    propertyValue= property.getProperty(propertyName);
		   
		}catch(Exception e) {
			e.printStackTrace();
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in getConfigProperty method");
			
			logger.info("Error occured in getConfigProperty method");
			System.exit(1);
		}finally {
			logger.info("getConfigProperty method execution completed successfully");
		}
		return propertyValue;
	}
	
	/*
	 * getProjectPath method return the project path
	 */
	public String  getProjectPath() {
			return System.getProperty("user.dir") ;
	}

	/*
	 * readTestDataFile method retrive test data sheet from test data file 
	 * this function return only one row  based on test case name
	 * And return the work sheet to calling function
	 */
	public XSSFSheet readExcelFile(String strExcelFilePath)
	
	{
		logger.info("readExcelFile method execution started");
		 XSSFWorkbook workbook=null;
		 File file =null;
		 try
		 {
			 file = new File(strExcelFilePath);
			 FileInputStream fis = null;
			 try {
					 fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
					report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured to access Test data file :"+strExcelFilePath);
					e.printStackTrace();
					System.exit(1);
			}
		
			 try {
				 workbook = new XSSFWorkbook(fis);
			 } catch (IOException e) {
					report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured to access Test data file :"+strExcelFilePath);
					e.printStackTrace();
					System.exit(1);
			 }
	}catch(Exception e){
			System.out.println("Problem in reading the global declaration file");
	}
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		report.reportData(TestExecutionStatus.PASS.toString(), "Successfully access test data file :"+strExcelFilePath);
		return sheet;
	 } // public XSSFSheet ReadExcelFile(String strExcelFilePath)
	
	 public String readPropertyValue(String propertyType,String propertyName) {
		 logger.info("readPropertyValue method execution started");
		 String propertyValue="";
		 File file = null;
		 FileInputStream fis =null;
		 
		 try {
			if(propertyType.equals(ConfigKeyWords.CONFIGPROPERTY.toString())) {
				 file = new File(getProjectPath()+ConfigKeyWords.CONFIG_PROPERTIES_PATH.toString());
				 fis = new FileInputStream(file);
			}
			else if (propertyType.equals(ConfigKeyWords.OBJECTPROPERTY.toString())) {
				System.out.println("path : "+getProjectPath()+getConfigProperty(ConfigKeyWords.OBJECT_PROPERTIES_PATH.toString()));
				file = new File(getProjectPath()+getConfigProperty(ConfigKeyWords.OBJECT_PROPERTIES_PATH.toString()));
				fis = new FileInputStream(file);
			}
			else
			{
				//Log error
			}
		    Properties property=new Properties();  
		    property.load(fis);  
		    System.out.println(property.getProperty(propertyName));  
		    propertyValue= property.getProperty(propertyName);
		   
		}catch(Exception e) {
			e.printStackTrace();
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in readPropertyValue method");
			
			logger.info("Error occured in readPropertyValue method");
			System.exit(1);
		}
		return propertyValue;
	}
	 
	 public String getDateTime()
	   { 
		 logger.info("getDateTime method execution started");
		 String formattedDate="";
		 try {
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			 dateFormat.setTimeZone(TimeZone.getTimeZone(readPropertyValue(ConfigKeyWords.CONFIGPROPERTY.toString(),ConfigKeyWords.TIMEZONE.toString())));
			 Date date = new Date();
			 formattedDate= dateFormat.format(date);
		 }catch(Exception e) {
			e.printStackTrace();
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in readPropertyValue method");
			logger.info("Error occured in readPropertyValue method");
			System.exit(1);
		 }
		return formattedDate;
	   }
	 
	 public XSSFWorkbook getWorkBook(String strExcelFilePath)
	 {
		 logger.info("getWorkBook method execution started");
		 XSSFWorkbook workbook=null;
		 
		 File file =null;
		 try
		 {
			 file = new File(strExcelFilePath);
			 FileInputStream fis = null;
			 try {
					 fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
					report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured to access Test data file :"+strExcelFilePath);
					e.printStackTrace();
					System.exit(1);
			}
		
			 try {
				 workbook = new XSSFWorkbook(fis);
			 } catch (IOException e) {
					report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured to access Test data file :"+strExcelFilePath);
					e.printStackTrace();
					System.exit(1);
			 }
	}catch(Exception e){
			System.out.println("Problem in reading the global declaration file");
	}
		
		report.reportData(TestExecutionStatus.PASS.toString(), "Successfully access test data file :"+strExcelFilePath);
		return workbook;
	 } // public XSSFSheet ReadExcelFile(String strExcelFilePath)
	
	 
	 
}
