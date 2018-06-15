package TestNG;

import org.testng.annotations.Test;

public class GroupAnnotations {

	@Test(description="Test Case 1",groups="login")
	public static void test1(){
		System.out.println("GroupsAnnotation test1");
	}
	
	@Test(groups="logout",priority=1,dependsOnGroups={"login"})  /*It checks and executes dependsonMethods even if that 
																group is not defined in testng.xml <run> <inclue> methods */
	public void test2(){
		System.out.println("GroupsAnnotation test2");
	}
	
	@Test(groups="reg",priority=2)
	public static void test3(){
		System.out.println("GroupsAnnotation test3");      		//Execution will be done wrt Alphabetic order
	}
	
	@Test(groups="login",priority=2)
	public void test(){
		System.out.println("GroupsAnnotation test4");
	}
	
	@Test(groups="reg",enabled=false)							//If enabled = false then that test case will not be executed
	public static void test5(){
		System.out.println("GroupsAnnotation test5");
	}
	
	@Test(alwaysRun=true,groups="logout")
	public void test6(){
		System.out.println("GroupsAnnotation test6");
	}
}
