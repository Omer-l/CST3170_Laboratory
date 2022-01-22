package lab03;

public class DecisionTableRenderer {
    private int[][] featuresAndCategory;

    public DecisionTableRenderer(int[][] featuresAndCategory) {
        this.featuresAndCategory = featuresAndCategory;
    }


    /**
     * This function retrieves the desired column from a 2D array of features and category.
     * @param columnNumber			the desired column.
     * @return						array representing the column inputs.
     */
    private int[] getColumnInputs(int columnNumber) {
        int[] featureInputs = new int[featuresAndCategory.length];

        for(int rowIterator = 0; rowIterator < featuresAndCategory.length; rowIterator++)
            featureInputs[rowIterator] = featuresAndCategory[rowIterator][columnNumber];

        return featureInputs;
    }

    /**
     * This function gets the features from an array of features and category
     * @return	returns a 2D array, the rows in the array represent the columns in the table lenseData.txt
     */
    protected int[][] getFeaturesInputs() {
        int[][] featuresInputs = new int[featuresAndCategory[0].length][featuresAndCategory.length-1]; //take out the category column.
        for(int featureIterator = 0; featureIterator < featuresAndCategory[0].length; featureIterator++) //rows are the features.
            featuresInputs[featureIterator] = getColumnInputs(featureIterator);

        return featuresInputs;
    }

    /**
     * This function gets the category column (AKA LAST COLUMN) from an array of features and category
     * @return							returns a 2D array, the rows in the array represent the columns in the table lenseData.txt
     */
    protected int[] getCategories() {
        int categoryColumnIndex = featuresAndCategory[0].length - 1; //will be needed to get category
        int[] categories = getColumnInputs(categoryColumnIndex);

        return categories;
    }


}
