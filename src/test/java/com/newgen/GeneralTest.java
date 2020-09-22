package com.newgen;

public abstract class GeneralTest {
	
	public String entering(String methodName) {
		return String.format("Entering %s", methodName);
	}
	
	public String parameters() {
		return String.format("| %-25s| %-25s | %-100s |","Paramter Type","Parameter Name","Parameter Value");
	}
	
	public String inputParameter(String paramType, String paramName, String value) {
		return	String.format("| %-25s| %-25s | %-100s |", paramType,paramName, value);		
	}
	
	public String exiting(String methodName) {
		return String.format("Exiting %s", methodName);
	}
}
