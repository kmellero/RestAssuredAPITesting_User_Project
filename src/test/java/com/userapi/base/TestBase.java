package com.userapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	public String userId="1";  //hard coded - input for POST, GET and PUT of a single user  respnseBody-->{"name":"EW43","job":"No7b","id":"299","createdAt":"2019-11-27T17:31:21.676Z"}

	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		logger=Logger.getLogger("UserRestAPI");  //added Logger e.g. "EmployeesRestAPI", usually project name
		PropertyConfigurator.configure("log4j.properties"); //added Logger
		logger.setLevel(Level.DEBUG);   
	}

}
