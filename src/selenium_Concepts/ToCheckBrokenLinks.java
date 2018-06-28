package selenium_Concepts;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;





public class ToCheckBrokenLinks {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("https://www.moolya.global/");
		
		List<WebElement> links_List = driver.findElements(By.tagName("a"));
		links_List.addAll(driver.findElements(By.tagName("img")));
		
		System.out.println("size of full links and images-------->"+links_List.size());
		
		List<WebElement> ativeLinks = new ArrayList<WebElement>();
		
		for(int i=0; i<links_List.size(); i++) {
			if(links_List.get(i).getAttribute("href") != null) {
				ativeLinks.add(links_List.get(i));
			}
		}
		
		System.out.println("size of all active links and images-------->"+ativeLinks.size());
		
		/*
		 * Check the href url, with httpconnection api:
		 * 200 -- OK
		 * 404 -- Not Found
		 * 500 -- Internal Error
		 * 400 -- Bad Request
		 */
		for(int j=0; j<ativeLinks.size(); j++) {
			HttpURLConnection connection = (HttpURLConnection) new URL(ativeLinks.get(j).getAttribute("href")).openConnection();
			
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			System.out.println(ativeLinks.get(j).getAttribute("href")+"----->"+response);
		}
	}

}
