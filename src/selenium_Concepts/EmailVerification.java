package selenium_Concepts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class EmailVerification {

	
	public static void emailVerificationProcess() throws Exception {
		
		String ExpectedTomail= "Userone@qa.moolya.global ";
		String ExpectedSubject= "moolya alert! Your next step on moolya";
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get("https://mail.raksan.in/");
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys("qateam@qa.moolya.global");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Moolya@123");
		driver.findElement(By.xpath("//input[@class='ZLoginButton DwtButton']")).click();
		
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		int totalemails= driver.findElements(By.xpath(".//*[@class='DwtListView-Rows']/li")).size();
		System.out.println("totalemails="+totalemails);
		
		//Thread.sleep(5000);
		
		
		for(int i=1;i<=totalemails;i++) {
			
		Thread.sleep(2000);

		driver.findElement(By.xpath(".//*[@class='DwtListView-Rows']/li["+i+"]/div")).click();
		
		String Subject= driver.findElement(By.xpath(".//*[@id='zv__TV__TV-main_MSG_hdrTableTopRow']/td[2]")).getText();
		String ToEmail= driver.findElement(By.xpath("//*[@id='zcs"+(2*i)+"']/span")).getText();
		
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("frames.size(): "+frames.size());
		
		for(WebElement frame : frames) {
			driver.switchTo().frame(frame);
			if(!driver.findElement(By.xpath("path")).isDisplayed()) {
				System.out.println("path not found");
				break;
			}
		}
		
		
		if(Subject.equalsIgnoreCase(ExpectedSubject)) {
			System.out.println("subject match");
	
		
		if(ToEmail.equalsIgnoreCase(ExpectedTomail)) {
			System.out.println("email matched");
			
			driver.switchTo().frame("zv__TV-main__MSG__body__iframe");
			System.out.println("switched to frame suucessfully");
		
			WebElement element = driver.findElement(By.xpath(".//a[text()='Verify e-mail id']"));
			scrollToFindElement(driver, element);
	        element.click();
			break;
		}
		else {
			System.out.println("email not matched");
		}
		}
			
		}
		
		
	}
	public static WebDriver scrollToFindElement(WebDriver driver, WebElement element){

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);

		return driver;
	}
	
	public static void main(String[] args) throws Exception {
		emailVerificationProcess();
	}
}
