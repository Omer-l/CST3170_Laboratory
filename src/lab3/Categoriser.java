package lab3;

import java.util.Arrays;

//This class is used to count and categorise each feature's input in the table (lenseData.txt)

public class Categoriser {

	private int[][] featuresInputs;
	private int[] categories;

	public Categoriser(int[][] featureInputs, int[] categories) {
		this.featuresInputs = featureInputs;
		this.categories = categories;
	}

	/**
	 * This function evaluates a feature and returns the categories that the inputs of the feature led to.
	 * @return					an array depicting the number of times a category class appeared for each input type of the feature.
	 */
	public int[][] getCategoryOfInputsForFeature(int featureNumber) {
		int[] featureInputs = featuresInputs[featureNumber];

		int numberOfInputsTypes = Application.getNumberOfInputTypesInColumn(featureInputs);
		int numberOfCategoryTypes = Application.getNumberOfInputTypesInColumn(categories);

		//holds the categories of each input in the feature.
		int[][] categoryInputsLedTo = new int[numberOfInputsTypes][numberOfCategoryTypes];

		for(int inputTypeIterator = 1; inputTypeIterator <= categoryInputsLedTo.length; inputTypeIterator++) {

			int[] categoriesInputTypeLedTo = new int[numberOfCategoryTypes];

			for (int rowIterator = 0; rowIterator < featureInputs.length; rowIterator++) {
				int input = featureInputs[rowIterator];
				int categoryInputLedTo = categories[rowIterator];

				if(input == inputTypeIterator)
					categoriesInputTypeLedTo[categoryInputLedTo-1]++;
			}

			categoryInputsLedTo[inputTypeIterator-1] = categoriesInputTypeLedTo;
		}

		return categoryInputsLedTo;
	}

}
