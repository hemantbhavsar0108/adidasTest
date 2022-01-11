I have implemented framework with following for this test.
	- Cucumber (separate package)
	- TestNG(separate package)
	- Generating log file using log4
	- Generting HTML report

As per my experience, application is not working properly
Once I select the product_1 and added the same in the Cart. Then I am moving to Home page and select product_2 and add the same in the cart, it display only product_2. 
I have implemented delete cart functionality but commented the code to cover other functionality required for the test. Due to time constraint , I have not developed 2 separate scenario.

I have used following folder structure for this framework.
com.adidas.test.cucumberweb
	-src/main.java
		- browser
			-  BrowserFactory
		- common
			- CommonFunction
		- configuration
		- constant
			- Constant
			-IFrameworkConstants (interface)
		- global
			- GlobalDeclaration
		- report
			- ReportLib
		- testData
			- TestData
		- webdriver
			- WebDriverFunctions
	- src/ test/java
		- feature
		- stepDefinition
		- testing.testSuite
		- testRunner
		- uiFunction

-	Report
	HTML report store here
-	Configuration
-	Logs
-	Object.repository
-	Test Data
-	
