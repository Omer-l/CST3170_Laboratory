package lab5;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
//import java.math.BigDecimal;
import org.junit.Test;

public class AllTests {

	@Test
	public void test() {
//		String testString = "11111111111111111119";           11111111111111111119/911111111111111111111                                 
//		String expectedString = "911111111111111111111";      11111111111911111111/911111111111111111111 This should have higher fitness 
//		THINK OF BETTER STRING TO TEST
		
	}
	
	@Test
	public void testSumOfString() {
		String testString = "01234567890123456789";
		for(char c : testString.toCharArray())
			System.out.print(c + "+");
		assertEquals(90, ApplicationLab5.getSumOfString(testString));
	}

	@Test
	public void testGetCrossOver() {
		String testString = "11111111111111111119"; 			
		String expectedString = "11111111111911111111"; 
		assertEquals(expectedString, Application.getCrossOver(testString, 8));
	}
	
	@Test
	public void testEvaluate() {
		ArrayList<BigDecimal> testList = new ArrayList<>();
		testList.add(new BigDecimal("11111111111111111119"));
		testList.add(new BigDecimal("11111111111911111111"));
		testList.add(new BigDecimal("91111111111111111111"));
		
		//Complete this test
//		Application.evaluateGeneration(testList);
	}
	
	@Test
	public void testOffSpring() {
		
	}
}
