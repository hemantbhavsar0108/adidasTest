package testData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.CommonFunction;
import constant.Constant;
import report.ReportLib;
import constant.IFrameworkConstants;
import global.GlobalDeclaration;



public class TestData implements IFrameworkConstants{
	
	static Logger logger = Logger.getLogger(TestData.class.getName()); 
	CommonFunction common = new CommonFunction();
	ReportLib report = new ReportLib();
	
	/*
	 * Using this class , implemented the data encapsulation concept
	 * creating private for get method
	 * This test data will used for script execution
	 */
	private String testDataTestcaseName="";
	private String testDataProductName1="";
	private String testDataProductName2="";
	private String testDataTestcaseNumber="";
	
	
	private String testDataRemark="";
	
	/*
	 * This method set up the data for test execution
	 * 
	 * We have also achieved data encapsulation concept using this method
	 */
	public void readTestDataFile(String testcaseNumber) {
		logger.info("Started readTestDataFile method execution");
		XSSFSheet testDataSheet = null;
		try {
			testDataSheet=common.readExcelFile(common.getProjectPath()+Constant.testDataPath);
			int intTotalRows = testDataSheet.getLastRowNum();
			for (int intRow=1;intRow<=intTotalRows;intRow++) {
				 testDataTestcaseNumber = testDataSheet.getRow(intRow).getCell(0).getStringCellValue();
				 //retrieve only one row from test data file base on test name provided as parameter
				 if(testDataTestcaseNumber.trim().equals(testcaseNumber.trim())) {
					 testDataTestcaseName=testDataSheet.getRow(intRow).getCell(1).getStringCellValue();
					 testDataProductName1=testDataSheet.getRow(intRow).getCell(2).getStringCellValue();
					 testDataProductName2=testDataSheet.getRow(intRow).getCell(3).getStringCellValue();
					 break;
				 }
			}
			logger.info("test data set up is completed");
			report.reportData(TestExecutionStatus.PASS.toString(), "Test data set up is completed");
			
		} catch(Exception e) {
			logger.info("Error Occured in readTestDataFile method");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in readTestDataFile method");
			e.printStackTrace();
			System.exit(1);

		}
	}
		
		public void updateTestStatus(String executionStatus, String stepDescription) {
			logger.info("Started updateTestStatus method execution");
			XSSFSheet testDataSheet = null;
			XSSFWorkbook workbook=null;
			String testDataTestcaseID="";
			String strExcelFilePath="";
			 File file =null;
				 try
				 {
					 
					 strExcelFilePath=common.getProjectPath()+Constant.testDataPath;
					 file = new File(strExcelFilePath);
					 FileInputStream fis = null;
					 try {
							 fis = new FileInputStream(file);
							 workbook = new XSSFWorkbook(fis);
							 testDataSheet=workbook.getSheetAt(0);
								int intTotalRows = testDataSheet.getLastRowNum();
							
								for (int intRow=1;intRow<=intTotalRows;intRow++) {
									 testDataTestcaseID = testDataSheet.getRow(intRow).getCell(0).getStringCellValue();
									 //retrive only one row from test data file base on test name provided as parameter
									 System.out.println(GlobalDeclaration.testcaseID);
									 if(testDataTestcaseID.trim().equals(GlobalDeclaration.testcaseID)) {
										 testDataSheet.getRow(intRow).getCell(4).setCellValue(executionStatus);
										//stepDescription
										 testDataSheet.getRow(intRow).getCell(5).setCellValue(stepDescription);
										 break;
									 }
								}
								
								FileOutputStream outputstream = new FileOutputStream(file);
								//	workbook=common.getWorkBook(common.getProjectPath()+Constant.testDataPath);
									workbook.write(outputstream);
									System.out.println("write successful");
					} catch (FileNotFoundException e) {
							report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured to access Test data file :"+strExcelFilePath);
							e.printStackTrace();
							System.exit(1);
					}
								
			} catch(Exception e) {
				logger.info("Error Occured in updateTestStatus method");
				report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured updateTestStatus method ");
				e.printStackTrace();
				System.exit(1);
			}
			

	}
	
	public String getTestCaseName() {
		return testDataTestcaseName;
	}
	
	public String getProductName1() {
		return testDataProductName1;
	}

	public String getProductName2() {
		return testDataProductName2;
	}

}
