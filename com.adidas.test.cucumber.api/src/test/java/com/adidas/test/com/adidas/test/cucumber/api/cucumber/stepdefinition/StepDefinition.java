package com.adidas.test.com.adidas.test.cucumber.api.cucumber.stepdefinition;

import org.apache.http.HttpStatus;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.adidas.test.com.adidas.test.cucumber.api.constant.EnumConstant;
import com.adidas.test.com.adidas.test.cucumber.api.constant.EnumConstant.PetOption;
import com.adidas.test.com.adidas.test.cucumber.api.helper.PetServiceHelper;
import com.adidas.test.com.adidas.test.cucumber.api.testapivalidation.APIVaidation;

import io.cucumber.core.gherkin.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class StepDefinition {
	PetServiceHelper petServiceHelper;
	APIVaidation apiValidation;
	EnumConstant enumobj;
	Response response;
	Boolean executionSucessful=false;
	Logger logger = Logger.getLogger(StepDefinition.class.getName());
	public static Scenario scenario;
	
	
	
	@Given("Access get pet store method")
	public void access_get_pet_store_method() {
		petServiceHelper = new PetServiceHelper();
		apiValidation=new APIVaidation();
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("set up the framework");
	}

	@When("Get pet information based on status {string} method  executed")
	public void get_pet_information_based_on_status_method_executed(String status) {
		System.out.println("petStatus in framework : "+status);
		response=petServiceHelper.getPetByStatus(status);
		logger.info("get method executed succesfully");
	}

	@Then("Validate response status code for get pet status method execution")
	public void validate_response_status_code_for_get_pet_status_method_execution() {
		System.out.println("get method execution started");
		executionSucessful=apiValidation.validateStausCode(response, HttpStatus.SC_OK);
	}

	@Then("Validate the reponse body for get pet status method execution")
	public void validate_the_reponse_body_for_get_pet_status_method_execution() {
		if (executionSucessful) {
			apiValidation.displayResponse(response, "id");
			
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}

	@Then("Validate the response header for get pet status method execution")
	public void validate_the_response_header_for_get_pet_status_method_execution() {
	    
		if (executionSucessful) {
			apiValidation.validateResponseHeader(response);
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}

	//Create Method

	@Given("Access create pet store method")
	public void access_create_pet_store_method() {
		petServiceHelper = new PetServiceHelper();
		apiValidation=new APIVaidation();
		
	}
	
	@When("Create new pet method  executed with id {string} and name {string} and status {string}")
	public void create_new_pet_method_executed_with_id_and_name_and_status_and_photourl_and_tag(String id, String name, String status) {
		System.out.println("Create method execution started");
		
		response=petServiceHelper.createPetService(id, name, status);
	}
	
	@Then("Validate response status code for Create new pet execution")
	public void validate_response_status_code_for_Create_new_pet_execution() {
		executionSucessful=apiValidation.validateStausCode(response, HttpStatus.SC_OK);
	}
	
	@Then("Validate the reponse body for Create new pet execution")
	public void validate_the_reponse_body_for_Create_new_pet_execution() {
		
		if (executionSucessful) {
			apiValidation.displayResponse(response, "id");
			
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}
	
	@Then("Validate the response header for Create new pet method execution")
	public void validate_the_response_header_for_Create_new_pet_method_execution() {
		
		if (executionSucessful) {
			apiValidation.validateResponseHeader(response);
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}
	
	// Update method scenario creation
	@Given("Access Update pet store method")
	public void access_Update_pet_store_method() {
		petServiceHelper = new PetServiceHelper();
		apiValidation=new APIVaidation();
	}


	@When("Update new pet method  executed with id {string} and name {string} and field {string} with {string}")
	public void update_new_pet_method_executed_with_id_and_name_and_field_with(String id, String name, String field, String value) {
    
		 response=petServiceHelper.updatePetService(id,name,field,value);

	}

	@Then("Validate response status code {string} for update new pet execution")
	public void validate_response_status_code_for_update_new_pet_execution(String statuscode) {
		executionSucessful=apiValidation.validateStausCode(response, Integer.valueOf(statuscode));
	}

	@Then("Validate the reponse body with id {string} for update new pet execution")
	public void validate_the_reponse_body_with_id_for_update_new_pet_execution(String value) {
	  
		if (executionSucessful) {
			apiValidation.displayResponse(response, value);
			
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}

	@Then("Validate the response header for update new pet method execution")
	public void validate_the_response_header_for_update_new_pet_method_execution() {
	  
		if (executionSucessful) {
			apiValidation.validateResponseHeader(response);
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}
	
	
	//Delete pet
	@Given("Access delete pet store method")
	public void access_delete_pet_store_method() {
		petServiceHelper = new PetServiceHelper();
		apiValidation=new APIVaidation();
		
	}
	@When("Delete pet method  executed with id {string}")
	public void delete_pet_method_executed_with_id(String id) {
	    System.out.println("Delete method execution started");
		 response=petServiceHelper.deletePetService(id.trim());
		
	}

	@Then("Validate response status code {string} for delete pet execution")
	public void validate_response_status_code_for_delete_pet_execution(String statuscode) {
	 
		executionSucessful=apiValidation.validateStausCode(response, Integer.valueOf(statuscode));
	}	

	@Then("Validate the reponse body with message {string} for delete pet execution")
	public void validate_the_reponse_body_with_message_for_delete_pet_execution(String message) {
	    
		if (executionSucessful) {
			apiValidation.displayResponse(response, message.trim());
			
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}

	}


	@Then("Validate the response header for delete pet method execution")
	public void validate_the_response_header_for_delete_pet_method_execution() {
	    
		if (executionSucessful) {
			apiValidation.validateResponseHeader(response);
		}
		else {
			System.out.println("Endpoint not executted successfully");
		}
	}

	
}
