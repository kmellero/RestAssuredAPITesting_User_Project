package com.userapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.userapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.assertion.ResponseTimeMatcher;
import io.restassured.http.Method;


public class TC1_GET_All_Users extends TestBase{

	
	@BeforeClass
	void getAllUsers() throws InterruptedException {
		logger.info("********************* Started TC1_GET_All_Users ******************************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/users");
		
		Thread.sleep(2000);
		
	}
	@Test
	void checkResponseBody() {
		logger.info("********************* checkResponseBody -not null ******************************");
		
		String responseBody=response.getBody().asString();
		System.out.println("responseBody--> "+ responseBody);
		logger.info("Response Body: "+responseBody);
		
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode()	{
		logger.info("********************* checkStatusCode *****************************");
		
		int statusCode = response.getStatusCode();
		System.out.println("statusCode ---> "+statusCode);
		logger.info("statusCode-->"+statusCode);
		Assert.assertEquals(statusCode, 200);

		}
	
	@Test
	void checkResponseTime() {
		logger.info("********************* checkResponseTime ******************************");		
		
		long responseTime = response.getTime();
		logger.info("responseTime-->"+responseTime);
		System.out.println("responseTime-->"+responseTime);
		if(responseTime>=2000)
			logger.warn("Warning: responseTime >=2000!!");
		Assert.assertTrue(responseTime<2000);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********************* checkStatusLine ******************************");		
		
		String statusLine = response.getStatusLine();
		System.out.println("statusLine--> "+statusLine);
		logger.info("statusLine--> "+statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType() {
		logger.info("********************* checkContentType ******************************");		
		
		String contentType = response.header("Content-Type");
		System.out.println("header contentType--> "+contentType);
		logger.info("contentType--> "+contentType);
		
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	void checkServer() {
		logger.info("********************* checkServer ******************************");		
		
		String server = response.header("Server");
		System.out.println("header Server--> "+server);
		logger.info("header Server--> "+server);
		
		Assert.assertEquals(server, "cloudflare");
	}
	
	@Test
	void checkEncodingType() {
		logger.info("********************* checkEncodingType ******************************");		
		
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("header contentEncoding--> "+contentEncoding);
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
		logger.info("********************* Completed TC1_GET_All_Users ******************************");		

	}
	
}
