package prep;

/**
 * Fukcing Chris this clearly runs the algorithms BITCH
 */
public class Application {
    private final static FileReaderDataset trainingFileReader = new FileReaderDataset("dataset1.csv"); //for training file reading
    private final static FileReaderDataset testingFileReader = new FileReaderDataset("dataset2.csv"); //for testing file reading
    private final static Row[] TRAINING_DATA_SETS = trainingFileReader.getData(); //training data
    private final static Row[] TESTING_DATA_SETS = testingFileReader.getData(); //test data

    public static void main(String[] args) {
        NearestNeighbour nearestNeighbour = new NearestNeighbour("Nearest Neighbour", TRAINING_DATA_SETS, TESTING_DATA_SETS);
        nearestNeighbour.run();
        System.out.println(nearestNeighbour);
    }

}
