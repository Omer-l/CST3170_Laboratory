package lab2;

import static org.junit.Assert.*;

import org.junit.Test;

public class testRow {

	@Test
	public void testGetClassifiedPointsA() {
		FileReaderLab2 fileReader = new FileReaderLab2("lineClass2.txt");
		PointLab2[] points = fileReader.getData();
		char expected = 'A';
		
		PointLab2[] pointsA = PointLab2.getClassifiedPoints(points, expected);
		
		for(PointLab2 point : pointsA)
			assertEquals('A', point.getClassification());
	}
	
	@Test
	public void testGetClassifiedPointsB() {
		FileReaderLab2 fileReader = new FileReaderLab2("lineClass2.txt");
		PointLab2[] points = fileReader.getData();
		char expected = 'B';
		
		PointLab2[] pointsB = PointLab2.getClassifiedPoints(points, expected);
		
		for(PointLab2 point : pointsB)
			assertEquals(expected, point.getClassification());
	}
	
	@Test
	public void testGetNumberOfClassification() {
		FileReaderLab2 fileReader = new FileReaderLab2("lineClass2.txt");
		PointLab2[] points = fileReader.getData();
		char classification = 'A';
		
		int expected = 9;
		int actual = PointLab2.getNumberOfClassification(points, classification);
		
		assertEquals(expected, actual);
	}

}
