package selenium_Concepts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DupDropdownVal {

	public static void main(String[] args) throws InterruptedException, IOException {

		String path = "/Users/saikiran/Desktop/countrylist.txt";
		String url = "https://whitelist.moolyacoin.io/";

		By country_DropDown_xpath = By.xpath("//*[@id='root']/div/div/div[2]/div[4]/div[1]/div[4]/div/div/div");
		By country_list_xpath = By.xpath("//*[@id='react-select-2--list']/div");
		//By country_search_text = By.xpath("//input[@class='select2-search__field']");
		By city_DropDown_xpath = By.xpath("//*[@id='root']/div/div/div[2]/div[4]/div[1]/div[6]/div/div/div");
		By city_list_xpath = By.xpath("//*[@id='react-select-3--list']/div");


		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		//		ChromeOptions options = new ChromeOptions();
		//		options.addArguments("window-size =1400,800");
		//		options.addArguments("headless");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(url);

		WebElement country_DropDown = driver.findElement(country_DropDown_xpath);
		WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(country_DropDown));
		country_DropDown.click();

		//		Select country = new Select(country_DropDown);
		//		List<WebElement> country_list = country.getOptions();

		List<WebElement> country_list = driver.findElements(country_list_xpath);

		int country_size = country_list.size();
		//System.out.println("countries: "+country_size);

		//System.out.println("Showing all countries with occurrences count in form key and value");
		Object[] country_array = new Object[country_size];
		for(int i=0; i<country_size; i++) {
			//System.out.println(country_list.get(i).getText());
			country_array[i] = country_list.get(i).getText();
		}	

		/* It prints all the records using advanced for loop
				for(Object str : country_array) {
					System.out.println(str);
				}
		 */

		/* If you want to remove duplicates from an array

		System.out.println("This method prints all the duplicate records in the array:");
		HashSet set = new HashSet();
		for(Object str : country_array) {
			if(!set.add(str)) {
				System.out.println(str);
			}
		}
		 */

		DupDropdownVal repetition = new DupDropdownVal();
		//repetition.RepeatitionInArray(country_array);
		repetition.write(path,country_array);

		for(int i=19; i<country_size; ) {

			
			String select_Country = driver.findElements(country_list_xpath).get(i).getText();
			//System.out.println("==========================================================================================");
		
			//driver.findElement(country_DropDown_xpath).sendKeys(select_Country);

			for(WebElement county : driver.findElements(country_list_xpath)){
				if(county.getText().equals(select_Country)) {
					county.click();
					

					WebElement city_Drodown = driver.findElement(city_DropDown_xpath);
					WebDriverWait wait2 = new WebDriverWait(driver, 30);
					wait2.until(ExpectedConditions.elementToBeClickable(city_Drodown));
					city_Drodown.click();

					Thread.sleep(3000);
					List<WebElement> city_list = driver.findElements(city_list_xpath);
					int city_size = city_list.size();
					

					//System.out.println("Showing all cities with occurrences count from "+select_Country+" in form key and value");


					Object[] city_array = new Object[city_size];
					for(int j=0; j<city_size; j++) {
//						if(city_list.get(j).getText().equalsIgnoreCase("Loading more results…")) {
//							System.out.println("\nIn "+select_Country+ " all cities are showing multiple times after loading more.. option in the city drop down");
//						}
						if(city_list.get(j).getText().equalsIgnoreCase("No results found")) {
							System.out.println("==========================================================================================");
							System.out.println("Selected Country is: "+select_Country);
							System.out.println("cities: "+city_size);
							System.out.println("\nNo results found in the city drop down");
						}
						//System.out.println(city_list.get(j).getText());
						//city_array[j] = city_list.get(j).getText();
					}	
					//repetition.RepeatitionInArray(city_array);
					break;
				}
			}
			
			WebDriverWait wait3 = new WebDriverWait(driver, 30);
			wait3.until(ExpectedConditions.elementToBeClickable(country_DropDown_xpath));
			country_DropDown.click();

			i++;

		}

		/*  This method is used to remove Duplicate using LinkedHashset maintaing insertion order

		ArrayList<Object> arraylist = new ArrayList<>(Arrays.asList(array));
		LinkedHashSet<Object> hash_set = new LinkedHashSet<Object>(arraylist);
		ArrayList<Object> arraylist_Modified = new ArrayList<Object>(hash_set);
		System.out.println(arraylist_Modified);
		System.out.println("After removing Duplicate using Hashset the count is: "+arraylist_Modified.size());

		 */

		driver.quit();

	}


	public void RepeatitionInArray(Object[] objarray) {

		boolean not_repeated = true;
		Map<Object, Integer> dupMap = new HashMap<Object, Integer>(); 
		//char[] chrs = str.toCharArray();

		for(Object record : objarray){
			if(dupMap.containsKey(record)){
				dupMap.put(record, dupMap.get(record)+1);
			} else {
				dupMap.put(record, 1);
			}
		}

		//To Count Occurrences
		//System.out.println("Printing all occurrences count");

		//System.out.println(dupMap);

		System.out.println("\nRepeated records in the selected country:");
		//To Count Only Repeated Character Occurrences In Dropdown
		Set<Object> keys = dupMap.keySet();
		for(Object record : keys){
			if(dupMap.get(record) > 1){
				System.out.println(record+" ---> "+dupMap.get(record)+" times");
				not_repeated = false;
			}
		}
		if(not_repeated) {
			System.out.println("No records are duplicated");
		}
	}

	/**
	 * Method use to write array to file
	 * @param filename
	 * @param array
	 * @throws IOException
	 */
	public void write (String filename, Object[] array) throws IOException{
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter(filename));
		for (int i = 0; i < array.length; i++) {
			// Maybe:
			outputWriter.write(array[i]+"");
			// Or:
			//outputWriter.write(Integer.toString(x[i]);
			outputWriter.newLine();
		}
		outputWriter.flush();  
		outputWriter.close();  
	}

}
