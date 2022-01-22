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

    /**
     * Multiplies all numbers in a 1D vector by a
     * @param vector    vector to find product of
     * @param a         constant to multiply all elements in vector by
     */
    public static void multiplyVector(double[] vector, double a) {
        for(int index = 0; index < vector.length; index++) {
            double element = vector[index];
            double product = element * a;
            vector[index] = product;
        }
    }

    /**
     * Multiplies all numbers in a 1D vector by a
     * @param vector1         is the vector to find product of
     * @param vector2         is the vector to multiply vector1 with.
     * @return                a 1D array of the products
     */
    public static double[] multiplyVector(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];

        for(int index = 0; index < vector1.length; index++) {
                double elementVector1 = vector1[index];
                double elementVector2 = vector2[index];
                double product = elementVector1 * elementVector2;
                result[index] = product;
        }
        return result;
    }

    /**
     * Computes the outer product of two vectors
     * @param vector1   is 1D vector 1
     * @param vector2   is 1D vector 2
     * @return          a 2D array containing the outer product
     */
    public static double[][] getOuterProduct(double[] vector1, double[] vector2) {
        double[][] result = new double[vector1.length][vector2.length];

        for(int rowNumber = 0; rowNumber < vector1.length; rowNumber++) {
            double rowElement = vector1[rowNumber];
            for(int columnNumber = 0; columnNumber < vector2.length; columnNumber++) {
                double outerElement = vector2[columnNumber];
                double outerProduct = rowElement * outerElement;
                result[rowNumber][columnNumber] = outerProduct;
            }
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

    //The matrix of all possible inner products of augmentedX. And then multiplies each row by the classification.
    public static double[][] getGramMatrix(double[][] augmentedX, double[] y) {

        double[][] X = new double[augmentedX.length][augmentedX[0].length-1];
        int numberOfRows = augmentedX.length;
        double[][] result = new double[numberOfRows][numberOfRows];
        double[][] outerProductOfYY = getOuterProduct(y, y);

        //turns augmented X into just X, so x0 = 1 is removed.
        int iteratorIndex = 0;
        for(double[] x : augmentedX) {
            double[] pointFeatures = new double[augmentedX[0].length - 1];
            for (int inputIndex = 1; inputIndex < X[0].length + 1; inputIndex++)
                pointFeatures[inputIndex - 1] = x[inputIndex];
            X[iteratorIndex++] = pointFeatures;
        }

        for(int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            double[] point = X[rowIndex];
            double[] gramMatrixRow = new double[numberOfRows];
            double[] outerProductYYRow = outerProductOfYY[rowIndex];
            double classification = y[rowIndex];
            for(int rowIndexIterator = 0, columnIndex = 0; rowIndexIterator < numberOfRows; rowIndexIterator++, columnIndex++) {
                double[] nextPoint = X[rowIndexIterator];
                double gram = MatrixUtils.getDotProduct(point, nextPoint);
                gramMatrixRow[columnIndex] = gram;
            }
            gramMatrixRow = multiplyVector(outerProductYYRow, gramMatrixRow);
            result[rowIndex] = gramMatrixRow;
        }
        return result;
    }
}
