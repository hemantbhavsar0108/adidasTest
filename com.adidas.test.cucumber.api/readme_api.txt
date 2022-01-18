
	I have implemented https://petstore3.swagger.io/ using following
		- Cucumber (separate package)
		- TestNG(separate package)
		- Generating log file using log4

	I have implemented following method

		Get- /pet/findByStatus		- Finds Pets by status
		Post - /pet  			- Add a new pet to the store
		Put - /pet 			- Update an existing pet
		Delete	- /pet/{petId}		- Deletes a pet



	I have used following folder structure for this framework.
		com.adidas.test.cucumber.api
			-src/main/java
				- com.adidas.test.com.adidas.test.cucumber.api.configuration
					- IConfiguration (interface)
				- com.adidas.test.com.adidas.test.cucumber.api.constant
					- EndPoints
					- EnumConstant
				- com.adidas.test.com.adidas.test.cucumber.api.helper	
					- PetServiceHelper


			- src/test/java

				-com.adidas.test.com.adidas.test.cucumber.api.cucumber.features
					- petStoreAPIMethodExecution.feature
				-com.adidas.test.com.adidas.test.cucumber.api.cucumber.stepdefinition

				-com.adidas.test.com.adidas.test.cucumber.api.cucumber.testrunner

				-com.adidas.test.com.adidas.test.cucumber.api.testapivalidation

				-com.adidas.test.com.adidas.test.cucumber.api.testngsuite


			-logs
	
			-Report

			- TestData
