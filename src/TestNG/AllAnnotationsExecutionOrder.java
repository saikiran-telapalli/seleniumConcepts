package TestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AllAnnotationsExecutionOrder {

	@BeforeSuite
	public void beforeSuite(){
		System.out.println("AllAnnotationsExecutionOrder beforeSuite");
	}

	@BeforeTest
	public void beforeTest(){
		System.out.println("AllAnnotationsExecutionOrder beforeTest");
	}

	@BeforeClass
	public void beforeClass(){
		System.out.println("AllAnnotationsExecutionOrder beforeClass");
	}
	

	@BeforeMethod
	public void beforeMethod(){
		System.out.println("AllAnnotationsExecutionOrder beforeMethod");
	}

	@Test(priority=1)
	public void test1(){
		System.out.println("AllAnnotationsExecutionOrder test1");
	}

	@Test
	public void test2(){
		System.out.println("AllAnnotationsExecutionOrder test2");  //Test that don,t have priority will execute first
	}

	@AfterMethod
	public void afterMethod(){
		System.out.println("AllAnnotationsExecutionOrder afterMethod");
	}

	@AfterClass
	public void afterClass(){
		System.out.println("AllAnnotationsExecutionOrder afterClass");
	}

	@AfterTest
	public void afterTest(){
		System.out.println("AllAnnotationsExecutionOrder afterTest");
	}

	@AfterSuite
	public void afterSuite(){		
		System.out.println("AllAnnotationsExecutionOrder afterSuite");
	}


	//Output

	/*AllAnnotationsExecutionOrder 
	beforeSuite
 	beforeTest
	beforeClass
 	beforeMethod
 	test2
 	afterMethod
 	beforeMethod
 	test1
 	afterMethod
 	afterClass
	afterTest
	afterSuite*/

}
