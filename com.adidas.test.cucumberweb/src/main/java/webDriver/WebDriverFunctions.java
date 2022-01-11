package webDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonFunction;
import constant.Constant;
import constant.IFrameworkConstants.ConfigKeyWords;
import constant.IFrameworkConstants.ExplicitWaitCondition;
import constant.IFrameworkConstants.Locator;
import constant.IFrameworkConstants.TestExecutionStatus;
import report.ReportLib;

public class WebDriverFunctions {
	
	static Logger logger = Logger.getLogger(WebDriverFunctions.class.getName()); 
	ReportLib report = new ReportLib();

	CommonFunction common= new CommonFunction();
	
	/*
	 * This method click on any object description provided as parameter
	 * 
	 * We have created dynamic xpath. Single xpath can be used for multiple object by passing text as paramter
	 * 
	 * we need to replace "TBU"mentioned in particular xpath by text value
	 */
	
	public void clickObject(WebDriver driver,String objectName,String tbu) {
		logger.info("Started clickObject method execution");
		WebElement element = null;
		try {
			applyExplicitWait(driver, objectName, tbu, ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
			 element=getElement(driver,objectName,tbu);
			if(isDisplayed(element)) {
				element.click();
				

			}else
			{
				Exception e=null;
				report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured to click the element :"+element.getText());
				e.printStackTrace();
				report.CreateDetailHTMLReport();
				System.exit(1);

			}
			logger.info("clickObject method execution completed successfully");
		}catch(Exception e) {
			logger.info("Error occured in clickObject method successfully");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured to click the element :"+element.getText());
			e.printStackTrace();
			report.CreateDetailHTMLReport();
			
			System.exit(1);
		}
	}
	
	/*
	 * getElement method return webelemt based dyanamic parameter is provided as parameter
	 */
	
	public WebElement getElement(WebDriver driver,String objectName,String tbu) {
		logger.info("Started getElement method execution");
		WebElement element =null;
		try {
			
			String objectProperty=common.readPropertyValue(ConfigKeyWords.OBJECTPROPERTY.toString(), objectName);
			String[] arrPropertyValue=objectProperty.split("==");
			System.out.println("arrPropertyValue[0] "+arrPropertyValue[0]);
			System.out.println("arrPropertyValue[1] "+arrPropertyValue[1]);
			
			if(arrPropertyValue[1].trim().contains("TBU")) {
				arrPropertyValue[1]=arrPropertyValue[1].replace("TBU", tbu.trim());
				System.out.println("arrPropertyValue[1] :"+arrPropertyValue[1]);
			}
		
			if(arrPropertyValue[0].equalsIgnoreCase(Locator.ID.toString())) {
				element=driver.findElement(By.id(arrPropertyValue[1].trim()));
			}else if(arrPropertyValue[0].trim().equalsIgnoreCase(Locator.NAME.toString())) {
				element=driver.findElement(By.name(arrPropertyValue[1].trim()));
			
			}else if(arrPropertyValue[0].trim().equalsIgnoreCase(Locator.LINKTEXT.toString())) {
				element=driver.findElement(By.linkText(arrPropertyValue[1].trim()));
			}else if(arrPropertyValue[0].trim().equalsIgnoreCase(Locator.PARTIALLINKTEXT.toString())) {
				element=driver.findElement(By.partialLinkText(arrPropertyValue[1].trim()));
			}else if(arrPropertyValue[0].trim().equalsIgnoreCase(Locator.CLASSNAME.toString())) {
				element=driver.findElement(By.className(arrPropertyValue[1].trim()));
			}else if(arrPropertyValue[0].trim().equalsIgnoreCase(Locator.TAGNAME.toString())) {
				element=driver.findElement(By.tagName(arrPropertyValue[1].trim()));
			}else if(arrPropertyValue[0].trim().equalsIgnoreCase(Locator.XPATH.toString())){
				element=driver.findElement(By.xpath(arrPropertyValue[1].trim()));
			}else if(arrPropertyValue[0].trim().equalsIgnoreCase(Locator.CSSSelector.toString())) {
				element=driver.findElement(By.cssSelector(arrPropertyValue[1].trim()));
			}
			arrPropertyValue=null;
			logger.info("getElement method execution completed successfully");

		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Error occured in getElement method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in getElement method"+tbu);
			e.printStackTrace();
			
		}
		return element;
		
	}
	
	/*
	 * isDisplayed method check whether particular object is displayed within page or not
	 */
	
		public Boolean isDisplayed(WebElement element) {
		logger.info("Started isDisplayed method execution");

		Boolean elementDisplayed=false;
		try {
			if(element.isDisplayed()) {
				elementDisplayed=true;
			}
			else{
				elementDisplayed=false;
			}
		}catch(Exception e) {
			logger.info("Error occured in isDisplayed method ");
			e.printStackTrace();
			
		}
		logger.info("isDisplayed method execution completed successfully");
		return elementDisplayed;
	}
		/*
		 * applyExplicitWait method applied Explicit wait on particular object
		 */

	 @SuppressWarnings("deprecation")
	public Boolean applyExplicitWait(WebDriver driver,String objectName,String tbu,String condition , int waitingTime){
		 logger.info("Started applyExplicitWait method execution");
		 Boolean waitSuccessful=false;
		 try
		 {
			
			 WebDriverWait wait = new WebDriverWait(driver,waitingTime);
			 
			 By element = getElementBy( driver, objectName, tbu);
			 
			 if (condition.equals(ExplicitWaitCondition.AlertIsPresent.toString())){
				wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			 }else  if (condition.equals(ExplicitWaitCondition.ElementToBeClickable.toString())){
				 wait.until(ExpectedConditions.elementToBeClickable(element));
				 
			 }else  if (condition.equals(ExplicitWaitCondition.ElementToBeSelected.toString())){
				 wait.until(ExpectedConditions.elementToBeSelected(element));
			 }else  if (condition.equals(ExplicitWaitCondition.FrameToBeAvaliableAndSwitchToIt.toString())){
				 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
			 }else  if (condition.equals(ExplicitWaitCondition.InvisibilityOfTheElementLocated.toString())){
				 wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			 }else  if (condition.equals(ExplicitWaitCondition.InvisibilityOfElementWithText.toString())){
				 wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			 }else  if (condition.equals(ExplicitWaitCondition.PresenceOfAllElementsLocatedBy.toString())){
				 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
			 }else  if (condition.equals(ExplicitWaitCondition.PresenceOfElementLocated.toString())){
				 wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			 }else  if (condition.equals(ExplicitWaitCondition.VisibilityOfElementLocated.toString())){
				 wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			 }
			 waitSuccessful=true;
			 logger.info("applyExplicitWait method execution completed successfully");
		 }catch (Exception e){
			 System.out.println("Error occured in applyExplicitWait");
			 logger.info("Error occured in applyExplicitWait method successfully");
			 e.printStackTrace();
		 }
		return waitSuccessful;
	 }
	 
	 /*
	  * getElementBy method return by object of particular object
	  */

	 public By getElementBy(WebDriver driver,String objectName,String tbu) {
		logger.info("Started getElementBy method execution");
		By byElement =null;
		try {
			Thread.sleep(2000);
			String objectProperty=common.readPropertyValue(ConfigKeyWords.OBJECTPROPERTY.toString(), objectName);
			String[] arrPropertyValue=objectProperty.split("==");
			System.out.println("arrPropertyValue[0] "+arrPropertyValue[0]);
			System.out.println("arrPropertyValue[1] "+arrPropertyValue[1]);
		
		if(arrPropertyValue[1].trim().contains("TBU")) {
			arrPropertyValue[1]=arrPropertyValue[1].replace("TBU", tbu.trim());
			System.out.println("arrPropertyValue[1] :"+arrPropertyValue[1]);
		}
	
		if(arrPropertyValue[0].equalsIgnoreCase(Locator.ID.toString())) {
			byElement=By.id(arrPropertyValue[1].trim());
		}else if(arrPropertyValue[0].equalsIgnoreCase(Locator.NAME.toString())) {
			
			byElement=By.name(arrPropertyValue[1].trim());
		
		}else if(arrPropertyValue[0].equalsIgnoreCase(Locator.LINKTEXT.toString())) {
			byElement=By.linkText(arrPropertyValue[1].trim());
		}else if(arrPropertyValue[0].equalsIgnoreCase(Locator.PARTIALLINKTEXT.toString())) {
			byElement=By.partialLinkText(arrPropertyValue[1].trim());
		}else if(arrPropertyValue[0].equalsIgnoreCase(Locator.CLASSNAME.toString())) {
			byElement=By.className(arrPropertyValue[1].trim());
		}else if(arrPropertyValue[0].equalsIgnoreCase(Locator.TAGNAME.toString())) {
			byElement=By.tagName(arrPropertyValue[1].trim());
		}else if(arrPropertyValue[0].equalsIgnoreCase(Locator.XPATH.toString())) {
			byElement=By.xpath(arrPropertyValue[1].trim());
		}else if(arrPropertyValue[0].equalsIgnoreCase(Locator.CSSSelector.toString())) {
			byElement=By.cssSelector(arrPropertyValue[1].trim());
		}
		arrPropertyValue=null;
		logger.info("getElementBy method execution completed successfully");

		
	}catch(Exception e) {
		logger.info("Error occured in isDisplayed method ");
		report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in isDisplayed method"+tbu);
		e.printStackTrace();
		
		
	}
	return byElement;
	 }
	 
	 
	/*getText method retieve the text displayed for object
	 * 
	 */
	public String getText(WebDriver driver,String objectName,String tbu) {
		logger.info("Started getText method execution");
		String text="";
		try {
			applyExplicitWait(driver, objectName, tbu, ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
			WebElement element=getElement(driver,objectName,tbu);
			if(isDisplayed(element)) {
				text=element.getText();
			}
			logger.info("getText method execution is completed");
		}catch(Exception e) {
			logger.info("Error occured in getText method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in getText method"+tbu);
			e.printStackTrace();
			}
		return text;
	}
	
	/*
	 * scrollIntoView method move to particular object 
	 */
	public String scrollIntoView(WebDriver driver,String objectName,String tbu) {
		logger.info("Started scrollIntoView method execution");
		String text="";
		try {
			Thread.sleep(1000);
			JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
			WebElement element=getElement(driver,objectName,tbu);
			jsexecutor.executeScript("arguments[0].scrollIntoView(true);",element);
			logger.info("scrollIntoView method execution is completed");
		}catch(Exception e) {
			logger.info("Error occured in scrollIntoView method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in scrollIntoView method"+tbu);
			e.printStackTrace();
			}
		return text;
	}
	
	/*getText method retieve the text displayed for object
	 * 
	 */
	public String sendKey(WebDriver driver,String objectName,String tbu,String inputText) {
		logger.info("Started getText method execution");
		String text="";
		try {
			applyExplicitWait(driver, objectName, tbu, ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
			WebElement element=getElement(driver,objectName,tbu);
			if(isDisplayed(element)) {
				element.sendKeys(inputText);
			}
			logger.info("getText method execution is completed");
		}catch(Exception e) {
			logger.info("Error occured in sendKey method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in sendKey method"+inputText);
			e.printStackTrace();
			}
		return text;
	}
	
	/*getText method retieve the text displayed for object
	 * 
	 */
	public String getAttribute(WebDriver driver,String objectName,String tbu,String propertyName) {
		logger.info("Started getText method execution");
		String text="";
		try {
			applyExplicitWait(driver, objectName, tbu, ExplicitWaitCondition.VisibilityOfElementLocated.toString(), Constant.second20);
			WebElement element=getElement(driver,objectName,tbu);
			if(isDisplayed(element)) {
				text=element.getAttribute(propertyName);
			}
			logger.info("getText method execution is completed");
		}catch(Exception e) {
			logger.info("Error occured in getAttribute method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in getAttribute method"+tbu);
			e.printStackTrace();
			}
		return text;
	}
	
	public String scrollIntoView(WebDriver driver,WebElement element) {
		logger.info("Started scrollIntoView method execution");
		String text="";
		try {
			Thread.sleep(1000);
			JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
			jsexecutor.executeScript("arguments[0].scrollIntoView(true);",element);
			logger.info("scrollIntoView method execution is completed");
		}catch(Exception e) {
			logger.info("Error occured in scrollIntoView method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in scrollIntoView method");
			e.printStackTrace();
			}
		return text;
	}
	
	
	public void highlight(WebDriver driver, WebElement ele) {
		logger.info("Started highlight method execution");
		try {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: grey; border: 2px solid yellow;');", ele);
		}catch(Exception e) {
			logger.info("Error occured in highlight method ");
			e.printStackTrace();
			}
    }
	 
	
	public  void click(WebElement element) {
		logger.info("Started click method execution");
		try {
        element.click();
		}catch(Exception e) {
			logger.info("Error occured in click method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in click method");
			
			e.printStackTrace();
			}
		
    }
	
	public Boolean applyExplicitWaitForAlertPresent(WebDriver driver, int waitingTime){
		 logger.info("Started applyExplicitWaitForAlertPresent method execution");
		 Boolean waitSuccessful=false;
		 try
		 {
			 WebDriverWait wait = new WebDriverWait(driver,waitingTime);
			 wait.until(ExpectedConditions.alertIsPresent());
			
		 }catch(Exception e) {
			logger.info("Error occured in applyExplicitWaitForAlertPresent method ");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Error occured in applyExplicitWaitForAlertPresent method");
			e.printStackTrace();
				}
		return waitSuccessful;
	 }
	

	
	/*
	 * 
	 */
	
}
