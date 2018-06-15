package TestNG;

import org.testng.annotations.Ignore;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@Test
public class ClassLevelAnnotations {

	@Parameters("one")
	public void One(@Optional("Default value here") String One){
		System.out.println("ClassLevelAnnotations One Method= "+One);
	}
	
	public void Two(){
		System.out.println("ClassLevelAnnotations Two Method");
	}
	@Ignore
	public void Three(){
		System.out.println("ClassLevelAnnotations Three Method");
		
	}
	
	public void Fourth(){
		System.out.println("ClassLevelAnnotations Fourth Method");
		Three();
	}
}
