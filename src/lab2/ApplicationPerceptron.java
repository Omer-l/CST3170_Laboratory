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
        int[] misclassifiedExamples = predict(X, y, weights); //indexes of misclassified
        System.out.println(Arrays.toString(misclassifiedExamples));
        System.out.println(Arrays.toString(weights));

        while(misclassifiedExamples.length != 0) {
            int misclassifiedIndex = pickOneFrom(misclassifiedExamples); //chooses a random example.
            double[] x = X[misclassifiedIndex];
            int actualClassification = y[misclassifiedIndex];
            updateWeights(actualClassification, weights, x);
        }
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

    private static int[] initialiseHypothesis(double[][] X, double[] weights) {
        int[] hypothesis = new int[X.length];

        for(int pointNumber = 0; pointNumber < X.length; pointNumber++) {
            double[] inputs = X[pointNumber];
            hypothesis[pointNumber] = Utils.getHypothesis(inputs, weights);
        }

    return hypothesis;
    }

    private static int[] predict(double[][] X, int[] y, double[] weights) {
        int[] predict = new int[X.length];
        int[] hypothesis = initialiseHypothesis(X, weights);
        System.out.println(Arrays.toString(hypothesis));
        int numberOfMisclassified = 0;
        //get number of misclassified
        for(int predictionNumber = 0; predictionNumber < predict.length; predictionNumber++) {
            int prediction = hypothesis[predictionNumber];
            int actual = y[predictionNumber];

            if(prediction != actual)
                numberOfMisclassified++;
        }

        //After counting, finally provide the indexes of those misclassified.
        int[] misclassifiedIndexes = new int[numberOfMisclassified];
        for(int predictionNumber = 0, misclassifiedIndex = 0; predictionNumber < predict.length; predictionNumber++) {
            int prediction = hypothesis[predictionNumber];
            int actual = y[predictionNumber];

            if(prediction != actual)
                misclassifiedIndexes[misclassifiedIndex++] = predictionNumber;
        }

        return misclassifiedIndexes;
    }

    private static int pickOneFrom(int[] misclassifiedExamples) {
        int index = (int)(Math.random() * misclassifiedExamples.length); //any
        return misclassifiedExamples[index];
    }

    private static void updateWeights(int actualClassification, double[] weights, double[] x) {
        if(actualClassification == 1) //the angle is larger? than 90 degrees
            weights = Utils.add1DVectors(weights, x);
        else
            weights = Utils.subtract1DVectors(weights, x);
    }
}
