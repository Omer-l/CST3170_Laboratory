package lab12;


import lab18.FileReaderLab18;
import lab18.PointLab18;
import lab18.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * TODO:
 * Decision boundary plot
 * https://www.bing.com/search?q=perceptron+how+to+get+line+from+weights
 * https://www.desmos.com/calculator/xuj0ksmyvs
 * https://www.desmos.com/calculator/ts5eefdf6p
 */
public class ApplicationPerceptron {
    private static FileReaderLab18 fileReader = new FileReaderLab18("lineClass1.txt");
    private static final PointLab18[] ALL_POINTS = fileReader.getData();
    private static double[] weights;
    private static Random random = new Random(); //for testing purposes
    private static final int NUMBER_OF_FEATURES = ALL_POINTS[0].getFeatures().length; //x and y and y-intercept

    public static void main(String[] args) {
        random.setSeed(230764458418060108L); //always get the same randoms
        double[][] augmentedX = initialiseX(); //augmented simply means x0 = 1.
        int[] y = initialiseY(); //'A' = 1, 'B' = -1

        Perceptron perceptron1 = new Perceptron(augmentedX, y, 230764458418060108L, NUMBER_OF_FEATURES);
        perceptron1.perceptronLearningAlgorithm();
        printLineEquation(perceptron1.getWeights());

//        Perceptron perceptron2 = new Perceptron(augmentedX, y, NUMBER_OF_FEATURES);
//        perceptron2.perceptronLearningAlgorithm();
//        printLineEquation(perceptron2.getWeights());
//
//        Perceptron perceptron3 = new Perceptron(augmentedX, y, NUMBER_OF_FEATURES);
//        perceptron3.perceptronLearningAlgorithm();
//        printLineEquation(perceptron3.getWeights());
    }

//    public static int transfer(Perceptron perceptron1, Perceptron perceptron2) {
//
//    }

    private static double[][] initialiseX() {
        double[][] X = new double[ALL_POINTS.length][NUMBER_OF_FEATURES];
        for (int pointIndex = 0; pointIndex < ALL_POINTS.length; pointIndex++) {
            PointLab18 currentPoint = ALL_POINTS[pointIndex];
            for (int featureIndex = 0; featureIndex < NUMBER_OF_FEATURES; featureIndex++) {
                double feature = currentPoint.getFeatures()[featureIndex];
                X[pointIndex][featureIndex] = feature;
            }
        }
        return X;
    }

    private static int[] initialiseY() {
        int[] y = new int[ALL_POINTS.length];
        for (int pointIndex = 0; pointIndex < ALL_POINTS.length; pointIndex++) {
            double classification = ALL_POINTS[pointIndex].getClassification();
            y[pointIndex] = (int)classification;
        }
        return y;
    }

    private static void printLineEquation(double[] weights) {
        //weights
        double w0 = weights[0];
        double w1 = weights[1];
        double w2 = weights[2];
        System.out.println("WEIGHTS: " + Arrays.toString(weights));
        //gradient in latex m=-\frac{\left(\frac{w_{0}}{w_{2}}\right)}{\left(\frac{w_{0}}{w_{1}}\right)}
        double gradient = -( w0 / w2 ) / ( w0 / w1 );

        //y-intercept
        double yIntercept = -(w0) / (w2); //-w0/w2

        System.out.println("y = " + gradient + "x" + " + " + yIntercept);
    }
}
