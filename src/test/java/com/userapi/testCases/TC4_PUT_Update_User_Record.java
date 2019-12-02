package com.userapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.userapi.base.TestBase;
import com.userapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_PUT_Update_User_Record extends TestBase{


	RequestSpecification httpRequest;
	Response response;

	String ename=RestUtils.eName();
	String ejob=RestUtils.eJob();
	
	
	@BeforeClass
	void createUser() throws InterruptedException {
		logger.info("********************* Started TC3_POST_user ******************************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();

		//POST data are in the hash (key:value) format.
		JSONObject requestparams  = new JSONObject();
		requestparams.put("name", ename);  
		requestparams.put("job", ejob);  
		logger.info("ename-->"+ename+"  ejob--->"+ejob);
		//specify the format of the data in header: Json
		httpRequest.header("Content-Type","application/json");
		//attach above data to the request body, convert hash format to json format
		httpRequest.body(requestparams.toJSONString());
		
		//send request and get the response
		response = httpRequest.request(Method.PUT, "/users/"+userId);
		
		Thread.sleep(5000);  //Must load and return to go to @Test's. Implicit or explicit wait times cannot use, because selenium driver is not used.
	}

	@Test
	void checkResponseBody()	{	
		
		logger.info("********************* checkResponseBody-posted record******************************");
		
		String responseBody=response.getBody().asString();
		logger.info("responseBody---> "+responseBody);

		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(ejob), true);
	}
	
	@Test
	void checkStatusCode()	{
		logger.info("********************* checkStatusCode *****************************");
		
		int statusCode = response.getStatusCode();
		logger.info("statusCode-->"+statusCode);

		Assert.assertEquals(statusCode, 200);
		}
	
	@Test
	void checkResponseTime() {
		logger.info("********************* checkResponseTime ******************************");		
		
		long responseTime = response.getTime();
		logger.info("responseTime-->"+responseTime);
		if(responseTime>=5000)
		logger.warn("Warning: responseTime >=5000!!");

		Assert.assertTrue(responseTime<5000);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********************* checkStatusLine ******************************");		
		
		String statusLine = response.getStatusLine();
		logger.info("statusLine--> "+statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("********************* checkContentType ******************************");		
		
		String contentType = response.header("Content-Type");
		logger.info("contentType--> "+contentType);
		
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	void checkServer() {
		logger.info("********************* checkServer ******************************");		
		
		String server = response.header("Server");
		logger.info("header Server--> "+server);
		
		Assert.assertEquals(server, "cloudflare");
	}
	
	@Test
	void checkEncodingType() {
		logger.info("********************* checkEncodingType ******************************");		
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("header contentEncoding--> "+contentEncoding);
		
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("********************* Completed TC3_POST_user - post record ******************************");		
	}
}
