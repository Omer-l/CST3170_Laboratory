package prep;

public abstract class HandwrittenDigitClassifierAlgorithm {
    private final Row[] trainingRows;
    private final Row[] testRows;
    private int correctClassificationCounter = 0; //counter for correct classifications
    private int incorrectClassificationCounter = 0;//counter for incorrect classifications

    public HandwrittenDigitClassifierAlgorithm(Row[] trainingRows, Row[] testRows) {
        this.trainingRows = trainingRows;
        this.testRows = testRows;
    }

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
        return "correct: " + correctClassificationCounter + "... incorrect: " + incorrectClassificationCounter + "... = " + accuracy;
    }
}
