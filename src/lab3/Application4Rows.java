package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Application4Rows {
	private final static int SIZE = 4;

	public static void main(String[] args) {

		int[][] data = getData();
		
		processData(data);
	}

	public static void processData(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			int decision = getDecision(data[i]);
			System.out.println(decision);
		}
	}

	public static int getDecision(int[] data) {
		int result = -1;

		int f1 = data[0];
		int f2 = data[1];
		int f3 = data[2];
		int f4 = data[3];

		if (f1 == 1) {
			if (f2 == 1) {
				if (f3 == 1) {
					if (f4 == 1) {
						result = 3;
					} else if (f4 == 2) {
						result = 2;
					}
				} else if (f3 == 2) {
					if (f4 == 1) {
						result = 3;
					} else if (f4 == 2) {
						result = 1;
					}
				}
			}
		}
		return result;
	}
	
	public static int[][] getData() {
		int[][] data = new int[SIZE][5];
		try {
			Scanner input = new Scanner(new File("./Resources/lenseData.txt"));
			int count = 0;
			while (input.hasNext() && count < 4) {

				String[] bits = input.nextLine().split(" ");

				// decisions
				int F1 = Integer.parseInt(bits[2]);
				int F2 = Integer.parseInt(bits[4]);
				int F3 = Integer.parseInt(bits[6]);
				int F4 = Integer.parseInt(bits[8]);
				int F5 = Integer.parseInt(bits[10]); // category
				int dataLine[] = { F1, F2, F3, F4, F5 };
				data[count] = dataLine;
				printArray(data[count]);
				count++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		
		return data;
	}

	public static void printArray(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}
}
