package com.adidas.test.com.adidas.test.cucumber.api.helper;

import org.json.simple.JSONObject;


import com.adidas.test.com.adidas.test.cucumber.api.configuration.IConfiguration;
import com.adidas.test.com.adidas.test.cucumber.api.constant.EndPoints;
import com.adidas.test.com.adidas.test.cucumber.api.constant.EnumConstant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

public class PetServiceHelper  extends EnumConstant implements IConfiguration{
	Logger logger = Logger.getLogger(PetServiceHelper.class.getName());

	public PetServiceHelper() {
		RestAssured.baseURI=BASE_URL;
		logger.info("Set base URI"+ BASE_URL);
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	public Response getPetByStatus(String  petStatus){
		logger.info("getPetByStatus method execution started");
		Response response=null;
		try {
		RequestSpecification request = RestAssured.given();
		RestAssured.given().auth().basic(USERNAME.trim(), PASSWORD.trim());
		RestAssured.given().header("Accept", ContentType.JSON.getAcceptHeader());
		
		if(!petStatus.trim().equals("")) {
			EndPoints.GET_FINDBYSTATUS=EndPoints.GET_FINDBYSTATUS.replace("TBU", petStatus.trim());
			response=request.get(EndPoints.GET_FINDBYSTATUS);
			logger.info("get method executed succewfully"+EndPoints.GET_FINDBYSTATUS);
			
			
		}else {
			System.out.println("Invalid endpoint URL.Please provide proper endpoint URL");
			logger.error("Invalid endpoint URL.Please provide proper endpoint URL");
			response=null;
			System.exit(1);
		}
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Error occured in getPetByStatus method");
			System.exit(1);
		}
		return response;
	}
	
	public  Response createPetService(String id,String name ,String status) {
		logger.info("createPetService method execution started");
		
		Response response=null;
		try {
			RequestSpecification request = RestAssured.given();
			RestAssured.given().auth().basic(USERNAME.trim(),PASSWORD.trim());
			RestAssured.given().header("Accept", ContentType.JSON.getAcceptHeader());
			
			System.out.println("request created");

			JSONObject requestParams = new JSONObject();
			requestParams.put("id", id);
			requestParams.put("name", name);
			requestParams.put("status", status);
			
			request.header("Content-Type","application/json");
			request.body(requestParams.toJSONString());
			logger.info("Created json  with id:"+id + " name :"+name + " status:"+status);
			
			response = request.post(EndPoints.CREATEPET);
			logger.info("Post method executed sucessfully :"+EndPoints.CREATEPET);
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Error occured in createPetService method");
			System.exit(1);
		}
		return response;
	}
	
public  Response updatePetService(String id,String name,String field ,String value) {
		logger.info("updatePetService method execution started");
	
		Response response=null;
		try {
			
			RequestSpecification request = RestAssured.given();
			RestAssured.given().auth().basic(USERNAME.trim(),PASSWORD.trim());
			RestAssured.given().header("Accept", ContentType.JSON.getAcceptHeader());
			
			System.out.println("request created");

			JSONObject requestParams = new JSONObject();
			requestParams.put("id", id);
			requestParams.put("name", name);
			requestParams.put("status", value);
			
			request.header("Content-Type","application/json");
			request.body(requestParams.toJSONString());
			logger.info("json  created with id:"+id + " " + field + " with value :"+value);
			
			response = request.put(EndPoints.UPDATEPET);
			logger.info("Put method executed sucessfully :"+EndPoints.UPDATEPET);
		
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Error occured in updatePetService method");
			System.exit(1);
		}
		return response;
	}

public  Response deletePetService(String id) {
	logger.info("deletePetService method execution started");
	
	Response response=null;
	try {
	
		RequestSpecification request = RestAssured.given();
		RestAssured.given().auth().basic(USERNAME.trim(),PASSWORD.trim());
		RestAssured.given().header("Accept", ContentType.JSON.getAcceptHeader());
		
		EndPoints.DELETEPET=EndPoints.DELETEPET.replace("TBU", id.trim());
		System.out.println("EndPoints.DELETEPET : "+EndPoints.DELETEPET);
		request.header("Content-Type","application/json");
		
		response = request.delete(EndPoints.DELETEPET);
		
		System.out.println(" Delete response body"+response.asString());
		logger.info("Delete method executed sucessfully :"+EndPoints.DELETEPET);
		
	
	} catch(Exception e) {
		e.printStackTrace();
	}
	return response;
}

}
