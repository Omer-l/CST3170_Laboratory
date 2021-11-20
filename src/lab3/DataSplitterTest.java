package lab3;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

class DataSplitterTest {

    private static final FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
    private static final DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.
    private int[][] featuresInputs = decisionTable.getFeaturesInputs();
    private int[] categories = decisionTable.getCategories();

//    @org.junit.jupiter.api.Test
//    public void getLeastImpureIndex() {
//        DataSplitter measurerOfGoodness = new DataSplitter(featuresInputs, categories);
//
//        int[] indexToPrune = {3, 1, 2}; //where FEATURE 3 is 1, category class is always 3. So should remove all rows where feature 3 is 1.
//
//        int[] goodnessIndex = measurerOfGoodness.getNextGoodnessIndex();
//    }

    @org.junit.jupiter.api.Test
    public void removeAllOccurencesOfInput() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);

        int categoryClass = 3;
        int featureNumber = 3;
        int featureInput = 1;

        dataSplitter.markAllOccurencesOfInputToRemove(categoryClass, featureNumber, featureInput);

        for(int i = 0; i < featuresInputs.length; i++) {
            if(featuresInputs[featureNumber][i] == featureInput && categories[i] == categoryClass) {
                int expected = Integer.MIN_VALUE;
                int actual = featuresInputs[0][i];
                Assertions.assertEquals(expected, actual);
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void getNumberOfRowsInNewFeaturesInputs() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);

        dataSplitter.setFeaturesInputs(new int[][] {{-2147483648, 1, 1, 1, -2147483648, 1, 1, 1, -2147483648, 2, 2, 2, -2147483648, 2, 2, 2, -2147483648, -2147483648, 3, 3, -2147483648, 3, 3, 3}, {1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2}, {1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2}, {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, {3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 3, 3, 3, 3, 1, 3, 2, 3, 3}});

        int expected = 7;
        int actual = dataSplitter.getNumberOfRowsInNewFeaturesInputs(0, Integer.MIN_VALUE);

        Assertions.assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    public void removeMarkedRows1Time() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);

        int categoryClass = 3;
        int featureNumber = 3;
        int featureInput = 1;

        dataSplitter.markAllOccurencesOfInputToRemove(categoryClass, featureNumber, featureInput);

        dataSplitter.removeMarkedRows();
        System.out.println(Arrays.deepToString(dataSplitter.getFeaturesInputs()));
        int[][] expecteds = {{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},{1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2},{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},{2, 1, 2, 1, 2, 1, 2, 3, 3, 1, 2, 3}};
        int[][] actuals = dataSplitter.getFeaturesInputs();

        Assertions.assertArrayEquals(expecteds, actuals);
    }

    @org.junit.jupiter.api.Test
    public void removeMarkedRows3Times() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);

        int categoryClass = 3;
        int featureNumber = 3;
        int featureInput = 1;

        dataSplitter.markAllOccurencesOfInputToRemove(categoryClass, featureNumber, featureInput);
        dataSplitter.removeMarkedRows();
        System.out.println(Arrays.deepToString(dataSplitter.getFeaturesInputs()) + " -> CATS: " + Arrays.toString(dataSplitter.getCategories()));

        categoryClass = 3;
        featureNumber = 2;
        featureInput = 1;

        dataSplitter.markAllOccurencesOfInputToRemove(categoryClass, featureNumber, featureInput);
        dataSplitter.removeMarkedRows();
        System.out.println(Arrays.deepToString(dataSplitter.getFeaturesInputs()) + " -> CATS: " + Arrays.toString(dataSplitter.getCategories()));

        categoryClass = 1;
        featureNumber = 0;
        featureInput = 2;

        dataSplitter.markAllOccurencesOfInputToRemove(categoryClass, featureNumber, featureInput);
        dataSplitter.removeMarkedRows();
        System.out.println(Arrays.deepToString(dataSplitter.getFeaturesInputs()) + " -> CATS: " + Arrays.toString(dataSplitter.getCategories()));

//        int[][] expecteds = {{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},{1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2},{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
//                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},{2, 1, 2, 1, 2, 1, 2, 3, 3, 1, 2, 3}};
//        int[][] actuals = dataSplitter.getFeaturesInputs();

//        Assertions.assertArrayEquals(expecteds, actuals);
    }

    @org.junit.jupiter.api.Test
    public void getMean() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories); //[[2, 2, 4], [1, 2, 5], [1, 1, 6]] test values
        int[] variables = {2, 2, 4};

        double expected = 2.6666666666666665;
        double actual = dataSplitter.calculateMean(variables);

        Assertions.assertEquals(expected, actual, 1e-15);
    }

    @org.junit.jupiter.api.Test
    public void calculateVariance() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);
        int[] variables = {2, 2, 4};
        double variance = dataSplitter.calculateVariance(variables);

        System.out.println(variance);
    }

    @org.junit.jupiter.api.Test
    public void getVarianceForFeature() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);

        int[][] variables = dataSplitter.getCountOfClassesOfFeatureInputs()[0];
        double[] actuals = dataSplitter.getVariancesForFeature(variables);

        double[] expecteds = {0.8888888888888888, 2.8888888888888893, 5.555555555555556};

        Assertions.assertArrayEquals(expecteds, actuals, 1e-15);
    }

    /*TEST VALUES
[[2, 2, 4], [1, 2, 5], [1, 1, 6]]
[[3, 2, 7], [1, 3, 8]]
[[0, 5, 7], [4, 0, 8]]
[[0, 0, 12], [4, 5, 3]]
 */

    @org.junit.jupiter.api.Test
    public void getVariancesForAllFeatures() {
        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);

        double[][] actuals = dataSplitter.getVariancesForAllFeatures();

        double[][] expecteds = {{0.8888888888888888, 2.8888888888888893, 5.555555555555556},
                                {4.666666666666667, 8.666666666666666},
                                {8.666666666666666, 10.666666666666666},
                                {32.0, 0.6666666666666666}};

        Assertions.assertArrayEquals(expecteds, actuals);
    }


}