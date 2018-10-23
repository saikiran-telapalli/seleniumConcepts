package utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import global.moolya.base.BaseClass;

public class CommonMethods extends BaseClass{
	
	static WebDriver driver;

	public static WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public static List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}


	public static void safeClear(By locator) {

		findElement(locator).clear();
	}


	public static void safeSendKeys(By locator, String data) {

		if(isElementPresent(locator)) {
			findElement(locator).sendKeys(data);
			log.info("Passing data to text field "+locator+ " as " +data);
			extentTest.log(LogStatus.PASS, "gfhfg");
		}
		else {
			log.error("Unable to pass data to text field "+locator+ "check the locator once.");
		}

	}

	public static void safeClick(By locator) {

		if(isElementPresent(locator)) {
			findElement(locator).click();
			log.info("Successfully clcked on "+locator);
		}
		else {
			log.error("Unable to click on "+locator);
		}

	}

	public static void safeGetText(By locator) {
		findElement(locator).getText();
	}

	public static boolean isElementDisplayed(By locator) {
		return findElement(locator).isDisplayed();

	}


	public static boolean isElementPresent(By locator) {
		if(findElements(locator).size()>0) {
			return true;
		}
		else {
			return false;
		}
	}


	public static void isElementChecked(By locator) {
		findElement(locator).isSelected();

	}

	public static boolean isElementEnabled(By locator) {
		return findElement(locator).isEnabled();

	}

	//Taking screenshots method:
	public static void captureScreenShots(String screenshotName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"/Screenshots/"+screenshotName+getTimeStamp()+".png"));

	}

	//Capture current Date & Time:
	public static String getTimeStamp() {
		Date d= new Date();
		Timestamp t= new Timestamp(d.getTime());
		String time= t.toString();
		time.replaceAll(":", "_");
		return time;
	}

	public static void switchFrames(By locator) {
		if(isElementDisplayed(locator)) {
			driver.switchTo().frame(driver.findElement(locator));
			log.info("Switched to frame "+locator+ " successfully");
		}
		else {
			log.error("Unable to switch the frame"+locator);
		}
	}

	public static void acceptAlertAt() {
		Alert alert= driver.switchTo().alert();
		alert.accept();
	}

	public static void dismissAlertAt() {
		Alert alert= driver.switchTo().alert();
		alert.dismiss();
	}

	public static String getAlertText() {
		Alert alert= driver.switchTo().alert();
		String text= alert.getText();
		return text;
	}

	//Scroll To specific Element:
	public static void scrollToFindElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",element);
	}

	//Draw border before taking the screenshot for bug
	public static void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border ='3px solid red'", element);
	}

	/**
	 * Javascript Executor concepts
	 * @param element
	 * @param message
	 */
	//Generating alert message at runtime using JSE
	public static void generateAlret(WebElement element, String message) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("alert('"+message+"')"); //only one argument in this case
		/**********************/
		 //Click function using JSE
		js.executeScript("arguments[0].click();", element);
		
		//Refresh using JSE
		js.executeScript("history.go(0);"); //(or) driver.navigate().refresh();
		
		//Get tile of the page
		String title = js.executeScript("return document.title;").toString(); //(or) driver.getTitle();
		
		//To get inner text of the page
		String pageText = js.executeScript("return document.documentElement.InnerText;").toString();
		
		//To scroll complete to bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		
	}
	//To inject date from a calendar using JS
	public static void selectDateUsingJS(WebElement element, String dateVal) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].setAttribute('value','"+dateVal+"')",element);
	}
	
	//Explicit wait method:
	public static void explicitWait(By locator) {
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}


	//Select text after click for drop down selectTextfromfield:
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

	//For loop for identifying child window:
	public static void windowHandler() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iter=allWindowHandles.iterator();
		int size=allWindowHandles.size();
		String child = null;
		for(int i=0;i<size;i++){
			child=iter.next();
		}
		//switching from current window to child window
		driver.switchTo().window(child);

	}

	public static void mouseHoverAt(By locator) {
		WebElement wb = driver.findElement(locator);
		Actions action= new Actions(driver);
		action.moveToElement(wb).build().perform();

	}

	public static void dragAndDropAt(By draglocator, By droplocator) {
		WebElement drag = driver.findElement(draglocator);
		WebElement drop = driver.findElement(droplocator);
		Actions action= new Actions(driver);
		action.dragAndDrop(drag, drop).build().perform();

	}

	public static void selectDropdown(By locator, String VisibleText) {
		new Select(driver.findElement(locator)).selectByVisibleText(VisibleText);
	}

	public static void selectDropdown(By locator, int index) {
		new Select(driver.findElement(locator)).selectByIndex(index);
	}

	public static void selectDropdownByValue(By locator, String value) {
		new Select(driver.findElement(locator)).selectByValue(value);

	}

	public static void uploadFile(By locator, String path) {
		driver.findElement(locator).sendKeys(path);

	}
}
