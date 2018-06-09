package selenium_Concepts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class googleSearch {
	
	public static void test() {
		
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("window-size=1400,800");
//		options.addArguments("headless");	
//		WebDriver driver = new ChromeDriver(options);
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		
		List<WebElement> listLinks = driver.findElements(By.tagName("a"));
		System.out.println("No.of links on the page: "+listLinks.size());
		
		for(int i=0; i<listLinks.size(); i++) {
			System.out.println(listLinks.get(i).getText());
		}
		
		driver.findElement(By.id("lst-ib")).sendKeys("rak");
		
		List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbqs_c']"));
		System.out.println("No of suggestions: "+list.size());
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getText());
			if(list.get(i).getText().equalsIgnoreCase("rakesh")) {
				list.get(i).click();
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		test();
	}

}
