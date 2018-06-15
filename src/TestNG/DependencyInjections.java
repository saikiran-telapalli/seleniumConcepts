package TestNG;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DependencyInjections {

	@BeforeMethod
	public void beforeMethod(){
		System.out.println("BeforeMethod DependencyInjections");
	}

	@Test(dependsOnMethods="test")
	public void main(){

		System.out.println("Main DependencyInjections");

	}

	@Test(dependsOnGroups="group1")
	public void test(){
		System.out.println("Test DependencyInjections");
		Assert.assertTrue(false);
	}

	@Test(groups="group1")
	public void test2(){
		System.out.println("Test2 DependencyInjections");
	}

	@AfterMethod
	public void afterMethod(ITestResult result){

		if(result.getStatus()==result.FAILURE){ //2
			System.out.println("Test Falied, testname="+result.getName());
		}
		else if(result.getStatus()==result.SUCCESS){ // 1
			System.out.println("Test Passed, testname="+result.getName());
		}
		else if(result.getStatus()==result.SKIP){ // 3
			System.out.println("Test Skipped, testname="+result.getName());
		}

		//System.out.println(result.SKIP);

		switch(result.getStatus()){


		}

		System.out.println("AfterMethod DependencyInjections");

	}
}
