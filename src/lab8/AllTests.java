package lab8;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class AllTests {

	@Test
	public void testGetClosestNode() {
		double[][] nodes = {
				{1,1},
				{2,2},
				{3,3},
				{4,4},
				{5,5},
		};
		
		int testPointIndex = 4;

		double[] testPoint = nodes[testPointIndex];
		
		int expectedIndex = 3;
		int actualIndex = ApplicationTask7.getClosestNode(nodes, testPoint, testPointIndex);
		
		assertEquals(expectedIndex, actualIndex);
	}
	
	@Test
	public void testMoveNodeTowardsTheData() {
		double[][] nodes = {
				{-1,-1},
				{2,2},
				{3,3},
				{4,4},
				{5,5},
		};
		
		int testIndexToMoveTo = 4;
		int testIndexToMove = 0;
		
		ApplicationTask7.moveNodeTowardsPoint(nodes, testIndexToMoveTo, testIndexToMove);
		
		double[] expecteds = {2, 2};
		
		assertArrayEquals(expecteds, nodes[testIndexToMove], 1e-15);
	}
	
//	@Test
//	public void testZeroLearningRateTrue() {
//		double[][] nodesFromBefore = {{56, 67}, {87, 6}, {56, 67}, {1, 26}, {81, 55}, {81, 55}, {1, 26}, {90, 8}, {84, 3}, {4, 26}};
//		double[][] nodes = {{56, 67}, {87, 6}, {56, 67}, {1, 26}, {81, 55}, {81, 55}, {1, 26}, {90, 8}, {84, 3}, {4, 26}};
//		
//		assertTrue(Application_UpToTask7.zeroLearningRate(nodes, nodesFromBefore));
//	}
//	
//	@Test
//	public void testZeroLearningRateFalse() {
//		double[][] nodesFromBefore = {{56, 67}, {87, 6}, {56, 67}, {1, 26}, {81, 55}, {81, 55}, {1, 26}, {90, 8}, {84, 3}, {4, 26}};
//		double[][] nodes = {{56, 12}, {87, 6}, {56, 67}, {1, 26}, {81, 55}, {81, 55}, {1, 26}, {90, 8}, {84, 3}, {4, 26}};
//		
//		assertTrue(!Application_UpToTask7.zeroLearningRate(nodes, nodesFromBefore));
//	}

}
