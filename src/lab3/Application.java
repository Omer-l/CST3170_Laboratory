package lab3;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");

        int[][] featuresAndCategory = fileReader.getData();

        DecisionTableRenderer decisionTable = new DecisionTableRenderer(featuresAndCategory);

        int[][] featuresInputs = decisionTable.getFeaturesInputs();
        int[] categories = decisionTable.getCategories();

        Categoriser categoriser = new Categoriser(featuresInputs, categories);

        for(int featureIterator = 0; featureIterator < featuresInputs.length; featureIterator++) {
            int[][] categoryOfInputs = categoriser.getCategoryOfInputsForFeature(decisionTable, featureIterator);
            System.out.println("FEATURE NUMBER: " + featureIterator + "\n" + Arrays.deepToString(categoryOfInputs));
        }
    }

}
