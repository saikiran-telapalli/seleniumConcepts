package selenium_Concepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlingTables {
	
	public static void main(String[] args) {
		int sum=0;
		
	
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/Drivers/chromedriver");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().fullscreen();
	driver.get("http://www.cricbuzz.com/live-cricket-scorecard/18869/eng-vs-pak-2nd-test-pakistan-tour-of-england-2018");
	
	WebElement table =driver.findElement(By.cssSelector("div[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));
	int rowcount= table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms']")).size();
	//System.out.println(rowcount);
	//Traversing from parent to child
	int colcount= table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).size();
		
	for(int i=0;i<colcount-2;i++) {
		String value=(table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i).getText());
		
		//Converting string to integer
		int Integervalue=Integer.parseInt(value);
		//Adding all the columns
		sum=sum+Integervalue;
		}

	System.out.println(sum);
	
	//Traversing from sibling to sibling
	String Extras =driver.findElement(By.xpath("//div[text()='Extras']/following-sibling::div")).getText();
	
	int IntergerExtras= Integer.parseInt(Extras);
	System.out.println(IntergerExtras);
	
	int Totalsum= sum+IntergerExtras;
	System.out.println("Total Score="+ Totalsum);
	
	String Total= driver.findElement(By.xpath("//div[text()='Total']/following-sibling::div")).getText();
	int ActualTotal=Integer.parseInt(Total);
	
	if(ActualTotal==Totalsum) {
		System.out.println("count match");
	}
	else {
		System.out.println("match fail");
	}
	}

}
