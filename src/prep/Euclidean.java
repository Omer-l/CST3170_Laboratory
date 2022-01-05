package prep;

public class Euclidean extends HandwrittenDigitClassifierAlgorithm{

    public Euclidean(Row[] trainingRows, Row[] testRows) {
        super(trainingRows, testRows);
    }

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


    //classifies new handwritten data by finding closest euclidean distance of the trained data, and then returns the index of that row.
    public int classify(Row newRow) {
        int closestNeighbourIndex = -1;
        double closestNeighbourDistance = Double.MAX_VALUE;
        Row[] trainingRows = getTrainingRows();

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

}
