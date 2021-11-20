package lab3;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
        DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.

        int[][] featuresInputs = decisionTable.getFeaturesInputs();
        int[] categories = decisionTable.getCategories();

        DataSplitter dataSplitter = new DataSplitter(featuresInputs, categories);

        for(int[][] result : dataSplitter.getCountOfClassesOfFeatureInputs())
            System.out.println(Arrays.deepToString(result));
    }


}