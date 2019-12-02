package com.userapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.userapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_GET_Single_User extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getSingleUser() throws InterruptedException {
		logger.info("********************* Started TC2_GET_Single_User ******************************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/users/"+userId);
		
		Thread.sleep(2000);
	}

	
	@Test
	void checkResponseBody() {
		logger.info("********************* checkResponseBody -not null ******************************");
		
		String responseBody=response.getBody().asString();
		logger.info("Response Body: "+responseBody);
		
		Assert.assertTrue(responseBody!=null);
	}
	@Test
	void checkUserId() {
		logger.info("********************* checkUserId ******************************");
		
		String responseBody=response.getBody().asString();

		Assert.assertEquals(responseBody.contains(userId), true);
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
		if(responseTime>=4000)
			logger.warn("Warning: responseTime >=4000!!");
		Assert.assertTrue(responseTime<4000);
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
	
/*	@Test
	void getCookie(){
		logger.info("********************* getCookie ******************************");		
		
		String ecookie = response.getCookie("__cfduid");
		System.out.println("cookie--> "+ecookie);
		logger.info("ecookie--> "+ecookie);
		
		Assert.assertEquals(ecookie, "d19b7f14b0dbfe219a5d1ccb9854dbf061572528600");
		}
*/
	
	@AfterClass
	void tearDown() {
		logger.info("********************* Completed TC2_GET_Single_User ******************************");		

	}
}
