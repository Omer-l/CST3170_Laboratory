package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Application_C45 {

	//* PLACE DATA FILES IN 'Resources' FOLDER.INPUT FILE NAME AFTER THE LAST '/' OF THIS LINE.
	private static File file = new File(System.getProperty("user.dir") + "/Resources/lenseData");

	public static void main(String[] args) {
		
		int[][] features = { { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, },
				{ 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, },
				{ 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, },
				{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, } };
		int[] category = { 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 3, 3, 3, 3, 1, 3, 2, 3, 3, };
	
	
//		int[][] categoryF1 = ApplicationTask2.getCategoryF1(features, category);
//		int[][] categoryF2 = ApplicationTask2.getCategoryF2_F4(features, category, 1);
//		int[][] categoryF3 = ApplicationTask2.getCategoryF2_F4(features, category, 2);
//		int[][] categoryF4 = ApplicationTask2.getCategoryF2_F4(features, category, 3);
//
//		double[][] entropies = new double[4][3]; //4 features, for which a feature can result in 3 possible categories.
//
//		entropies[0] = getEntropy(categoryF1);
//		entropies[1] = getEntropy(categoryF2);
//		entropies[2] = getEntropy(categoryF3);
//		entropies[3] = getEntropy(categoryF4);
//
//
//		System.out.println(Arrays.deepToString(categoryF1));
//		System.out.println(Arrays.deepToString(categoryF2));
//		System.out.println(Arrays.deepToString(categoryF3));
//		System.out.println(Arrays.deepToString(categoryF4));
//
//
//		int[] minimumEntropyIndex = getMaximumEntropy(entropies);
//
//		System.out.println(Arrays.deepToString(entropies));
//
//		System.out.println("MAXIMUM ENTROPY AT: " + Arrays.toString(minimumEntropyIndex));
	}
	
	/**
	 * 
	 * @param entropies		calculated entropies.
	 * @return				index of feature's label that has the maximum entropy.
	 */
	private static int[] getMaximumEntropy(double[][] entropies) {
		int[] index = new int[2]; //returns y (row) and x (column) coordinate in array of categories
		
		double currentMin = Integer.MAX_VALUE;
		
		for(int y = 0; y < entropies.length; y++)
			for(int x = 0; x < entropies[y].length; x++) {
				if(entropies[y][x] < currentMin) {
					index[0] = y;
					index[1] = x;
					currentMin = entropies[y][x];
				}
			}
		return index;
	}

	/**
	 * 
	 * @param arr		The outcomes of each label of a feature.
	 * @return			entropy of each label of the feature.
	 */
	public static double[] getEntropy(int[][] arr) {
		double[] entropy = new double[arr.length];
		
		int weight = (entropy.length == 3 ? 8 : 12);
		
		for(int label = 0; label < arr.length; label++) {
			double tmpEntropy = 0;
			for(int frequency : arr[label]) { //frequency of a particular label's category.
				double pPos = (double)frequency / (double)weight;
				double pNeg = ((double)weight - (double)frequency) / (double)weight;
//				double probabilityOfByte = (double) frequency / (double) arr.length;
//				
//				double value = probabilityOfByte * (Math.log(probabilityOfByte) / Math.log(2));
				if(pPos == 0 || pNeg == 0)
					continue;
				else {
					double value = - (( pPos * log2(pPos) ) + ( pNeg * log2(pNeg)));
					tmpEntropy += value;
				}
			}
			entropy[label] = tmpEntropy;
			System.out.println();
		}
		return entropy;
	}
	
	public static double log2(double base) {
		return Math.log(base) / Math.log(2);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	int[][] data = getData();
//	int[][] features = getFeature(data);
//	int[] category = new int[data.length];
//	System.out.println(Arrays.deepToString(features));
//	System.out.println(Arrays.toString(category));
	
	
	public static int[] getOutcomes(int[][] data, int category) {
//		int[] features = getFeature(int[][] data);
		
		return null;
	}
	
//	public static void getFeature(int[][] data) {
//		
//	}
	
	/**
	 * BELOW ARE THE DATA RETRIEVING FUNCTIONS.
	 */
	public static void getUserInput() { 
//		Scanner input = new Scanner(System.in);
//		System.out.print("ENTER FILE NAME (<filename>.<filetype>): ");
//		file = new File("System.getProperty(\"user.dir\") + \"/Resources/" + input.next());
	}

	public static int[][] getData() {
		int[][] data = new int[getNumberOfTrainingSets()][getNumberOfColumns() - 1];
		int trainingSetCount = 0;
		
		System.out.println(data.length); // DEL
		try {
			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				String[] bits = input.nextLine().split("\\s+");
				data[trainingSetCount] = getRowData(bits, data[0].length);
				System.out.println(Arrays.toString(data[trainingSetCount]));
				trainingSetCount++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return data;
	}

	public static int getNumberOfTrainingSets() {
		int trainingSetsCounter = 0; // counter

		try {
			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				String nextLine = input.nextLine();
				trainingSetsCounter++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return trainingSetsCounter;
	}

	public static int getNumberOfColumns() {
		int numberOfColumns = 0;

		try {
			Scanner input = new Scanner(file);

			String line = input.nextLine();
			String[] bits = line.split("\\s+");
			numberOfColumns = bits.length;
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return numberOfColumns;
	}

	public static int[] getRowData(String[] bits, int numberOfColumns) {
		int[] data = new int[numberOfColumns];
		
		for(int i = 1; i < bits.length; i++) //index 0 is row number, last index is category.
			data[i-1] = Integer.parseInt(bits[i].trim());
		
		return data;
	}
}
