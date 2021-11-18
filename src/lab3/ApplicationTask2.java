package lab3;

import java.util.Arrays;

public class ApplicationTask2 {

	public static void main(String[] args) {
		int[][] features = { { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, },
				{ 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, },
				{ 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, },
				{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, } };
		int[] category = { 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 3, 3, 3, 3, 1, 3, 2, 3, 3, };

		System.out.println(getCategoryF1(features, category));
		System.out.println(getCategoryF2_F4(features, category));
	}

	public static int[][] getCategoryF1(int[][] features, int[] category) {

		int[][] howManyOfEachCategory = new int[3][3];

		int f = 0;
		for (int d = 0; d < features[f].length; d++) { // d -> data index of feature
			int data = features[f][d];
			if (data == 1)
				switch (category[d]) {
				case 1:
					howManyOfEachCategory[0][0]++;
					break;
				case 2:
					howManyOfEachCategory[0][1]++;
					break;
				case 3:
					howManyOfEachCategory[0][2]++;
					break;
				}
			else if (data == 2)
				switch (category[d]) {
				case 1:
					howManyOfEachCategory[1][0]++;
					break;
				case 2:
					howManyOfEachCategory[1][1]++;
					break;
				case 3:
					howManyOfEachCategory[1][2]++;
					break;
				}
			else if (data == 3)
				switch (category[d]) {
				case 1:
					howManyOfEachCategory[2][0]++;
					break;
				case 2:
					howManyOfEachCategory[2][1]++;
					break;
				case 3:
					howManyOfEachCategory[2][2]++;
					break;
				}

		}

		for (int i = 0; i < howManyOfEachCategory.length; i++) {
			System.out.println(Arrays.toString(howManyOfEachCategory[i]));
		}

		return howManyOfEachCategory;
	}

	public static int[][] getCategoryF2_F4(int[][] features, int[] category) {

		int[][] howManyOfEachCategory = new int[2][3];
		for (int f = 1; f < features.length; f++) { // f -> feature number AKA columns
			howManyOfEachCategory = new int[2][3];
			System.out.print("FEATURE " + (f + 1) + "\n");
			
			for (int d = 0; d < features[f].length; d++) { // d -> data index of feature
				int data = features[f][d];

				if (data == 1)
					switch (category[d]) {
					case 1:
						howManyOfEachCategory[0][0]++;
						break;
					case 2:
						howManyOfEachCategory[0][1]++;
						break;
					case 3:
						howManyOfEachCategory[0][2]++;
						break;
					}
				else if (data == 2)
					switch (category[d]) {
					case 1:
						howManyOfEachCategory[1][0]++;
						break;
					case 2:
						howManyOfEachCategory[1][1]++;
						break;
					case 3:
						howManyOfEachCategory[1][2]++;
						break;
					}
			}
			for (int i = 0; i < howManyOfEachCategory.length; i++)
				System.out.println(Arrays.toString(howManyOfEachCategory[i]));
		}
		
			return howManyOfEachCategory;

		}
}
