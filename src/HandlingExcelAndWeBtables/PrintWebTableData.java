package HandlingExcelAndWeBtables;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class PrintWebTableData {
	
	public static WebDriver driver;
	public static ExcelOperations data;

	public static void initialisation() throws Exception {
		
		String filePath = "/Users/saikiran/Downloads/MainSeleniumAutomationFW-master/src/main/resources/excelFiles/login.xlsx";
		
		
		data = new ExcelOperations();
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.freecrm.com/index.html?e=2");
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		String userName = data.readExcel(filePath,"login",1,0);
		String password = data.readExcel(filePath,"login",1,1);
		
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		driver.switchTo().frame("mainpanel");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		List<WebElement> col =driver.findElements(By.xpath("//*[@id='vContactsForm']/table/tbody/tr[3]/td"));
		System.out.println("No.of columns in a table: "+col.size());
		
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"vContactsForm\"]/table/tbody/tr/td[1]"));
		System.out.println("No.of rows in a table: "+rows.size());
		
		for(int i=3; i<rows.size()-1; i++) {
			for(int j=1; j<col.size(); j++) {
				String textinCell = driver.findElement(By.xpath("//*[@id='vContactsForm']/table/tbody/tr["+i+"]/td["+(j+1)+"]")).getText();
				System.out.print(textinCell+" ");
			}
			System.out.println("");
		}
		
		
	}

}
