package prep;

/**
 * This class has the functions to get the training row (nearest neighbour) for each test row.
 * Uses Euclidean distance to achieve this.
 * OUTPUT: correct: 2755... incorrect: 55... = 98.04% accuracy
 */
public class NearestNeighbour extends HandwrittenDigitClassifierAlgorithm{

    public NearestNeighbour(String algorithmName, Row[] trainingRows, Row[] testRows) {
        super(algorithmName, trainingRows, testRows);
    }

    //runs the algorithm
    @Override
    public void run() {

        for(Row testRow : getTestRows()) {
            //gets classification based on euclidean distance
            int actualClassificationOfRow = classify(testRow);
            int expectedClassificationOfRow = testRow.getClassification();

            //tests whether learning approach gave the right classification.
            if(actualClassificationOfRow == expectedClassificationOfRow)
                add1Correct();
            else
                add1Incorrect();
        }
    }


    /** classifies new handwritten data by finding closest euclidean distance of the trained data, and then returns the index of that row.
     *
     * @param newRow    the row to classify
     * @return          the closest neighbour
     */
    private int classify(Row newRow) {
        int closestNeighbourIndex = -1;
        double closestNeighbourDistance = Double.MAX_VALUE;
        Row[] trainingRows = getTrainingRows();

        //gets closest euclidean distance
        for(int rowIndex = 0; rowIndex < trainingRows.length; rowIndex++) {
            Row neighbour = trainingRows[rowIndex];
            double distanceToNeighbour = newRow.getEuclideanDistanceTo(neighbour);

            //closer than current closest neighbour?
            if(distanceToNeighbour < closestNeighbourDistance) {
                closestNeighbourIndex = rowIndex;
                closestNeighbourDistance = distanceToNeighbour;
            }
        }

        return trainingRows[closestNeighbourIndex].getClassification();
    }

}
