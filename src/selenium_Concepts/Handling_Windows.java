package selenium_Concepts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Handling_Windows {

	public static WebDriver driver;

	public static void main(String[] args) {

		// Create a new instance of the Firefox driver

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");

		// Store and Print the name of the First window on the console
		String parentWindow= driver.getWindowHandle();
		System.out.println(parentWindow);

		// Click on the Button "New Message Window"
		driver.findElement(By.name("New Message Window")).click();
		
		// Store and Print the name of all the windows open	              
		Set handles = driver.getWindowHandles();
		System.out.println(handles);

		// Pass a window handle to the other window
		for (String handle1 : driver.getWindowHandles()) {
			System.out.println(handle1);
			driver.switchTo().window(handle1);
			if(driver.getTitle().equalsIgnoreCase("Expected title")) {
				System.out.println("Stop the loop we are in expected window");
				break;
			}

		}
		
		//switch back to parent window
		driver.switchTo().window(parentWindow);
		
		// Closing Pop Up window
		driver.close();

		// Close Original window
		driver.quit();

	}

}


