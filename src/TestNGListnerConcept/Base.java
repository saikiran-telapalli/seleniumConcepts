package TestNGListnerConcept;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	public static WebDriver driver;

	public static void initialisation() {
		
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://google.com");
		
	}
	
	public String timeStamp() {
		Date d = new Date();
		Timestamp ts = new Timestamp(d.getTime());
		String time = ts.toString();
		time= time.replaceAll(":", "-");
		time= time.replaceAll("-", "_");
		time= time.substring(0, 19);
		System.out.println(time);
		
		
		return time;
		
		
	}
	
	public void failed(String testMethodName) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("/Users/saikiran/eclipse-workspace/SeleniumDifferentConcepts/Screenshots/"+testMethodName+" "+timeStamp()+".jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
