package lab18;

public class Utils {

    /**
     * Calculates the hypothesis
     * @param augmentedVector           is the inputs, a augmentedVector on the graph where x0 = 1
     * @param gradient                  is the gradient, which is an augmentedVector, AKA weights for perceptron. w0 = yIntercept initially.
     * @return                          +1 if hypothesis is more than or equal to 0, otherwise returns -1
     */
    public static int getHypothesis(double[] augmentedVector, double[] gradient) {
        double dotProductOfWeightsAndVector = getDotProduct(augmentedVector, gradient);
        double hypothesisResult = dotProductOfWeightsAndVector;
        System.out.println(hypothesisResult);
        if(hypothesisResult >= 0)
            return 1;
        else
            return -1;
    }

    /**
     * The f. To determine how good the line of best fit of the classified data points is.
     * @param gradient  AKA the weights, the 'm'
     * @param augmentedVector       The augmented X, including x0 = 1
     * @param y                     The y-coordinate
     * @return                      The functional margin points, the higher the better.
     */
    public static double getFunctionalMargin(double[] gradient, double[] augmentedVector, double classification) {
        double dotProductOfWeightsAndVector = getDotProduct(augmentedVector, gradient);
        return classification * dotProductOfWeightsAndVector;
    }

    /**
     * Calculates the dot product of two vectors/points
     * @param vector1       is a vector/point
     * @param vector2       is a vector/point
     * @return              dot product of the given vectors
     */
    public static double getDotProduct(double[] vector1, double[] vector2) {
        double dotProduct = 0;

        for(int vectorNumber = 0; vectorNumber < vector1.length && vectorNumber < vector2.length; vectorNumber++) {
            double vectorPoint1 = vector1[vectorNumber];
            double vectorPoint2 = vector2[vectorNumber];

            dotProduct += (vectorPoint1 * vectorPoint2);
        }
        return dotProduct;
    }

    public static double[] add1DVectors(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];

        for(int vectorNumber = 0; vectorNumber < vector1.length && vectorNumber < vector2.length; vectorNumber++) {
            double vectorPoint1 = vector1[vectorNumber];
            double vectorPoint2 = vector2[vectorNumber];

            result[vectorNumber] = vectorPoint1 + vectorPoint2;
        }

        return result;
    }

    public static double[] subtract1DVectors(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];

        for(int vectorNumber = 0; vectorNumber < vector1.length && vectorNumber < vector2.length; vectorNumber++) {
            double vectorPoint1 = vector1[vectorNumber];
            double vectorPoint2 = vector2[vectorNumber];

            result[vectorNumber] = vectorPoint1 - vectorPoint2;
        }

        return result;
    }
}
