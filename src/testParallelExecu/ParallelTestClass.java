package testParallelExecu;

import org.testng.annotations.Test;

public class ParallelTestClass extends BaseClass{
	
	@Test
	public  void test1() {
		System.out.println("test1");
	}
	
	@Test
	public  void test2() {
		System.out.println("test2");
	}

}
