package uiFunction;

import java.util.List;


import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import browser.BrowserFactory;
import common.CommonFunction;
import constant.Constant;
import constant.IFrameworkConstants;
import constant.IFrameworkConstants.ConfigKeyWords;
import constant.IFrameworkConstants.TestExecutionStatus;
import report.ReportLib;
import testData.TestData;
import webDriver.WebDriverFunctions;

public class UIFunction implements IFrameworkConstants{
	CommonFunction common= new CommonFunction();
	TestData  testdata= new TestData();
	static Logger logger = Logger.getLogger(UIFunction.class.getName()); 
	ReportLib report = new ReportLib();
	WebDriver driver=null;
	WebDriverFunctions webDriverFunction= new WebDriverFunctions();
	/*
	 * Following method call web driver's launchApplication method to launch the application
	 */
	public void launchApplication() {
		logger.info("Started launchApplication method execution");
		try {
			BrowserFactory bf= new BrowserFactory();
			driver = bf.launchApplication();
			logger.info("launchApplication method execution is completed");
		} catch(Exception e) {
			e.printStackTrace();
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in getConfigProperty method");
			
			logger.info("Error occured in getConfigProperty method");
			System.exit(1);
		}
	}

	/*
	 * Following method call web driver's clickObject method to click on particular object
	 */
	public void clickObject(String objectName,String tbu) {
		logger.info("Started clickObject method execution");
		try {
			webDriverFunction.clickObject(driver,objectName,tbu);
			testdata.updateTestStatus("Passed", "Test Execution Successful");
			 report.reportData(TestExecutionStatus.PASS.toString(), "Click on "+tbu);
				
			logger.info("clickObject method execution is completed");
		} catch(Exception e) {
			e.printStackTrace();
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in clickObject method");
			
			logger.info("Error occured in clickObject method");
			System.exit(1);
		}
		
	}
		
	/*
	 * quitBrowser method quit the browser
	 */
	public void quitBrowser() {
		logger.info("Started quitBrowser method execution");
		try {
			driver.quit();
			
			logger.info("Close the browser");
			report.reportData(TestExecutionStatus.PASS.toString(), "Close the browser");
			
		} catch(Exception e) {
			e.printStackTrace();
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in quitBrowser method");
			
			logger.info("Error occured in quitBrowser method");
			System.exit(1);
		}
	}
	
	
	public String getProjectPath() {
		logger.info("Started getProjectPath method execution");
		String newsubText="";
		try {
			 String projectPath=common.getProjectPath();
			 logger.info("project :"+projectPath);
			
		} catch(Exception e) {
			e.printStackTrace();
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in getProjectPath method");
			
			logger.info("Error occured in getProjectPath method");
			System.exit(1);
		}
		return newsubText;
	}
	//common.getConfigProperty(ConfigKeyWords.REPORT_LOCATION.toString())
	
	 public String getDateTime()
	   { 
		 logger.info("Started getDateTime method execution");
		 String formattedDate="";
		 try {
			 common.getDateTime();
		 }catch(Exception e) {
			 logger.info("Error Occured in getDateTime method");
				e.printStackTrace();
		 }
		return formattedDate;
	   }
	 
