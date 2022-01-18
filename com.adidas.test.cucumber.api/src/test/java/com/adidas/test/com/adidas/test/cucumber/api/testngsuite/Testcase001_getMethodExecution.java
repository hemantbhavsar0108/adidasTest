package com.adidas.test.com.adidas.test.cucumber.api.testngsuite;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.adidas.test.com.adidas.test.cucumber.api.configuration.IConfiguration;
import com.adidas.test.com.adidas.test.cucumber.api.constant.EnumConstant;
import com.adidas.test.com.adidas.test.cucumber.api.helper.PetServiceHelper;
import com.adidas.test.com.adidas.test.cucumber.api.testapivalidation.APIVaidation;

import io.restassured.response.Response;

public class Testcase001_getMethodExecution extends EnumConstant implements IConfiguration{
	
	@Test
	public void getMethodExecution() {
		PetServiceHelper petServiceHelper = new PetServiceHelper();
		APIVaidation apiValidation=new APIVaidation();
		EnumConstant enumobj=new EnumConstant();
		
		Response response=petServiceHelper.getPetByStatus(PetOption.AVAILABLE.toString());
		Boolean executionSucessful=apiValidation.validateStausCode(response, HttpStatus.SC_OK);
		if (executionSucessful) {
			apiValidation.displayResponse(response, "id");
			apiValidation.validateResponseHeader(response);
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}


}

