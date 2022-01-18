package com.adidas.test.com.adidas.test.cucumber.api.testapivalidation;

import java.util.List;


import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.apache.log4j.Logger;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class APIVaidation {
	static Logger logger = Logger.getLogger(APIVaidation.class.getName());
	
	public Boolean validateStausCode(Response response,int expectedStatusCode) {
		logger.info("validateStausCode method execution started");
		Boolean success=false;
		try {
			int  statuscode=response.getStatusCode();
			logger.info("Displayed Status code :"+statuscode);
			
			Assert.assertEquals(HttpStatus.SC_OK, expectedStatusCode, "Status code validation :");
			success=true;
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Error occured in validateStausCode method");
			System.exit(1);
		}
		
		return success;
	}
	
	public void displayResponse(Response response,String responseValidationText) {
		logger.info("displayResponse method execution started");
		try {
			String responceBodyString=response.body().asString();
			logger.info("Displayed responce body  :"+responceBodyString);
			if(!responceBodyString.trim().equals("")) {
				
				logger.info("Validating the resonse body with text :"+responseValidationText);
				
				Assert.assertTrue(responceBodyString.trim().contains(responseValidationText.trim()), "Response body contains  "+ responseValidationText + " is successful");
			
				String[] arrResponceBody=responceBodyString.split(",");
					
				for(String data:arrResponceBody) {
					System.out.println(data);
				}
			}
			else
			{
				logger.info("Response body is not generated");
				System.exit(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Error occured in displayResponse method");
			System.exit(1);
		}
	}
	
	public void validateResponseHeader(Response response) {
		logger.info("validateResponseHeader method execution started");
		
		try {
			Headers responseHeader=response.getHeaders();
			logger.info("Displaying response header");
			for(Header header:responseHeader) {
				
				System.out.println(header.getName()+" : "+header.getValue());
				logger.info(header.getName()+" : "+header.getValue());
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Error occured in validateResponseHeader method");
			System.exit(1);
		}
	}
}
