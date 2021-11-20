package lab3;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
        DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.

        int[][] featuresInputs = decisionTable.getFeaturesInputs();
        int[] categories = decisionTable.getCategories();

        Pruner pruner = new Pruner(featuresInputs, categories);

        for(int featureIterator = 0; featureIterator < featuresInputs.length-1; featureIterator++) {
            int[][] categoryOfInputs = pruner.getCategoryOfInputsForFeature(decisionTable, featureIterator);
            System.out.println("FEATURE NUMBER: " + (featureIterator+1) + "\n" + Arrays.deepToString(categoryOfInputs));
        }
    }

}
