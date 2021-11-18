package lab6;

import java.util.Scanner;

/**
 * In general, can be described using four REs: • REtrieve the most similar case
 * or cases. • REuse the case(s) to attempt to solve the problem. • REvise the
 * proposed solution if necessary. • REtain the new solution as a part of a new
 * case. Garden: x | y | plant 1 | 1 | ROSE 2 | 2 | ROSE 3 | 3 | ROSE 4 | 4 |
 * ROSE 5 | 5 | HOLLY 6 | 6 | HOLLY 7 | 7 | [distances to other
 * classifications]-> closest. 8 | 8 | HOLLY 14 | 14 | MAPLE 21 | 21 | OAK how
 * far are other classifications from this new HxW AKA 7x7.
 * 
 */
//			(5,4) rose -> elm
//			(5,5) daphne -> oak
//			(7,5) daphne -> oak

public class Application {
	private static Garden[] cases = new Garden[20]; // cases to train.
	private final static Plants[] PLANTS = Plants.values();
	private static int caseIterator = 0;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		setCases();
		System.out.print("\n=========TRAINED DATA=========\n");
		for(int i = 0; i < cases.length; i++)
			System.out.println(cases[i]);
		System.out.print("\n==============================\n");
		
		System.out.print("Enter query ('width height' with space in between): ");
		Lab6Point query = new Lab6Point(input.nextDouble(), input.nextDouble());
		getAllEuclidianDistanceToTrainedData(query);
		int closestCase = classify(query);
		System.out.println("The closest case to this query: " + cases[closestCase]);
	}

	public static void setCases() {
		// TRAINING THE DATA
		for (int i = 1; i <= cases.length; i++) {
			cases[caseIterator].setPlant(PLANTS[i]);

			caseIterator++;
		}
	}

	/**
	 * 
	 * @param query 	new query to classify
	 * @return			distances to all other classifications
	 */
	public static double[] getAllEuclidianDistanceToTrainedData(Lab6Point query) {
		
		double[] distances = new double[cases.length];
		
		for(int i = 0; i < cases.length; i++) {
			Lab6Point point = new Lab6Point(cases[i].getWidth(), cases[i].getHeight());
			double distanceToCase = query.getDistanceTo(point);
			System.out.println(cases[i] + "\tDISTANCE TO HERE: \t" + distanceToCase);
			distances[i] = distanceToCase;
		}
		
		
		return distances;
	}
	
	public static int classify(Lab6Point query) {
		int minimumIndex = 0;
		
		double[] allDistances = getAllEuclidianDistanceToTrainedData(query);
		for(int i = 1; i < allDistances.length; i++)
			if(allDistances[i] <= allDistances[minimumIndex])
				minimumIndex = i;
		return minimumIndex;
	}
}