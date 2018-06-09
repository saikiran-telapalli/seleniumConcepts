package selenium_Concepts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class uploadingFile {
	
	public static void main(String [] args) throws AWTException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		
		driver.get("https:qaapp.moolya.global/");
		driver.findElement(By.name("userName")).sendKeys("saikiran.telapalli@raksan.in");
		driver.findElement(By.name("Password")).sendKeys("MoolyaAdmin@123");
		driver.findElement(By.xpath("//button[@class='ml_submit_btn']")).click();
		
		WebElement profilePic = driver.findElement(By.xpath("//*[@id=\"NavLbl\"]"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();",profilePic);
		
		
		
		driver.findElement(By.cssSelector("span.ml.my-ml-blank_Profile_3")).click();
		driver.findElement(By.xpath("//a[@class=\"btn btn-big blue\"]")).click();
		
		//driver.findElement(By.xpath(".//*[@id='uploadifive-file_upload']/span[2]")).click();
		
		String filePath="/Users/saikiran/Desktop/Peter Thiel.jpg";

		StringSelection filePathToCopy=new StringSelection(filePath);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathToCopy, null);
		
		//cmd+tab > cmd+shift+g > paste>two tyms enter  ----- This is to upload file in mac through keyboard
		
		Robot robot=new Robot();
		//cmd+tab to operate on file window
//		robot.keyPress(KeyEvent.VK_META);
//		robot.keyRelease(KeyEvent.VK_META);
//		robot.keyPress(KeyEvent.VK_TAB);
//		robot.keyRelease(KeyEvent.VK_TAB);
		//robot.delay(2000);
		//cmd+shift+g
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_G);
		//paste- cmd+V
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_V);
		//Hit Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(2000);
		//Again hit Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		}
		

}
