package TestNG;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class TestNGReports {

	@Test(priority=2)
	public void reportsTest(){
//		System.out.println("Output of this Reporter can be seen in test-output folder,index.html  file");
//		System.out.println(Reporter.getCurrentTestResult());
		
		Reporter.log("Output of this Reporter can be seen in test-output folder,index.html  file ", false);
		
	}
	
	@Test(priority=1)
	public void reportsTest1(){
		System.out.println("Output of this Reporter can be seen in test-output folder,index.html  file");
		System.out.println(Reporter.getCurrentTestResult());
		
		//Reporter.log("Output of this Reporter can be seen in test-output folder,index.html  file "+Reporter.getCurrentTestResult(), true);
		
	}
	
	@AfterMethod
	public void afterMethod(){
		System.out.println(Reporter.getCurrentTestResult());
	}

}
