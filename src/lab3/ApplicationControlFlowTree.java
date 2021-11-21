package lab3;

import java.util.Arrays;

public class ApplicationControlFlowTree {

	public static void main(String[] args) {
		FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
		DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.

		int[] expectedCategories = decisionTable.getCategories();

		int[][] featuresInputs = decisionTable.getFeaturesInputs();
		int[] actualCategories = new int[featuresInputs[0].length];

		for(int rowIterator = 0; rowIterator < featuresInputs[0].length; rowIterator++) {
			int f1 = featuresInputs[0][rowIterator];
			int f2 = featuresInputs[1][rowIterator];
			int f3 = featuresInputs[2][rowIterator];
			int f4 = featuresInputs[3][rowIterator];

			actualCategories[rowIterator] = getRowCategory(f1, f2, f3, f4);
		}

		System.out.printf("%-20s%-20s\n", "EXPECTED", "ACTUAL");

		for(int categoryIterator = 0; categoryIterator < expectedCategories.length; categoryIterator++) {
			String expectedCategory = categoryToString(expectedCategories[categoryIterator]);
			String actualCategory = categoryToString(actualCategories[categoryIterator]);
			System.out.printf("%-20s%-20s\n", expectedCategory, actualCategory);
		}
	}

	/**
	 * This function turns the category class into its respective lense type.
	 * @param category	category class number
	 * @return			lense type
	 */
	public static String categoryToString(int category) {
		StringBuilder sb = new StringBuilder();

			if(category == 1)
				sb.append("hard");
			else if(category == 2)
				sb.append("soft");
			else if(category == 3)
				sb.append("none");

		return sb.toString();
	}

	/**
	 * evalutes the class category sequence of features belongs to.
	 * @param f1	feature 1's input
	 * @param f2	feature 2's input
	 * @param f3	feature 3's input
	 * @param f4	feature 4's input
	 * @return		class of row.
	 */
	public static int getRowCategory(int f1, int f2, int f3, int f4) {

		if (f4 == 1) {
			return 3;
		} else if (f4 == 2) {
			if (f3 == 1) {
				if (f1 == 1) {
					return 2;
				} else if (f1 == 2) {
					return 2;
				} else if (f1 == 3) {
					if (f2 == 1) {
						return 3;
					} else if (f2 == 2) {
						return 2;
					}
				}
			}
			if (f3 == 2) {
				if (f2 == 1) {
					return 1;
				} else if (f2 == 2) {
					if (f1 == 1) {
						return 1;
					} else if (f1 == 2) {
						if (f2 == 1) {
							return 1;
						} else if (f2 == 2) {
							return 3;
						}
					} else if (f1 == 3) {
						if (f2 == 1) {
							return 1;
						} else if (f2 == 2) {
							return 3;
						}
					}
				}
			}
		}

		return 0;

	}

}
