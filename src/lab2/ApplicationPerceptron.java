package lab2;


import lab18.Utils;

import java.util.Arrays;
import java.util.Random;

public class ApplicationPerceptron {
    private static FileReaderLab2 fileReader = new FileReaderLab2("lineClass1.txt");
    private static PointLab2[] allPoints = fileReader.getData();
    private static PointLab2[] A = PointLab2.getClassifiedPoints(allPoints, 'A');
    private static PointLab2[] B = PointLab2.getClassifiedPoints(allPoints, 'B');
    private static double[] weights;
    private static Random random = new Random(); //for testing purposes
    private static int numberOfInputs = 3; //x and y and y-intercept

    public static void main(String[] args) {
        random.setSeed(230764458418060108L); //always get the same randoms
        double[][] X = initialiseX();
        int[] y = initialiseY(); //'A' = 1, 'B' = -1
        System.out.println(Arrays.deepToString(X) + "\n" + Arrays.toString(y));
        boolean[] correctlyClassified = perceptronLearningAlgorithm(X, y);
    }

    private static double[][] initialiseX() {
        double[][] X = new double[allPoints.length][numberOfInputs];
        for(int pointIndex = 0; pointIndex < allPoints.length; pointIndex++) {
            X[pointIndex][0] = 1;
            X[pointIndex][1] = allPoints[pointIndex].getX();
            X[pointIndex][2] = allPoints[pointIndex].getY();
        }
        return X;
    }

    private static int[] initialiseY() {
        int[] y  = new int[allPoints.length];
        for(int pointIndex = 0; pointIndex < allPoints.length; pointIndex++) {
            char classification = allPoints[pointIndex].getClassification();

            if(classification == 'A')
                y[pointIndex] = 1;
            else
                y[pointIndex] = -1;
        }
        return y;
    }

    public static boolean[] perceptronLearningAlgorithm(double[][] X, int[] y) {
        double yIntercept = 5;
        weights = randomiseWeights(yIntercept);
        double[] hypothesis = initialiseHypothesis(X, weights, yIntercept);//Utils.getHypothesis(X[0], weights, yIntercept);
        System.out.println(Arrays.toString(hypothesis));
//        boolean[] misclassifiedExamples = predict(hypothesis, X, y, weights); //indexes of misclassified
        System.out.println(Arrays.toString(weights));
        return null;
    }

    private static double[] randomiseWeights(double yIntercept) {
        int numberOfInputs = 3; //x and y and y-intercept
        double[] weights = new double[numberOfInputs];
        weights[0] = yIntercept;

        for(int weightIndex = 1; weightIndex < numberOfInputs; weightIndex++)
            weights[weightIndex] = random.nextDouble();

        return weights;
    }

    private static double[] initialiseHypothesis(double[][] X, double[] weights, double yIntercept) {
        double[] hypothesis = new double[X.length];

        for(int pointNumber = 0; pointNumber < X.length; pointNumber++) {
            double[] inputs = X[pointNumber];
            hypothesis[pointNumber] = Utils.getHypothesis(inputs, weights, yIntercept);
        }

    return hypothesis;
    }
}
