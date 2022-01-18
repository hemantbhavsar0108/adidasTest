package com.adidas.test.com.adidas.test.cucumber.api.testngsuite;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.adidas.test.com.adidas.test.cucumber.api.configuration.IConfiguration;
import com.adidas.test.com.adidas.test.cucumber.api.constant.EnumConstant;
import com.adidas.test.com.adidas.test.cucumber.api.helper.PetServiceHelper;
import com.adidas.test.com.adidas.test.cucumber.api.testapivalidation.APIVaidation;

import io.restassured.response.Response;

public class Testcase003_putMethodExecution extends EnumConstant implements IConfiguration {
	
	@Test
	public void putMethodExecution() {
		
		PetServiceHelper petServiceHelper = new PetServiceHelper();
		APIVaidation apiValidation=new APIVaidation();
		EnumConstant enumobj=new EnumConstant();
		Response response=null;
		 response=petServiceHelper.updatePetService("56", "name","Status","sold");
		Boolean executionSucessful=apiValidation.validateStausCode(response, HttpStatus.SC_OK);
		if (executionSucessful) {
			apiValidation.displayResponse(response, "56");
			apiValidation.validateResponseHeader(response);
		}
		else {
			System.out.println("Endpoint not executed successfully");
		}
	}

				
	}


