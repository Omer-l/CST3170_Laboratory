package lab12;

import lab18.Utils;

import java.util.Arrays;
import java.util.Random;

public class Perceptron {
    private double[][] X;
    private int[] y;
    private Random random = new Random(); //for testing purposes
    private final int numberOfFeatures;
    private double[] weights;

    public Perceptron(double[][] X, int[] y, long seed, int numberOfFeatures) {
        this.X = X;
        this.y = y;
        this.random.setSeed(seed);
        this.numberOfFeatures = numberOfFeatures;
        this.weights = randomiseWeights(5);
    }
    //no seed
    public Perceptron(double[][] X, int[] y, int numberOfFeatures) {
        this.X = X;
        this.y = y;
        this.numberOfFeatures = numberOfFeatures;
        this.weights = randomiseWeights(5);
    }

    public void perceptronLearningAlgorithm() {
        double yIntercept = 5;
        weights = randomiseWeights(yIntercept);
        int[] misclassifiedExamples = predict(X, y, weights); //indexes of misclassified
        System.out.println(Arrays.toString(weights));

        while (misclassifiedExamples.length != 0) {
            System.out.println(Arrays.toString(misclassifiedExamples));
            int misclassifiedIndex = pickOneFrom(misclassifiedExamples); //chooses a random example.
            double[] x = X[misclassifiedIndex];
            int actualClassification = y[misclassifiedIndex];
            weights = updateWeights(actualClassification, weights, x);
            System.out.println(Arrays.toString(weights));
            misclassifiedExamples = predict(X, y, weights); //indexes of misclassified
        }

    }

    private double[] randomiseWeights(double yIntercept) {
        int numberOfFeatures = 3; //x and y and y-intercept
        double[] weights = new double[numberOfFeatures];
        weights[0] = yIntercept;

        for (int weightIndex = 1; weightIndex < numberOfFeatures; weightIndex++)
            weights[weightIndex] = random.nextDouble();

        return weights;
    }

    private int[] initialiseHypothesis(double[][] X, double[] weights) {
        int[] hypothesis = new int[X.length];

        for (int pointNumber = 0; pointNumber < X.length; pointNumber++) {
            double[] features = X[pointNumber];
            hypothesis[pointNumber] = Utils.getHypothesis(features, weights);
        }

        return hypothesis;
    }

    private int[] predict(double[][] X, int[] y, double[] weights) {
        int[] predict = new int[X.length];
        int[] hypothesis = initialiseHypothesis(X, weights);
        System.out.println(Arrays.toString(hypothesis));
        int numberOfMisclassified = 0;
        //get number of misclassified
        for (int predictionNumber = 0; predictionNumber < predict.length; predictionNumber++) {
            int prediction = hypothesis[predictionNumber];
            int actual = y[predictionNumber];

            if (prediction != actual)
                numberOfMisclassified++;
        }

        //After counting, finally provide the indexes of those misclassified.
        int[] misclassifiedIndexes = new int[numberOfMisclassified];
        for (int predictionNumber = 0, misclassifiedIndex = 0; predictionNumber < predict.length; predictionNumber++) {
            int prediction = hypothesis[predictionNumber];
            int actual = y[predictionNumber];

            if (prediction != actual)
                misclassifiedIndexes[misclassifiedIndex++] = predictionNumber;
        }

        return misclassifiedIndexes;
    }

    private int pickOneFrom(int[] misclassifiedExamples) {
        int index = (int) (Math.random() * misclassifiedExamples.length); //any
        return misclassifiedExamples[index];
    }

    private double[] updateWeights(int actualClassification, double[] weights, double[] x) {
        double[] newWeights = new double[weights.length];
        if (actualClassification == 1) //the angle is larger? than 90 degrees
            newWeights = Utils.add1DVectors(weights, x);
        else
            newWeights = Utils.subtract1DVectors(weights, x);
        return newWeights;
    }

    public double[] getWeights() {
        return weights;
    }
}
