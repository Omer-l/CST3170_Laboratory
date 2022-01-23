package lab18;

import lab12.Perceptron;

public class ApplicationSVM4Classifications {
    private final static double[][] ALL_POINTS = {{1, 6},{1, 7},{2, 5},{2, 8},
        {4, 2},{4, 3},{5, 1},{5, 2},{5, 3},{6, 1},{6, 2},{9, 4},{9, 7},{10, 5},{10, 6},{11, 6},{5, 9},{5, 10},{5, 11},{6, 9},{6, 10},{7, 10},{8, 11}};
    private final static int NUMBER_OF_LINES_ATTEMPTS_PER_CLASSIFICATION = 15;
    private final static int NUMBER_OF_CLASSIFICATIONS = 4;
    private static double[] classifications = {1, 1, 1, 1,
            2, 2, 2, 2, 2, 2, 2,
            3, 3, 3, 3, 3,
            4, 4, 4, 4, 4, 4, 4}; //for every point index, there is a classification.
    private static final int NUMBER_OF_FEATURES = ALL_POINTS[0].length; //x and y and y-intercept

    public static void main(String[] args) {
        Perceptron[][] perceptrons2D = new Perceptron[NUMBER_OF_CLASSIFICATIONS][NUMBER_OF_LINES_ATTEMPTS_PER_CLASSIFICATION];

        perceptrons2D[0] = getClassification(1);
        perceptrons2D[1] = getClassification(2);
        perceptrons2D[2] = getClassification(3);
        perceptrons2D[3] = getClassification(4);
        int weightIndex = 0;
        int classificationIndex = 0;
        for(Perceptron[] perceptrons : perceptrons2D) {
            System.out.print("\nPerceptron: \n");
            double[][] lineWeights = new double[perceptrons.length][perceptrons[0].getWeights().length];
            for (Perceptron perceptron : perceptrons) {
                System.out.print(perceptron + "\n");
                lineWeights[weightIndex++] = perceptron.getWeights();
            }
            double[] bestLineWeights = MatrixUtils.maximiseTheMinimumGeometricMargin(ALL_POINTS, lineWeights, perceptrons[classificationIndex].getY());
            System.out.print("****BEST LINE EQ: " + MatrixUtils.getLineEquation(bestLineWeights) + "*****");;
            weightIndex = 0;
        }
    }

    private static Perceptron[] getClassification(double desiredClassification) {

        //Transform the NUMBER_OF_CLASSIFICATIONS classifications into NUMBER_OF_CLASSIFICATIONS binary class problems
        double[] binaryClassifications = MatrixUtils.getBinaryClassifications(classifications, desiredClassification);
        double[][] augmented_X = initialiseX();
        Perceptron[] p = new Perceptron[NUMBER_OF_LINES_ATTEMPTS_PER_CLASSIFICATION];
        for(int i = 0; i < NUMBER_OF_LINES_ATTEMPTS_PER_CLASSIFICATION; i++) {
//            double[][] augmentedX = initialiseX(); //augmented simply means x0 = 1.
            Perceptron perceptron1 = new Perceptron(augmented_X, binaryClassifications, NUMBER_OF_FEATURES+1);
            perceptron1.perceptronLearningAlgorithm();
            p[i] = perceptron1;
        }

        return p;
    }

    private static double[][] initialiseX() {
        double[][] X = new double[ALL_POINTS.length][NUMBER_OF_FEATURES + 1];
        for (int pointIndex = 0; pointIndex < ALL_POINTS.length; pointIndex++) {
            double[] currentPoint = ALL_POINTS[pointIndex];
            X[pointIndex][0] = 1;
            for (int featureIndex = 0; featureIndex < NUMBER_OF_FEATURES; featureIndex++) {
                double feature = currentPoint[featureIndex];
                X[pointIndex][featureIndex+1] = feature;
            }
        }
        return X;
    }
}
