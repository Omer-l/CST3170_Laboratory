package lab3;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

class MeasurerOfGoodnessTest {

    private FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
    private DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.
    private int[][] featuresInputs = decisionTable.getFeaturesInputs();
    private int[] categories = decisionTable.getCategories();

//    @org.junit.jupiter.api.Test
//    public void getLeastImpureIndex() {
//        MeasurerOfGoodness measurerOfGoodness = new MeasurerOfGoodness(featuresInputs, categories);
//
//        int[] indexToPrune = {3, 1, 2}; //where FEATURE 3 is 1, category class is always 3. So should remove all rows where feature 3 is 1.
//
//        int[] goodnessIndex = measurerOfGoodness.getNextGoodnessIndex();
//    }

    @org.junit.jupiter.api.Test
    public void removeAllOccurencesOfInput() {
        MeasurerOfGoodness measurerOfGoodness = new MeasurerOfGoodness(featuresInputs, categories);

        int categoryClass = 3;
        int featureNumber = 3;
        int featureInput = 1;

        measurerOfGoodness.markAllOccurencesOfInputToRemove(categoryClass, featureNumber, featureInput);

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
        MeasurerOfGoodness measurerOfGoodness = new MeasurerOfGoodness(featuresInputs, categories);

        measurerOfGoodness.setFeaturesInputs(new int[][] {{-2147483648, 1, 1, 1, -2147483648, 1, 1, 1, -2147483648, 2, 2, 2, -2147483648, 2, 2, 2, -2147483648, -2147483648, 3, 3, -2147483648, 3, 3, 3}, {1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2}, {1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2}, {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, {3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 3, 3, 3, 3, 1, 3, 2, 3, 3}});

        int expected = 7;
        int actual = measurerOfGoodness.getNumberOfRowsInNewFeaturesInputs(0, Integer.MIN_VALUE);

        Assertions.assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    public void removeMarkedRows() {
        MeasurerOfGoodness measurerOfGoodness = new MeasurerOfGoodness(featuresInputs, categories);

        int categoryClass = 3;
        int featureNumber = 3;
        int featureInput = 1;

        measurerOfGoodness.markAllOccurencesOfInputToRemove(categoryClass, featureNumber, featureInput);

        measurerOfGoodness.removeMarkedRows();
        System.out.println(Arrays.deepToString(measurerOfGoodness.getFeaturesInputs()));
        int[][] expecteds = {{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},{1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2},{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},{2, 1, 2, 1, 2, 1, 2, 3, 3, 1, 2, 3}};
        int[][] actuals = measurerOfGoodness.getFeaturesInputs();

        Assertions.assertArrayEquals(expecteds, actuals);
    }
}