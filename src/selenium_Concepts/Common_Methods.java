package selenium_Concepts;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common_Methods {
	
	
	static WebDriver driver;
	
	
	public static void browserinvoke() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get("https://qaapp.moolya.global");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	public static WebElement findElement(By locator) {
		return driver.findElement(locator); 
	}

	public static void sendKeys(By locator,String value) {
		findElement(locator).sendKeys(value);
	}
	
	public static void click(By locator) {
		findElement(locator).click();
	}
	
	public static void clear(By locator) {
		findElement(locator).clear();
	}
	
	//Method To find element when scrolling
	public static WebDriver scrollToFindElement(WebElement element){
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
	return driver;
	}
	
	//Dropdown method
	public static ISelect selectDropdown(By locator) {
		Select dropdown = new Select(findElement(locator));
		return dropdown;
	}
	
	//Taking screenshots method
	public static void screenShots(String screenshotname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("/Users/saikiran/eclipse-workspace/moolya/Screenshots"+screenshotname+getTimeStamp()+".jpg"));
		
	}
	
	//Capture current date & time
	public static String getTimeStamp() {
		Date d= new Date();
		Timestamp t= new Timestamp(d.getTime());
		String time= t.toString();
		time.replaceAll(":", "_");
		return time;
		
	}
	
	//Explicit wait method
	public static void explicitWait(By locator) {
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}
	
	public static String getText(By locator) {
		return findElement(locator).getText();
	}
	
	public static void handlingFrames(String id) {
		driver.switchTo().frame(id);
	}
	
	public static void handlingMouseHover(WebElement target) {
		Actions action1= new Actions(driver);
		action1.moveToElement(target).build().perform();
	}
	
	public static void handlingDragDrop(WebElement target) {
		Actions action2= new Actions(driver);
		action2.moveToElement(target).build().perform();
	}
	
	public static  WebElement handleStaleElement(By locator,int retryCount,int delayInMilliSeconds) throws InterruptedException {
		//log.info(locator);
		WebElement element = null;
		
		while (retryCount >= 0) {
			try {
				element = driver.findElement(locator);
				return element;
			} catch (StaleElementReferenceException e) {
				Thread.sleep(delayInMilliSeconds);
				retryCount--;
			}
		}
		throw new StaleElementReferenceException("Element cannot be recovered");
	}

	
	
	
	//Select text after click for drop down selectTextfromfield
	
	public static WebElement selectFieldOnlyInput(WebDriver driver,int index){
		return driver.findElements(By.xpath(".//*[@class='Select-input']/input")).get(index);
	}
	
	public static void selectTextfromfield(String searchText,int selectFieldNumber){

		
		try {
			selectFieldOnlyInput(driver, selectFieldNumber).sendKeys(searchText);
			Thread.sleep(2000);
			int optionNumber=1;
			while(true){
				String suggestedText=driver.findElement(By.xpath(".//*[@class='Select-menu-outer']/div/div["+optionNumber+"]")).getText();
				System.out.println("suggestedText="+suggestedText);
				if(searchText.equalsIgnoreCase(suggestedText)){
					System.out.println("Search & Suggest matched");				
					selectFieldOnlyInput(driver, selectFieldNumber).sendKeys(Keys.ENTER);
					break;
				}
				else{
					selectFieldOnlyInput(driver, selectFieldNumber).sendKeys(Keys.ARROW_DOWN);
					System.out.println("Search & Suggest didn't match");
				}
				optionNumber++;
			}
		}
		catch (Exception e) {
			System.out.println("Haven't found the text in the search suggestion box");
			e.printStackTrace();
			
		}

	}
	
	
	
	public static WebElement selectFieldOnlyClick(WebDriver driver,int index){
		return driver.findElements(By.xpath(".//*[@class='Select-placeholder']")).get(index);
	}


}

