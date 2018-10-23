package HandlingExcelAndWeBtables;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

@Test
public class HandlingWebTables {

	public static WebDriver driver;

	public static void initialisation() throws Exception {


		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.freecrm.com/index.html?e=2");
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.name("username")).sendKeys("naveenk");
		driver.findElement(By.name("password")).sendKeys("test@123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		driver.switchTo().frame("mainpanel");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		HandlingWebTables test = new HandlingWebTables();
		test.find("abc efg");

	}


	public void find(String data) {
		driver.findElement(By.xpath("//*[text()='"+data+"']/parent::td//preceding-sibling::td/input[@type='checkbox']")).click();
		String text = driver.findElement(By.xpath("//*[text()='"+data+"']/parent::td//following-sibling::td[5]//a")).getText();
		System.out.println("text: "+text);
	}

}
