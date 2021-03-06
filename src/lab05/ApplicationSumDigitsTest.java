package lab05;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ApplicationSumDigitsTest {

	@Test
	public void test() {
		System.out.println(Math.random() * 1);
	}
	
	@Test
	public void testGetOffspring() {
		String parent1 = "94555068728873695776"; //117
		String parent2 = "99502239992905807798"; //112
		String expectedResult = "94552239992905807798"; //112
		int crossoverIndex = 4;
		System.out.println(ApplicationSumDigits.getSumOfString(expectedResult));
		assertEquals( "94552239992905807798" , ApplicationSumDigits.getCrossover(parent1, parent2, crossoverIndex));
	}
	
	@Test
	public void testMutation() {
		char[] offspringTest = "94555068728873695776".toCharArray();
		
		char[] mutated = ApplicationSumDigits.getMutation(offspringTest, 9);
		assertTrue(!Arrays.equals(offspringTest, mutated));
	}

}
