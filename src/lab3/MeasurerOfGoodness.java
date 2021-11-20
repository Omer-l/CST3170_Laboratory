package lab3;

import java.util.Arrays;

//This class determines the next best splitter feature  for the decision tree.
public class MeasurerOfGoodness extends Categoriser {
    FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
    DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.

    public MeasurerOfGoodness(int[][] featuresInputs, int[] categories) {
        super(featuresInputs, categories);
    }

    /**
     * This function removes rows marked with a value (In this case, feature 1's inputs' that are marked with -2147483648)
     */
    public void removeMarkedRows() {
        int[][] featuresInputs = getFeaturesInputs();

        int[][] newFeaturesInputs = new int[featuresInputs.length][getNumberOfRowsInNewFeaturesInputs(0, Integer.MIN_VALUE)];

        for(int rowInputIterator = 0, newFeatureRowIterator = 0; rowInputIterator < featuresInputs[0].length; rowInputIterator++) {

            if(featuresInputs[0][rowInputIterator] != Integer.MIN_VALUE) { //then add this row (not whole column)
                int[] row = new int[featuresInputs.length];

                for(int columnInputIterator = 0; columnInputIterator < featuresInputs.length; columnInputIterator++)
                    newFeaturesInputs[columnInputIterator][newFeatureRowIterator] = featuresInputs[columnInputIterator][rowInputIterator];
                newFeatureRowIterator++;
            }
        }

        setFeaturesInputs(newFeaturesInputs);
    }

    public int[] markAllOccurencesOfInputToRemove(int categoryClass, int featureNumber, int featureInput) {
        int[][] featuresInputs = getFeaturesInputs();

        for(int row = 0; row < featuresInputs[0].length; row++) {
            int currentFeatureInput = featuresInputs[featureNumber][row];

            if(currentFeatureInput == featureInput && getCategories()[row] == categoryClass) {
                //set first feature's input in the row to negative to mark row as `to remove`.
                featuresInputs[0][row] = Integer.MIN_VALUE;
            }
        }
        System.out.println(Arrays.deepToString(featuresInputs));
        return null;
    }

    public int[][][] splitTableUsingGoodnessIndex(int[] indexOfGoodness) {
        int row = indexOfGoodness[indexOfGoodness.length-1];
        int classOfCategory = getCategories()[row];

        return null;
    }

    public int[] getNextGoodnessIndex() {
        double currentLowestVariance = Double.MAX_VALUE; // lower the better.
        int[] currentLowestVarienctIndex = null; //index of best.

//        int[][][] newResults = new int
        for(int i = 0; i < getFeaturesInputs().length; i++) {

        }

        return currentLowestVarienctIndex;
    }
}
