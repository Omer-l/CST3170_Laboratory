package prep;

public class Application {
    private final static FileReaderDataset trainingFileReader = new FileReaderDataset("dataset1.csv"); //for training file reading
    private final static FileReaderDataset testingFileReader = new FileReaderDataset("dataset2.csv"); //for testing file reading
    private final static DataSet[] TRAINING_DATA_SETS = trainingFileReader.getData(); //training data
    private final static DataSet[] TESTING_DATA_SETS = testingFileReader.getData(); //test data

    public static void main(String[] args) {

        int correct = 0; //counter for correct classifications
        int incorrect = 0; //counter for incorrect classifications

        for(DataSet testDataSet : TESTING_DATA_SETS) {
            //gets classification based on euclidean distance
            int actualClassificationOfRow = classify(testDataSet);
            int expectedClassificationOfRow = testDataSet.getClassification();

            //tests whether learning approach gave the right classification.
            if(actualClassificationOfRow == expectedClassificationOfRow)
                correct++;
            else
                incorrect++;
        }

        //output results
        double accuracy = ((double)correct / TESTING_DATA_SETS.length) * 100.00;
        System.out.println("correct: " + correct + "... incorrect: " + incorrect + "... = " + accuracy);
    }

    //classifies new handwritten data by finding closest euclidean distance of the trained data, and then returns the index of that row.
    public static int classify(DataSet newDataSet) {
        int closestNeighbourIndex = -1;
        double closestNeighbourDistance = Double.MAX_VALUE;

        for(int rowIndex = 0; rowIndex < TRAINING_DATA_SETS.length; rowIndex++) {
            DataSet neighbour = TRAINING_DATA_SETS[rowIndex];
            double distanceToNeighbour = newDataSet.getEuclideanDistanceTo(neighbour);

            if(distanceToNeighbour < closestNeighbourDistance) {
                closestNeighbourIndex = rowIndex;
                closestNeighbourDistance = distanceToNeighbour;
            }
        }

        return TRAINING_DATA_SETS[closestNeighbourIndex].getClassification();
    }


}
