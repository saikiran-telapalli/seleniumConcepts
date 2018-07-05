package testParallelExecu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import com.framework.Package.reader.PropertyFileReader;

public class BaseClass {

	public static WebDriver driver;


	@BeforeClass
	@Parameters("browserType")
	public  void openBrowser(String browserType) throws Exception{


		if(browserType.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
			driver=new ChromeDriver();			
		}else if(browserType.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}else if(browserType.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/Drivers/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}else{
			browserType="Chrome";
			System.out.println("As invalid Browser Name was provided. Hence Opening Chrome Browser");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}		


		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		driver.get("https://www.facebook.com/");

	}

}
