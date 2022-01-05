package prep;

public abstract class HandwrittenDigitClassifierAlgorithm {
    private final Row[] trainingRows;
    private final Row[] testRows;
    private String algorithmName;
    private int correctClassificationCounter = 0; //counter for correct classifications
    private int incorrectClassificationCounter = 0;//counter for incorrect classifications

    public HandwrittenDigitClassifierAlgorithm(String algorithmName, Row[] trainingRows, Row[] testRows) {
        this.algorithmName = algorithmName.toUpperCase();
        this.trainingRows = trainingRows;
        this.testRows = testRows;
    }

    //to run the algorithm
    public void run() {

    }

    public Row[] getTrainingRows() {
        return trainingRows;
    }

    public Row[] getTestRows() {
        return testRows;
    }

    //adds 1 to correct classification counter
    public void add1Correct() {
        correctClassificationCounter++;
    }

    //adds 1 to incorrect classification counter
    public void add1Incorrect() {
        incorrectClassificationCounter++;
    }

    @Override
    public String toString() {
        //output results
        double accuracy = ((double)correctClassificationCounter / testRows.length) * 100.00;
        double errorRate = 100.0 - accuracy;

        return "ALGORITHM NAME: \t\t\t" + algorithmName + "\nCORRECT CLASSIFICATIONS: \t" + correctClassificationCounter + "\nINCORRECT CLASSIFICATIONS: \t" + incorrectClassificationCounter + "\nACCURACY: \t\t\t\t\t" + accuracy + "%\nERROR RATE: \t\t\t\t" + errorRate;
    }
}
