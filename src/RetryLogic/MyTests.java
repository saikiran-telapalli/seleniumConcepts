package RetryLogic;

import org.testng.Assert;
import org.testng.annotations.Test;



public class MyTests {

	@Test(retryAnalyzer = RetryLogic.RetryAnalyzer.class)   /*Using RetryAnalyzer only specific Test will be repeated for specific no.of times
															How to define Reference = Packagename.RetryAnalyzer.class  But using IAnnotationTransformer all the test cases retries*/
	public void Test1()
	{
		Assert.assertEquals(false, true);
	}

	@Test
	public void Test2()
	{
		Assert.assertEquals(false, true);
	}

	@Test
	public void Test3()
	{
		Assert.assertEquals(true, true);
	}

}
