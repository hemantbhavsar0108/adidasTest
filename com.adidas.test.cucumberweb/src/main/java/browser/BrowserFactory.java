package browser;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import common.CommonFunction;
import constant.IFrameworkConstants;
import constant.IFrameworkConstants.TestExecutionStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import report.ReportLib;

public class BrowserFactory implements IFrameworkConstants {
	
	static Logger logger = Logger.getLogger(BrowserFactory.class.getName());  
	ReportLib report = new ReportLib();
	/*
	 * launchApplication method launch the browser based on url and browser name provided in config properties
	 */
	WebDriver driver = null;
	
	public WebDriver launchApplication()
	 {
		logger.info("launchApplication method execution started");
		try{
			Thread.sleep(1000);
			CommonFunction common= new CommonFunction();
			String projectPath=common.getProjectPath();
			String browser=common.getConfigProperty(ConfigKeyWords.BROWSER.toString());
		
			String url=common.getConfigProperty(ConfigKeyWords.URL.toString());
	
			 if(browser.equalsIgnoreCase(Browser.IE.toString())) {
				 WebDriverManager.iedriver().setup();
				 driver = new InternetExplorerDriver();
			 } else if (browser.equalsIgnoreCase(Browser.FIREFOX.toString())) {
				 WebDriverManager.firefoxdriver().setup();
				 logger.info("WebDriver manager setup completed");
				 driver = new FirefoxDriver();
			 }
			 else if (browser.equalsIgnoreCase(Browser.CHROME.toString())) {
				 	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					//set up chrome option here
					chromePrefs.put("download.directory_upgrade", "true");
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.prompt_for_download", "false");
					chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
					chromePrefs.put("safebrowsing.enabled", "true");

					ChromeOptions options = new ChromeOptions();
					
					options.setExperimentalOption("prefs", chromePrefs);
					options.addArguments("--test-type");
					options.addArguments("--disable-extensions");
					options.addArguments("--safebrowsing-disable-extension-blacklist");
					options.addArguments("--safebrowsing-disable-download-protection");
					options.addArguments("--ignore-certificate-errors");
					options.addArguments("--disable-gpu");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(options);
			 }
			 //Launch the URL here 
			 driver.navigate().to(url);
			 
			 logger.info("Launch the application URL " + url + " on Browser " + browser);
			 driver.manage().deleteAllCookies();
			 driver.manage().window().maximize();
			 logger.info("Launch the application URL " + url + " on Browser " + browser);
			 report.reportData(TestExecutionStatus.PASS.toString(), "Launch the application URL " + url + " on Browser " + browser);
				 System.out.println("Launch the application URL " + url + " on Browser " + browser);
		}catch(Exception e)
		{
			logger.info("Application unable to launch successly. Please check");
			report.reportData(TestExecutionStatus.FAIL.toString(), "Application unable to launch successly. Please check");
			System.out.println("Application unable to launch successly. Please check");
			e.printStackTrace();
		}
		return driver;
}
}
