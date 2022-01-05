package prep;

public class Application {
    private final static FileReaderDataset trainingFileReader = new FileReaderDataset("dataset1.csv"); //for training file reading
    private final static FileReaderDataset testingFileReader = new FileReaderDataset("dataset2.csv"); //for testing file reading
    private final static Row[] TRAINING_DATA_SETS = trainingFileReader.getData(); //training data
    private final static Row[] TESTING_DATA_SETS = testingFileReader.getData(); //test data

    public static void main(String[] args) {
        Euclidean euclidean = new Euclidean(TRAINING_DATA_SETS, TESTING_DATA_SETS);
        euclidean.run();
        System.out.println(euclidean);
    }

}