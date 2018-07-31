package selenium_Concepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//How to switch over the frame, if we CANNOT switch using ID or Web Element:


public class Handling_Frames {


	public static WebDriver driver;

	public static void main(String[] args) {

		// Create a new instance of the Firefox driver

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");

		int Frame_count = driver.findElements(By.tagName("iframe")).size();
		System.out.println(Frame_count);

		for(int i=0; i<=Frame_count; i++){
			driver.switchTo().frame(i);
			if(driver.findElement(By.name("somewebelemnt")).isDisplayed()) {
				break;
			}
		}

	}
}
