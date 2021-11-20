package lab3;

//This class is used to count and categorise the class of each feature's input in the table

public abstract class Categoriser {

	private int[][] featuresInputs;
	private int[] categories;
	private int[][][] countOfClassesOfFeatureInputs;

	public Categoriser(int[][] featureInputs, int[] categories) {
		this.featuresInputs = featureInputs;
		this.categories = categories;
		countOfClassesOfFeatureInputs = countClassesOfFeatureInputs();
	}

	/**
	 * This function gets the number of possible inputs of a column. i.e. column 1 (AKA feature 1) has 3 possible inputs, '1', '2', '3'
	 * @param column			array representing the column in the table.
	 * @return					the possible values the inputs can go up to in the column/ feature.
	 */
	protected int getNumberOfInputTypesInColumn(int[] column) {
		int numberOfInputs = 2; //the input is 1 or 2 at least.

		for(int valueInColumn : column)
			if(valueInColumn > numberOfInputs)
				numberOfInputs++; //i.e. column 1 has 3 inputs.

		return numberOfInputs;
	}

	/**
	 * This function evaluates a feature and returns the categories that the inputs of the feature led to.
	 * @return					an array depicting the number of times a category class appeared for each input type of the feature.
	 */
	public int[][] getCategoryOfInputsForFeature(int featureNumber) {
		int[] featureInputs = featuresInputs[featureNumber];

		int numberOfInputsTypes = getNumberOfInputTypesInColumn(featuresInputs[featureNumber]);
		int numberOfCategoryTypes = getNumberOfInputTypesInColumn(categories);

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

	/**
	 * This function gets the class each input led to in for each feature.
	 * @return  A 3D array, category class of each input under each feature.
	 */
	public int[][][] countClassesOfFeatureInputs() {

		int[][][] results = new int[getFeaturesInputs().length-1][][];

		for(int featureIterator = 0; featureIterator < getFeaturesInputs().length-1; featureIterator++) {
			int[][] categoryOfInputs = getCategoryOfInputsForFeature(featureIterator);
			results[featureIterator] = categoryOfInputs;
//            System.out.println("FEATURE NUMBER: " + (featureIterator+1) + "\n" + Arrays.deepToString(categoryOfInputs));
		}

		return results;
	}

	/**
	 * this function searches for the number of occurences of a value given (i.e.'-2147483648') in a given column. and takes away the number
	 * of rows with that value in it.
	 * @param valueToSearch 	value to search for.
	 * @return					number of rows with the specified value in the specified column number.
	 */
	public int getNumberOfRowsInNewFeaturesInputs(int featureNumber, int valueToSearch) {
		int numberOfRows = featuresInputs[0].length;

		for(int inputIterator = 0; inputIterator < featuresInputs[featureNumber].length; inputIterator++)
			if(featuresInputs[featureNumber][inputIterator] == valueToSearch)
				numberOfRows--;

		return numberOfRows;
	}

	public int[][] getFeaturesInputs() {
		return featuresInputs;
	}

	public void setFeaturesInputs(int[][] featuresInputs) {
		this.featuresInputs = featuresInputs;
	}

	public int[] getCategories() {
		return categories;
	}

	public void setCategories(int[] categories) {
		this.categories = categories;
	}

	public int[][][] getCountOfClassesOfFeatureInputs() {
		return countOfClassesOfFeatureInputs;
	}

	public void setCountOfClassesOfFeatureInputs(int[][][] countOfClassesOfFeatureInputs) {
		this.countOfClassesOfFeatureInputs = countOfClassesOfFeatureInputs;
	}
}
