package lab07;

import java.util.Arrays;

public class ApplicationTask7 {
	private final static int ROWS = 10;
	private final static int COLUMNS = 2; // x and a y
	private static boolean learningRateZero = false; //whence the points are all equivalent to the points before them.
	private static double[][] nodesFromBefore;
	private final static double minimumLearningRate = 0.5;
	private static double[] allLearningRates = new double[ROWS]; //keeps track of all the learning rates.
	private static boolean[] allLearningRatesMinimum = new boolean[ROWS]; //keeps track of all the learning rates.
	
	public static void main(String[] args) {
		double[][] nodes = generateArbitraryArrayOfIntegers();
		nodesFromBefore = nodes;
		int input = 0;
		do {
			System.out.println(Arrays.deepToString(nodes));
			nodesFromBefore = nodes.clone(); //save nodes before changing
			selfOrganise(nodes, input++);
			
			if(input == ROWS)
				input = 0;
		} while(!allAtMinimumLearningRate());
	}

//	protected static boolean zeroLearningRate(double[][] nodes, double[][] nodesFromBefore) {
//		for(int i = 0; i < nodes.length; i++)
//			if(nodes[i][0] != nodesFromBefore[i][0] || nodes[i][1] != nodesFromBefore[i][1]) //then learning rate is not zero yet.
//				return false;
//		
//		return true;
//	}
	
	/**
	 * This function returns true if the new
	 * @param 	dataPointFromBefore
	 * @param 	dataPointFromAfter
	 * @return
	 */
	protected boolean atMinimumLearningRate(double dataPointFromBefore, double dataPointFromAfter) {
		double learningRate = Math.abs(dataPointFromBefore - dataPointFromAfter); //how much data has changed by.
		
		if(learningRate <= minimumLearningRate)
			return true;
		else
			return false;
	}

	/**
	 * will return true if all points have stopped moving towards the closest point to them.
	 */
	protected static boolean allAtMinimumLearningRate() {
		for(boolean isDataPointMinimumLearningRate : allLearningRatesMinimum)
			if(!isDataPointMinimumLearningRate)
				return false;
		
		return true;
	}

	/**
	 * This function generates an array of arbitrary numbers.
	 * @return An array of arbitrary Integers
	 */
	public static double[][] generateArbitraryArrayOfIntegers() {
		double[][] arr = new double[ROWS][COLUMNS];

		for (int y = 0; y < ROWS; y++)
			for (int x = 0; x < COLUMNS; x++)
				arr[y][x] = (Math.random() * 100);

		return arr;
	}

	/**
	 * This function calculates the euclidian distance for a 2D graph
	 * @param x1	point x1
	 * @param y1	point y1
	 * @param x2	point x2
	 * @param y2 	point y2
	 * @return distance between the two points
	 */
	public static double getDistanceTo(double x1, double y1, double x2, double y2) {
		return Math.sqrt((Math.pow(x1 - x2, 2)) + ((Math.pow(y1 - y2, 2))));
	}

	/**
	 * This function evaluates the nearest node, it finds the smallest
	 * difference.
	 * 
	 * @param point 		the point comparing all the other nodes to. the input is an x and y in an array of length 2
	 * @param indexOfPoint 	index of the 'point' array in the parameter.
	 * @return 				the closest node's index
	 */
	public static int getClosestNode(double[][] nodes, double[] point, int indexOfPoint) {
		// get x and y of point
		double x1 = point[0];
		double y1 = point[1];
		
		double x2 = -1;
		double y2 = -1;

		// calculating distance with point number 0 for now, or if indexOfPoint is 0, make it 1.
		if (indexOfPoint != 0) {
			x2 = nodes[0][0];
			y2 = nodes[0][1];
		} else {
			x2 = nodes[1][0];
			y2 = nodes[1][1];
		}

		double currentMinimumDistance = getDistanceTo(x1, y1, x2, y2);
		int currentMinimumDistanceIndex = 0;

		for (int pointIt = 0; pointIt < nodes.length; pointIt++) {
			if (pointIt == indexOfPoint)
				continue; // ensures function doesn't calculate the distance to
							// the point in the parameter.
			x2 = nodes[pointIt][0];
			y2 = nodes[pointIt][1];

			double distance = getDistanceTo(x1, y1, x2, y2);
			if(distance < minimumLearningRate)
				
			if (distance < currentMinimumDistance) {
				currentMinimumDistance = distance; // sets a new minimum if less than currentMinimumDistance
				currentMinimumDistanceIndex = pointIt;
			}
		}
		return currentMinimumDistanceIndex;
	}

	public static void moveNodeTowardsPoint(double[][] nodes, int indexToMoveTo, int indexToMove) {
		//get half x,
		double newX = (nodes[indexToMove][0] + nodes[indexToMoveTo][0]) / 2;
		//get half y
		double newY = (nodes[indexToMove][1] + nodes[indexToMoveTo][1]) / 2;
		//create a new point
		double[] newPoint = {newX, newY};
		
		nodes[indexToMove] = newPoint; 
	}
	
	public static void selfOrganise(double[][] nodes, int indexToMoveNodesTo) {
		int closestNodeIndex = getClosestNode(nodes, nodes[indexToMoveNodesTo], indexToMoveNodesTo);
		System.out.println("MOVING INDEX: " + (closestNodeIndex+1) + " TO " + (indexToMoveNodesTo+1));
		moveNodeTowardsPoint(nodes, indexToMoveNodesTo, closestNodeIndex);
		
	}
}
