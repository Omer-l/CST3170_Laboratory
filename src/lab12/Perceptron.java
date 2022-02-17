package lab12;

import lab18.MatrixUtils;

import java.util.Random;

public class Perceptron {
    private double[][] X;
    private double[] y;
    private Random random = new Random(); //for testing purposes
    private final int numberOfFeatures;
    private double[] weights;

    public Perceptron(double[][] X, double[] y, long seed, int numberOfFeatures) {
        this.numberOfFeatures = numberOfFeatures + 1;
        this.X = augmentX(X, this.numberOfFeatures);
        this.y = y;
        this.random.setSeed(seed);
        this.weights = randomiseWeights(5);
    }
    //no seed
    public Perceptron(double[][] X, double[] y, int numberOfFeatures) {
        this.numberOfFeatures = numberOfFeatures + 1;
        this.X = augmentX(X, this.numberOfFeatures);
        this.y = y;
        this.weights = randomiseWeights(5);
    }


    /**
     * Augments the vectors, the inputs, the x and y by making x0 = 1
     * @param X     the vector to augment
     * @return      an augmented vector, for which x0 = 1
     */
    public static double[][] augmentX(double[][] X, int numberOfFeatures) {
        double[][] augmentedX = new double[X.length][numberOfFeatures];
        for (int pointIndex = 0; pointIndex < augmentedX.length; pointIndex++) {
            double[] currentPoint = X[pointIndex];
            augmentedX[pointIndex][0] = 1;
            for (int featureIndex = 0; featureIndex < currentPoint.length; featureIndex++) {
                double feature = currentPoint[featureIndex];
                augmentedX[pointIndex][featureIndex+1] = feature;
            }
        }
        return augmentedX;
    }

    public void perceptronLearningAlgorithm() {
        double yIntercept = 5;
        weights = randomiseWeights(yIntercept);
        int[] misclassifiedExamples = predict(X, y, weights); //indexes of misclassified
//        System.out.println(Arrays.toString(weights));

        while (misclassifiedExamples.length != 0) {
//            System.out.println(Arrays.toString(misclassifiedExamples));
            int misclassifiedIndex = pickOneFrom(misclassifiedExamples); //chooses a random example.
            double[] x = X[misclassifiedIndex];
            double actualClassification = y[misclassifiedIndex];
            weights = updateWeights(actualClassification, weights, x);
//            System.out.println(Arrays.toString(weights));
            misclassifiedExamples = predict(X, y, weights); //indexes of misclassified
        }

    }

    private double[] randomiseWeights(double yIntercept) {
        double[] weights = new double[numberOfFeatures];
        weights[0] = yIntercept;

        for (int weightIndex = 1; weightIndex < numberOfFeatures; weightIndex++)
            weights[weightIndex] = random.nextDouble();

        return weights;
    }

    public int[] initialiseHypothesis(double[][] X, double[] weights) {
        int[] hypothesis = new int[X.length];

        for (int pointNumber = 0; pointNumber < X.length; pointNumber++) {
            double[] features = X[pointNumber];
            hypothesis[pointNumber] = MatrixUtils.getHypothesis(features, weights);
//            hypothesis[pointNumber] = MatrixUtils.getHypothesisSoftMargin(features, weights, y[pointNumber], 0);
        }

        return hypothesis;
    }

    public int[] predict(double[][] X, double[] y, double[] weights) {
        int[] predict = new int[X.length];
        int[] hypothesis = initialiseHypothesis(X, weights);
//        System.out.println(Arrays.toString(hypothesis));
        int numberOfMisclassified = 0;
        //get number of misclassified
        for (int predictionNumber = 0; predictionNumber < predict.length; predictionNumber++) {
            int prediction = hypothesis[predictionNumber];
            double actual = y[predictionNumber];

            if (prediction != actual)
                numberOfMisclassified++;
        }

        //After counting, finally provide the indexes of those misclassified.
        int[] misclassifiedIndexes = new int[numberOfMisclassified];
        for (int predictionNumber = 0, misclassifiedIndex = 0; predictionNumber < predict.length; predictionNumber++) {
            int prediction = hypothesis[predictionNumber];
            double actual = y[predictionNumber];

            if (prediction != actual)
                misclassifiedIndexes[misclassifiedIndex++] = predictionNumber;
        }

        return misclassifiedIndexes;
    }

    private int pickOneFrom(int[] misclassifiedExamples) {
        int index = (int) (Math.random() * misclassifiedExamples.length); //any
        return misclassifiedExamples[index];
    }

    private double[] updateWeights(double actualClassification, double[] weights, double[] x) {
        double[] newWeights = new double[weights.length];
        if (actualClassification == 1) //the angle is larger? than 90 degrees
            newWeights = MatrixUtils.add1DVectors(weights, x);
        else
            newWeights = MatrixUtils.subtract1DVectors(weights, x);
        return newWeights;
    }

    public double[] getWeights() {
        return weights;
    }

    @Override
    public String toString() {
        String result = "";
        for(double w : weights)
            result += w + ", \t";
        return result + " \t" + MatrixUtils.getLineEquation(weights);
    }

    public double[] getY() {
        return y;
    }

    public double[][] getX() {
        return X;
    }

    public void setX(double[][] x) {
        X = x;
    }

    public void setY(double[] y) {
        this.y = y;
    }
}