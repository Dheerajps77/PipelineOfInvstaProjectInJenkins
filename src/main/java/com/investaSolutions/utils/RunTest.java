package com.investaSolutions.utils;
import java.io.File;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class RunTest {
	
	
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();	
		//suites.add("C:\\Users\\dheeraj.singh\\git\\JavaFrameWork2\\InvFrameworkDemo\\testng.xml");//path to xml..in src
		suites.add("src"+File.separator+"main"+File.separator+"resources"+File.separator+"testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}
	
	
	/*
	
	@Test
	public void RunXmlFile()
	{
		
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();	
		//suites.add("C:\\Users\\dheeraj.singh\\git\\JavaFrameWork2\\InvFrameworkDemo\\testng.xml");//path to xml..in src
		suites.add("src"+File.separator+"main"+File.separator+"resources"+File.separator+"testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}
	*/
}
