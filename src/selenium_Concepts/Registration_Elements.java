package selenium_Concepts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Registration_Elements extends Common_Methods{
	
	//Admin log in elements
	static By emailid= By.name("userName");
	static By password= By.name("Password");
	static By Signin= By.xpath("//button[@class='ml_submit_btn']");
	static String username= "saikiran.telapalli@raksan.in";
	static String pswd= "MoolyaAdmin@123";
	
	//Registration elements
	static By Transactionleftnav= By.xpath("//span[@class='ml my-ml-transactions']");
	static By registration= By.linkText("Registrations");
	static String ExpectedEmailId="qatest-68@raksan.in";
	
	static By requestedlist= By.xpath(".//div[@class='react-bs-container-body']/table/tbody/tr");
	static By lastpageindex= By.xpath(".//*[@title='last page']/a");
	
	static By filtericon= By.xpath(".//div[@class='filter_btn']");
	static By checkBoxAfterFilter= By.xpath(".//*[@id='react-root']//table/tbody/tr[1]/td[1]/input");
	static By EditButton=By.xpath(".//*[@class='ml ml-edit']");
	
	
	public static void login() throws IOException {

		sendKeys(emailid, username);
		sendKeys(password, pswd);
		click(Signin);
	screenShots("after login");

	}

	public static void transactions() throws Exception {
		
		WebElement transactionelement= findElement(Transactionleftnav);
		scrollToFindElement(transactionelement);
		Thread.sleep(2000);
		transactionelement.click();
	}

	public static void registration() throws Exception {

		explicitWait(registration);
		Thread.sleep(2000);
		click(registration);
		createRegistration();
		//pagination();
		//filter();
		//pageIndex();
		//registrationBasicInfo();

	}
	
	public static void createRegistration() throws Exception {
		Thread.sleep(1000);
		click(By.xpath(".//*[text()='Create']"));
		sendKeys(By.xpath(".//input[@placeholder='First Name']"), "Saikiran");
		sendKeys(By.xpath(".//input[@placeholder='Last Name']"), "lastname");
		selectTextfromfield("India", 0);
		sendKeys(By.xpath(".//input[@placeholder='Contact number']"), "number");
		sendKeys(By.xpath(".//input[@placeholder='Email Id']"), "emailid@raksa.in");
		selectTextfromfield("Investors", 1);
		selectTextfromfield("India", 2);
		selectTextfromfield("Hyderabad", 3);
		selectTextfromfield("moolya-Hyderabad", 4);
		sendKeys(By.xpath(".//input[@placeholder='Password']"), "password");
		
		
	}

	public static void pagination() throws Exception {

		int i=1;
		int page=2;
		int requestedlistcount= findElements(requestedlist).size();
		for(;i<=requestedlistcount;i++) {
			
			By ActualEmail= By.xpath(".//div[@class='react-bs-container-body']/table/tbody/tr["+i+"]/td[5]");
			handleStaleElement(ActualEmail, 2, 2000);
			By checkBoxIfMatched= By.xpath(".//div[@class='react-bs-container-body']/table/tbody/tr["+i+"]/td[1]");
			By IncrementIndex= By.xpath("(.//*[@class='page-link'])["+page+"]");
			
			String ActualEmailID= findElement(ActualEmail).getText();
			System.out.println(ActualEmailID);

			if(ExpectedEmailId.equalsIgnoreCase(ActualEmailID)) {
				System.out.println("Emailid matched");
				click(checkBoxIfMatched);
				click(EditButton);
				break;
			}
			else {
				if(i==requestedlistcount) {
					try {
						System.out.println(IncrementIndex);
						 click(IncrementIndex);
						 i=0;
						 if(!findElement(lastpageindex).isDisplayed()) {
							 page=7;
							 
						 }
						 else if(page==2) {
								page=page+2;
							}
						 else if(page==6){
								 page=6;
						 }
							else {
								page++;
							}
					}
					catch(Exception e){
						System.out.println("oopss !! you are already  on last page");
					}
					
					Thread.sleep(2000);
					}
				}
		}
	}
	
	public static void filter() throws Exception {
		
		click(filtericon);
		WebElement EmailFilter= findElements(By.xpath(".//input[@class= 'form-control float-label']")).get(2);
		EmailFilter.sendKeys(ExpectedEmailId);
		click(By.xpath(".//span[@class='ml ml-save']"));
		//explicitWait(checkBox);
		Thread.sleep(1000);
		click(checkBoxAfterFilter);
		click(EditButton);
			
	}
	public static void pageIndex() {
			int i=1;
			int requestedlistcount= findElements(requestedlist).size();
			
		for(;i<=requestedlistcount;i++) {
			
			By ActualEmail= By.xpath(".//div[@class='react-bs-container-body']/table/tbody/tr["+i+"]/td[5]");
			String ActualEmailID= findElement(ActualEmail).getText();
			By checkBoxIfMatched= By.xpath(".//div[@class='react-bs-container-body']/table/tbody/tr["+i+"]/td[1]");
			
		if(ExpectedEmailId.equalsIgnoreCase(ActualEmailID)) {
			System.out.println("Emailid matched");
			click(checkBoxIfMatched);
			click(EditButton);
			break;
		}
		else {
			click(By.xpath(".//*[@id='pageDropDown']"));
			click(By.xpath(".//*[@id='react-root']//ul/li[2]/a"));
			
		}
	}

	}
	
	
	public static void registrationBasicInfo() throws Exception {
		WebElement OperationArea= findElement(By.xpath(".//*[@class='panel-heading']"));
		handleStaleElement(By.xpath(".//*[@class='panel-heading']"), 2, 2000);
		scrollToFindElement(OperationArea);
			
		/*selectFieldOnlyClick(driver, 0).click();
		Thread.sleep(3000);*/
		selectTextfromfield("India", 2);
		Thread.sleep(3000);
		selectTextfromfield("Hyderabad", 3);
		selectTextfromfield("moolya-Hyderabad", 4);
		click(By.id("individual"));
		selectTextfromfield("Seed Investor", 5);
		selectTextfromfield("Transportation", 6);
		selectTextfromfield("Doctorates", 7);
		click(By.xpath(".//*[@class='ml my-ml-save']"));
		
	}
	
}

