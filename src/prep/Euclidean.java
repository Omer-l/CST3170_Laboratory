package prep;

public class Euclidean {
    private final Row[] trainingRows;
    private final Row[] testRows;
    private int correctClassificationCounter = 0; //counter for correct classifications
    private int incorrectClassificationCounter = 0;//counter for incorrect classifications

    public Euclidean(Row[] trainingRows, Row[] testRows) {
        this.trainingRows = trainingRows;
        this.testRows = testRows;
    }

    public void run() {

        for(Row testRow : testRows) {
            //gets classification based on euclidean distance
            int actualClassificationOfRow = classify(testRow);
            int expectedClassificationOfRow = testRow.getClassification();

            //tests whether learning approach gave the right classification.
            if(actualClassificationOfRow == expectedClassificationOfRow)
                correctClassificationCounter++;
            else
                incorrectClassificationCounter++;
        }
    }


    //classifies new handwritten data by finding closest euclidean distance of the trained data, and then returns the index of that row.
    public int classify(Row newRow) {
        int closestNeighbourIndex = -1;
        double closestNeighbourDistance = Double.MAX_VALUE;

        for(int rowIndex = 0; rowIndex < trainingRows.length; rowIndex++) {
            Row neighbour = trainingRows[rowIndex];
            double distanceToNeighbour = newRow.getEuclideanDistanceTo(neighbour);

            if(distanceToNeighbour < closestNeighbourDistance) {
                closestNeighbourIndex = rowIndex;
                closestNeighbourDistance = distanceToNeighbour;
            }
        }

        return trainingRows[closestNeighbourIndex].getClassification();
    }

    @Override
    public String toString() {
        //output results
        double accuracy = ((double)correctClassificationCounter / testRows.length) * 100.00;
        return "correct: " + correctClassificationCounter + "... incorrect: " + incorrectClassificationCounter + "... = " + accuracy;
    }
}
