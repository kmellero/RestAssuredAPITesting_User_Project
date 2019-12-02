package com.userapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.userapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC5_DELETE_User_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void deleteSingleUser() throws InterruptedException {
		logger.info("********************* Started TC5_deleteSingleUser ******************************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/users");
		
		//get JsonPath object instance from the Response
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//capture id  from the above response, [0] is first key=id, .id?, then this id is passed to a local userId, not from TestBase class
		String userId=jsonPathEvaluator.get("[0].id");
		
		
		response = httpRequest.request(Method.DELETE, "/users/"+userId);
		
		Thread.sleep(3000);
	}
	@Test
	void checkResponseBody()	{	
		
		logger.info("********************* checkResponseBody-deleted record******************************");
		
		String responseBody=response.getBody().asString();
		logger.info("responseBody---> "+responseBody);

		Assert.assertEquals(responseBody.contains(""), true);
	}

	@Test
	void checkStatusCode()	{
		logger.info("********************* checkStatusCode *****************************");
		
		int statusCode = response.getStatusCode();
		logger.info("statusCode-->"+statusCode);

		Assert.assertEquals(statusCode, 204);
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
		
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
	}
	
	@Test
	void checkServer() {
		logger.info("********************* checkServer ******************************");		
		
		String server = response.header("Server");
		logger.info("header Server--> "+server);
		
		Assert.assertEquals(server, "cloudflare");
	}
	@Test
	
	void checkContentLength() {
		logger.info("********************* checkContentLength ******************************");		
		
		String contentLength = response.header("Content-Length");
		logger.info("header contentLength--> "+contentLength);
		
		Assert.assertEquals(contentLength, "0");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("********************* Completed TC5_deleteSingleUser record ******************************");		
	}
}
