package com.softserve.edu;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.CSVUtils;

public class AppTest {
	public static final Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	@Test
	public void testApp() {
		//System.out.println("class AppTest, testApp()");
		logger.info("class AppTest, testApp()");
		Reporter.log("<br><p>class AppTest, testApp()</p>");
		//System.out.println("*****Path = " + AppTest.class
		System.out.println("*****Path = " + this.getClass()
				.getResource("/user.csv").getPath().substring(1));
//		for (List<String> list : (new CSVUtils()).getAllCells(AppTest.class
//				.getResource("user.csv").getPath().substring(1)) ) {
//			System.out.println(list);
//		}
		Assert.assertTrue(true);
	}

}
