package ScreenshotForFailedTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


//@Listeners(CustomListner.class)
public class ScreenshotTest extends Base{
	
	@BeforeMethod
	public void setUp() {
		initialisation();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void test1() {
		System.out.println("started test1");
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void test2() {
		System.out.println("started test2");
		Assert.assertEquals(false, true);
	}
	
	@Test
	public void test3() {
		System.out.println("started test3");
		Assert.assertEquals(true, true);
	}

}
