package selenium_Concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ImageTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();

		driver.get("https:qaapp.moolya.global/");
		driver.findElement(By.name("userName")).sendKeys("test-180@qa.moolya.global");
		driver.findElement(By.name("Password")).sendKeys("Moolya@123");
		driver.findElement(By.xpath("//button[@class='ml_submit_btn']")).click();
		Thread.sleep(20000);
		
		List<WebElement> links_list = driver.findElements(By.tagName("a"));
		links_list.addAll(driver.findElements(By.tagName("img")));

		System.out.println("size of full links and images-------->"+links_list.size());

		List<WebElement> ativeLinks = new ArrayList<WebElement>();

		for(int i=0; i<links_list.size(); i++) {
			if(links_list.get(i).getAttribute("href") != null) {
				ativeLinks.add(links_list.get(i));
			}
		}

		System.out.println("size of all active links and images-------->"+ativeLinks.size());

		for(int j=0; j<ativeLinks.size(); j++) {
			ativeLinks.get(j).click();
			

			List<WebElement> img_list = driver.findElements(By.tagName("img"));
			int list_size = img_list.size();
			System.out.println("list_size: "+list_size);

			for(int i=0; i<list_size; i++) {
				String img_url = img_list.get(i).getAttribute("src");
				System.out.println(img_url);
			}
		}	

	}
}
