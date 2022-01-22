package lab03;

//This class determines the next best splitter feature  for the decision tree.
public class DataSplitter extends Categoriser {
    FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
    DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.

    public DataSplitter(int[][] featuresInputs, int[] categories) {
        super(featuresInputs, categories);
    }

    /**
     * This function removes rows marked with a value (In this case, feature 1's inputs' that are marked with -2147483648)
     */
    public void removeMarkedRows() {
        int[][] featuresInputs = getFeaturesInputs();
        int newNumberOfRows = getNumberOfRowsInNewFeaturesInputs(0, Integer.MIN_VALUE);
        int[][] newFeaturesInputs = new int[featuresInputs.length][newNumberOfRows];
        int[] newCategories = new int[newNumberOfRows];

        for(int rowInputIterator = 0, newFeatureRowIterator = 0; rowInputIterator < featuresInputs[0].length; rowInputIterator++) {

            if(featuresInputs[0][rowInputIterator] != Integer.MIN_VALUE) { //then add this row (not whole column)
                int[] row = new int[featuresInputs.length];

                for(int columnInputIterator = 0; columnInputIterator < featuresInputs.length; columnInputIterator++) {
                    newFeaturesInputs[columnInputIterator][newFeatureRowIterator] = featuresInputs[columnInputIterator][rowInputIterator];
                    newCategories[newFeatureRowIterator] = getCategories()[rowInputIterator];
                }
                newFeatureRowIterator++;
            }
        }

        setFeaturesInputs(newFeaturesInputs);
        setCategories(newCategories);
    }

    /**
     * This function marks the specified rows for deletion
     * @param categoryClass     category number in row
     * @param featureNumber     which feature/column?
     * @param featureInput      the input of the feature in the specified column
     * @return
     */
    public void markAllOccurencesOfInputToRemove(int categoryClass, int featureNumber, int featureInput) {
        int[][] featuresInputs = getFeaturesInputs();

        for(int row = 0; row < featuresInputs[0].length; row++) {
            int currentFeatureInput = featuresInputs[featureNumber][row];

            if(currentFeatureInput == featureInput && getCategories()[row] == categoryClass) {
                //set first feature's input in the row to negative to mark row as `to remove`.
                featuresInputs[0][row] = Integer.MIN_VALUE;
            }
        }
    }

    /**
     * this function sums up the given array and returns the mean
     * @param variables     variables to add up and solve the mean
     * @return              the mean value.
     */
    public double calculateMean(int[] variables) {
        double sum = 0;
        for(int num : variables)
            sum += num;

        double mean = sum / variables.length;

        return mean;
    }
    /**
     * this function calculates the variance of variables.
     * @param variables     variables for the formula
     * @return              a variance value.
     */
    public double calculateVariance(int[] variables) {

        double mean = calculateMean(variables);

        double variance = 0;

        for(int variable : variables)
            variance += Math.pow( (variable - mean), 2);

        variance = variance / variables.length;

        return variance;
    }

    /**
     * This function returns an array of each input's variance value in a feature.
     * @param countsOfClassesForInputs      count for each category that each input leads to in the feature.
     * @return                              an array of variances.
     */
    public double[] getVariancesForFeature(int[][] countsOfClassesForInputs) {
        double[] variances = new double[countsOfClassesForInputs.length];

        for(int i = 0; i < variances.length; i++) {
            variances[i] = calculateVariance(countsOfClassesForInputs[i]);
        }

        return variances;
    }

    /**
     * This function returns an array of each input's variance value in all features.
     * @return                              an array of variances.
     */
    public double[][] getVariancesForAllFeatures() {
        int[][][] countsOfClassesOfAllFeatureInputs = getCountOfClassesOfFeatureInputs();
        double[][] allVariances = new double[countsOfClassesOfAllFeatureInputs.length][];

        int i = 0;
        for(int[][] countOfClassesForInputs : countsOfClassesOfAllFeatureInputs) {
            double[] variances = getVariancesForFeature(countOfClassesForInputs);
            allVariances[i] = variances;
            i++;
        }
        return allVariances;
    }

    public int[] getNextGoodnessIndex() {
        double currentLowestVariance = Double.MAX_VALUE; // lower the better.
        int[] currentLowestVarienctIndex = null; //index of best.

        for(int i = 0; i < getFeaturesInputs().length; i++) {

        }

        return currentLowestVarienctIndex;
    }
}
