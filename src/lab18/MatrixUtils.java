package lab18;

public class MatrixUtils {

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
     * @param gradient              AKA the weights, the 'm'
     * @param augmentedVector       The augmented X, including x0 = 1, Note, this will not be necessary for this calculation
     * @return                      The functional margin points, the higher, the better.
     */
    public static double getGeometricMargin(double[] gradient, double[] augmentedVector, double classification) {
        double b = gradient[0];
        double dotProductOfWeightsAndVector = getDotProductExcludeX0W0(augmentedVector, gradient);
        return (classification / getMagnitudeOrUnitNormalVector(gradient)) * (dotProductOfWeightsAndVector + b);
    }

    public static double getMagnitudeOrUnitNormalVector(double[] vector) {
        double sum = 0;
        for(int elementIndex = 1; elementIndex < vector.length; elementIndex++) { //starts from 1, excludes 'b'
            double element = vector[elementIndex];
            sum += (element * element);
        }

        return Math.sqrt(sum);
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

    /**
     * Calculates the dot product of two vectors/points excludes x_0 and w_0 where x_0 = 1 and w_0 is 'b' or yIntercept
     * @param augmentedVector1          is a vector/point
     * @param augmentedVector2          is a vector/point
     * @return                          dot product of the given vectors
     */
    public static double getDotProductExcludeX0W0(double[] augmentedVector1, double[] augmentedVector2) {
        double dotProduct = 0;

        for(int vectorNumber = 1; vectorNumber < augmentedVector1.length && vectorNumber < augmentedVector2.length; vectorNumber++) {
            double vectorPoint1 = augmentedVector1[vectorNumber];
            double vectorPoint2 = augmentedVector2[vectorNumber];

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

    //Gives equation for 2D weights for a 2D graphs
    public static void printLineEquation(double[] weights) {
        //weights
        double w0 = weights[0];
        double w1 = weights[1];
        double w2 = weights[2];
        //gradient in latex m=-\frac{\left(\frac{w_{0}}{w_{2}}\right)}{\left(\frac{w_{0}}{w_{1}}\right)}
        double gradient = - ( w0 / w2 ) / ( w0 / w1 );

        //y-intercept
        double yIntercept = (-w0) / (w2); //-w0/w2

        System.out.println("y = " + gradient + "x" + " + " + yIntercept);
    }

    //The matrix of all possible inner products of X.
    public static double[][] getGramMatrix(double[][] X) {
        int numberOfRows = X.length;
        double[][] result = new double[numberOfRows][numberOfRows];

        for(int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            for(int columnIndex = 1; columnIndex < numberOfRows; columnIndex++) {
                result[rowIndex][columnIndex] = MatrixUtils.getDotProduct(X[columnIndex-1], X[columnIndex]);
            }
        }
        return result;
    }
}
