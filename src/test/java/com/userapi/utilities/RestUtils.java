package com.userapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	//random generated names and jobs
	public static String eName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("Jonasz"+generatedString);
	}
	
	public static String eJob() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return("lead "+generatedString);
	}
}