	 public void searchProduct(String productName) {
		  logger.info("Started searchProduct method execution");
		 	Boolean productDislayed=false;
		 	WebElement productNameElement= null;
		 	WebElement elementNextButton= null;
		 	
		 	try { 
			// Identify product object
		 		if (webDriverFunction.isDisplayed(webDriverFunction.getElement(driver, "link.text", productName.trim()))){
		 		productNameElement=webDriverFunction.getElement(driver, "link.text", productName.trim());
		 		productNameElement.click();
		 		
		 		}
		 		
		 		else {
		 			webDriverFunction.applyExplicitWait(driver, "button.text", ApplicationObjectText.NextButton.toString(), ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
			 		elementNextButton=webDriverFunction.getElement(driver,"button.text", "Next");
			 		elementNextButton.click();
			 		webDriverFunction.applyExplicitWait(driver, "link.text", productName.trim(), ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
			 		
			 		productNameElement=webDriverFunction.getElement(driver, "link.text", productName.trim());
			 		productNameElement.click();
		 		
		 		}
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Selected the product :"+productName.trim());
				
		 		
		  } catch(Exception e) {
			  e.printStackTrace();
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in searchProduct method");
			  logger.info("Error occured in searchProduct method");
			  System.exit(1);
			  
		  }finally {
			  System.out.println("searchProduct method execution completed sucessfully");
			  logger.info("searchProduct method execution completed sucessfully");
				
		  }
		  
	 }
	 
	 /*
	  * addToCart method validate weather correct product is selected and add that product to cart
	  */
	 public void addProductToCart() {
		  logger.info("Started addProductToCart method execution");
		 
		 	try { 
			// Identify product object
		 	// Click addtocart button
		 		webDriverFunction.clickObject(driver, "link.text", ApplicationObjectText.AddToCart.toString());
		 		logger.info("click on Add to cart button");
		 		 report.reportData(TestExecutionStatus.PASS.toString(), "click on Add to cart button");
				 
		 		try {
		 			webDriverFunction.applyExplicitWaitForAlertPresent(driver, 20);
		 			
		 			Alert alert=driver.switchTo().alert();
		 			
		 			
		 			String confirmationMessage=alert.getText();
		 			System.out.println("Displayed the confirmation message :"+confirmationMessage);
		 			logger.info("Displayed the confirmation message :"+confirmationMessage);
		 			 report.reportData(TestExecutionStatus.PASS.toString(), "Displayed the confirmation message :"+confirmationMessage);
					alert.accept();
					logger.info("Alert accepted suceessfuly");
		 			
		 		}
		 		catch(Exception e) {
		 			 e.printStackTrace();
					  report.reportData(TestExecutionStatus.PASS.toString(), "Alert is not displayed");
					  logger.info("Alert is not displayed");
					  System.exit(1);
		 		}
		 		
		  } catch(Exception e) {
			  e.printStackTrace();
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in searchProduct method");
			  logger.info("Error occured in searchProduct method");
			  System.exit(1);
		  }finally {
			  System.out.println("addProductToCart method execution completed sucessfully");
			  logger.info("addProductToCart method execution completed sucessfully");
				
		  }
		  
	 }
	 /*
	  * * validateAddToCartProduct method validate weather correct product is selected and add that product to cart
	  */
	 public void validateAddToCartProduct(String productName) {
		  logger.info("Started validateAddToCartProduct method execution");
		 
		 	try { 
			// Identify product object
		 	// Click addtocart button
		 	String uiAddToCartProduct=webDriverFunction.getText(driver, "addToCart.product.name", "");
		 	Assert.assertEquals(productName.trim(), uiAddToCartProduct.trim()," validated product displayed in Add to Cart page");
		 	report.reportData(TestExecutionStatus.PASS.toString(), "Product displayed in cart page");
			 	
		  } catch(Exception e) {
			  e.printStackTrace();
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in validateAddToCartProduct method");
			  logger.info("Error occured in validateAddToCartProduct method");
			  System.exit(1);
		  }
	
		  
	 }

	 /*
	  * * validateAddToCartProduct method validate weather correct product is selected and add that product to cart
	  */
	 public void gotoCartPage() {
		  logger.info("Started validateAddToCartProduct method execution");
		 
		 	try { 
			// Identify product object
		 	// Click addtocart button
		 		webDriverFunction.applyExplicitWait(driver, "link.text", ApplicationObjectText.CartPage.toString(), ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
		 		
		 		webDriverFunction.clickObject(driver, "link.text", ApplicationObjectText.CartPage.toString());
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Navigated to Cart Page");
				logger.info("Navigated to cart page");
		 		System.out.println("Navigated to cart page");
		 		
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.info("Error occured in gotoCartPage method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occued to Navigate Cart Page");
			  System.exit(1);
		  }finally {
			  System.out.println("validateAddToCartProduct method execution completed sucessfully");
			  logger.info("validateAddToCartProduct method execution completed sucessfully");
				
		  }
	 }
	 
	 /*
	  * 
	  */
	 public void deleteCartProduct(String productName) {
		  logger.info("Started deleteCartProduct method execution");
		 
		 	try { 
			// Identify product object
		 	// Click addtocart button
		 		WebElement productNameElement=null;
		 		
		 		webDriverFunction.applyExplicitWait(driver, "cart.product.name", productName.trim(), ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
		 		
		 		productNameElement=webDriverFunction.getElement(driver, "cart.product.name", productName.trim());
		 		
		 		Assert.assertTrue(webDriverFunction.isDisplayed(productNameElement), "Product is displayed in the cart:");
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Product is displayed in cart page for deletion :"+productName);
				  
		 		webDriverFunction.applyExplicitWait(driver, "cart.product.delete", productName.trim(), ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
		 		
		 		//Click Delete Button
		 		webDriverFunction.clickObject(driver, "cart.product.delete", productName.trim());
		 		
		 	//	productNameElement=webDriverFunction.getElement(driver, "cart.product.name", productName.trim());
		 		webDriverFunction.applyExplicitWait(driver, "button.text", "Place Order", ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
			 	
		 		Assert.assertFalse(webDriverFunction.isDisplayed(productNameElement), "Product is deleted from the cart:");
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Product is deleted from cart page  :"+productName);
				
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.info("Error occured in deleteCartProduct method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in deleteCartProduct method");
			  System.exit(1);
				
		  }
	 }
	 
	 public void gotoHomePage() {
		  logger.info("Started gotoHomePage method execution");
		 
		 	try { 
		 		webDriverFunction.clickObject(driver, "product.home.page", "");
		 		logger.info("Navigated to Home page");
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Navigared to Home page");
		 			
		 		System.out.println("Navigated to cart page");
		 		
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.info("Error occured in gotoHomePage method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in gotoHomePage method");
			  System.exit(1);
			  
		  }
	 }
	 
	//
	 public void placeOrder() {
		  logger.info("Started placeOrder method execution");
		 
		 	try { 
		 		webDriverFunction.applyExplicitWait(driver, "button.text", ApplicationObjectText.PlaceOrderLink.toString(), ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
		 		webDriverFunction.clickObject(driver, "button.text", ApplicationObjectText.PlaceOrderLink.toString());
		 		logger.info("Clicked on place order button");
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Clicked on place order button");
		 			
		 		
		 		
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.info("Error occured in gotoHomePage method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in placeOrder method");
			  System.exit(1);
			  
		  }
	 }
	
	 public void enterFormDetails(String name,String country,String city,String card,String month,String year) {
		  logger.info("Started placeOrder method execution");
		 
	
		 	try { 
		 		webDriverFunction.applyExplicitWait(driver, "object.by.id", "name", ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
		 		
		 		webDriverFunction.sendKey(driver, "object.by.id", "name", name);
		 		logger.info("set name in form details"+name);
		 		report.reportData(TestExecutionStatus.PASS.toString(), "set name in form details"+name);
		 		
		 		webDriverFunction.sendKey(driver, "object.by.id", "country", country);
		 		logger.info("set country in form details"+country);
		 		report.reportData(TestExecutionStatus.PASS.toString(), "set country in form details"+country);
		 		
		 		webDriverFunction.sendKey(driver, "object.by.id", "city", city);
		 		logger.info("set city in form details"+city);
		 		report.reportData(TestExecutionStatus.PASS.toString(), "set city in form details"+city);
		 		
		 		webDriverFunction.sendKey(driver, "object.by.id", "card", card.trim());
		 		logger.info("set card in form details"+card.trim());
		 		report.reportData(TestExecutionStatus.PASS.toString(), "set name in form details"+name);
		 		
		 		webDriverFunction.sendKey(driver, "object.by.id", "month", month.trim());
		 		logger.info("set month in form details"+month);
		 		report.reportData(TestExecutionStatus.PASS.toString(), "set month in form details"+month);
		 		
		 		
		 		webDriverFunction.sendKey(driver, "object.by.id", "year", year.trim());
		 		logger.info("set year in form details"+year.trim());
		 		report.reportData(TestExecutionStatus.PASS.toString(), "set year in form details"+year.trim());
		 		
		 		
		 		
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.info("Error occured in gotoHomePage method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in placeOrder method");
			  System.exit(1);
			  
		  }
	 }
	 
	 public void purchaseOrder() {
		  logger.info("Started purchaseOrder method execution");
		 
		 	try { 
		 		webDriverFunction.applyExplicitWait(driver, "button.text", "Purchase", ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
		 		webDriverFunction.scrollIntoView(driver, "button.text", "Purchase");
		 		webDriverFunction.clickObject(driver, "button.text", "Purchase");
		 		logger.info("Clicked on purchase order button");
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Clicked on purchase order button");
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.info("Error occured in purchaseOrder method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in purchaseOrder method");
			  System.exit(1);
			  
		  }
	 }
	 
	 public void validatePurchaseMessage() {
		  logger.info("Started purchaseOrder method execution");
		 
		 	try { 
		 		Thread.sleep(3000);
		 		webDriverFunction.applyExplicitWait(driver, "data.purchase.message", "", ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
//		 		/data.purchase.message
		 		String purchaseMessage=webDriverFunction.getText(driver, "data.purchase.message", "");
		 		
		 		String id=getPurchaseDetail("Id",purchaseMessage);
		 		logger.info("Purchase Id displayed in confirmation message:"+id);
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Purchase Id displayed in confirmation message:"+id);
		 		String amount=getPurchaseDetail("Amount",purchaseMessage);
		 		logger.info("amount Id displayed in confirmation message:"+amount);
			 	
		 		report.reportData(TestExecutionStatus.PASS.toString(), "amount Id displayed in confirmation message:"+amount);
		 		
		 		
		 		logger.info("Clicked on purchase order button");
		 		report.reportData(TestExecutionStatus.PASS.toString(), "Clicked on purchase order button");
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.info("Error occured in purchaseOrder method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in purchaseOrder method");
			  System.exit(1);
			  
		  }
	 }
	 
	 public String getPurchaseDetail(String field , String purchaseMessage) {
		 logger.info("Started getPurchaseDetail method execution");
	 
		 String fieldMessage="";
		 String[] arrFieldMessage;
		 String value="";
		 String[] arrPurchaseMessage=purchaseMessage.split("\\n");
		 int counter=0;
		 String purchaseDetailValue="";
		
		 try {
			 if(field.trim().equalsIgnoreCase("Id")) {
				 fieldMessage=arrPurchaseMessage[0];
				 arrFieldMessage=fieldMessage.split(":");
				 value=arrFieldMessage[1];
			 } else if (field.trim().equalsIgnoreCase("Amount")) {
				 fieldMessage=arrPurchaseMessage[1];
				 arrFieldMessage=fieldMessage.split(":");
				 value=arrFieldMessage[1];
			 } else if (field.trim().equalsIgnoreCase("Card Number")) {
				 fieldMessage=arrPurchaseMessage[2];
				 arrFieldMessage=fieldMessage.split(":");
				 value=arrFieldMessage[1];
				 
			} else if (field.trim().equalsIgnoreCase("Name")) {
				 fieldMessage=arrPurchaseMessage[3];
				 arrFieldMessage=fieldMessage.split(":");
				 value=arrFieldMessage[1];
			} else if (field.trim().equalsIgnoreCase("Date")) {
				fieldMessage=arrPurchaseMessage[4];
				 arrFieldMessage=fieldMessage.split(":");
				 value=arrFieldMessage[1];
			}
		 }catch (Exception e){
			  e.printStackTrace();
			  logger.info("Error occured in getPurchaseDetail method");
			  report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in getPurchaseDetail method");
			  System.exit(1);
		 }
		 
		 return value;
	 }
	 

}
