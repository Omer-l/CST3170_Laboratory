package lab18;

import java.util.Arrays;

public class MatrixUtils {

    /**
     * Generates a 1D array of ones
     * @param numberOfOnes  the size of the array
     * @return              a single-dimensional array of ones
     */
    public static double[] ones(int numberOfOnes) {
        double[] arrayOfOnes = new double[numberOfOnes];

        for(int index = 0; index < numberOfOnes; index++)
            arrayOfOnes[index] = 1;

        return arrayOfOnes;
    }

    /**
     * Generates a 1D array of zeros
     * @param numberOfZeros     the size of the array
     * @return                  a single-dimensional array of zeros
     */
    public static double[] zeros(int numberOfZeros) {
        double[] arrayOfZeros = new double[numberOfZeros];

        for(int index = 0; index < numberOfZeros; index++)
            arrayOfZeros[index] = 0;

        return arrayOfZeros;
    }

    /**
     * Inserts a value into the main diagonal of the 2D array
     * @param vector    is the 2 dimensional array
     * @param value     is the value to insert into the main diagonal
     */
    public static void mainDiagonalInsertion(double[][] vector, double value) {
        for(int row = 0, column = 0; row < vector.length && column < vector[row].length; row++, column++)
            vector[row][column] = value;
    }


    /**
     * Calculates the hypothesis
     * @param augmentedVector           is the inputs, a augmentedVector on the graph where x0 = 1
     * @param gradient                  is the gradient, which is an augmentedVector, AKA weights for perceptron. w0 = yIntercept initially.
     * @return                          +1 if hypothesis is more than or equal to 0, otherwise returns -1
     */
    public static int getHypothesis(double[] augmentedVector, double[] gradient) {
        double dotProductOfWeightsAndVector = getDotProduct(augmentedVector, gradient);
        double hypothesisResult = dotProductOfWeightsAndVector; //can also use polynomial kernel here with a degree of 1.
        System.out.println(hypothesisResult);
        if(hypothesisResult >= 0)
            return 1;
        else
            return -1;
    }

    /**
     * Calculates the hypothesis with a zeta in the constraint for a soft margin.
     * @param augmentedVector           is the inputs, a augmentedVector on the graph where x0 = 1
     * @param gradient                  is the gradient, which is an augmentedVector, AKA weights for perceptron. w0 = yIntercept initially.
     * @param zeta                      is the for soft margin classification
     * @return                          +1 if hypothesis is more than or equal to 0, otherwise returns -1
     */
    public static int getHypothesisSoftMargin(double[] augmentedVector, double[] gradient, double classification, double zeta) {
        double dotProductOfWeightsAndVector = getGeometricMargin(gradient, augmentedVector, classification);
        double hypothesisResult = dotProductOfWeightsAndVector; //can also use polynomial kernel here with a degree of 1.
        System.out.println(hypothesisResult);
        if(hypothesisResult >= 0 - zeta)
            return 1;
        else
            return -1;
    }

    /**
     * The f. To determine how good the line of best fit of the classified data points is.
     * @param gradient              AKA the weights, the 'm'
     * @param augmentedVector       The augmented X, including x0 = 1, Note, this will not be necessary for this calculation
     * @param classification        the y, the output, the classification
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
    public static String getLineEquation(double[] weights) {
        //weights
        double w0 = weights[0];
        double w1 = weights[1];
        double w2 = weights[2];
        //gradient in latex m=-\frac{\left(\frac{w_{0}}{w_{2}}\right)}{\left(\frac{w_{0}}{w_{1}}\right)}
        double gradient = - ( w0 / w2 ) / ( w0 / w1 );

        //y-intercept
        double yIntercept = (-w0) / (w2); //-w0/w2

        return "y = " + gradient + "x" + " + " + yIntercept;
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

    /**
     * function that returns the result of a dot product performed in another space
     * @param vector1   is vector 1 to multiply
     * @param vector2   is vector 2 that will be multiplied with vector 1
     * @return
     */
    public static double polynomialKernel(double[] vector1, double[] vector2, int degree) {
        int numberOfIterations = vector1.length; //the number of iterations for the value inside the bracket
        double kernel = 0; //could be 1 to follow lecture vid on youtube.

        for(int index = 0; index < numberOfIterations; index++) {
            kernel += (vector1[index] * vector2[index]);
        }

        return Math.pow(kernel, degree);
    }

    /**
     * Transforms a classification array into -1 or 1, depending on the classification to keep as 1, if not classification, -1.
     * @param allClassifications    the array containing all the classifications
     * @param desiredClass          the desired classification to turn into 1 in the classification array.
     * @return                      an array containing 1s for desiredClassification, -1 for other classifications
     */
    public static double[] getBinaryClassifications(double[] allClassifications, double desiredClass) {
        int size = allClassifications.length;
        double[] binaryClassifications = new double[size];

        for(int index = 0; index < size; index++) {
            int classification = (int)allClassifications[index];

            if(classification == desiredClass)
                binaryClassifications[index] = 1;
            else
                binaryClassifications[index] = -1;
        }
        return binaryClassifications;
    }

    /**
     * Given an 2D array of different line weights, calculates the minimum geometric margin
     * of each line and returns the line with the highest minimum geometric margin
     * @param lineWeights       is a 2D array, where each index contains the weights for a line
     * @return                  the weights for a line which has the maximum of the minimum geometric margin (I'm going to go mad)
     */
    public static double[] maximiseTheMinimumGeometricMargin(double[][] allPoints, double[][] lineWeights, double[] classifications) {
        double maximumOfTheMinimumGeometricMargin = Double.MIN_VALUE;
        int bestHyperplaneIndex = 0;
        int numberOfLines = lineWeights.length;
        int numberOfPoints = allPoints.length;
        for(int lineIndex = 0; lineIndex < numberOfLines; lineIndex++) {
            double[] weights = lineWeights[lineIndex];
            int classification = (int) classifications[lineIndex];
            double minimumGeometricMarginClass1 = Double.MAX_VALUE;
            double minimumGeometricMarginClassMinus1 = Double.MAX_VALUE;
            //get geometric margin from points to line
            for (int pointIndex = 0; pointIndex < numberOfPoints; pointIndex++) {
                double[] currentPoint = allPoints[pointIndex];
                double geometricMargin = getGeometricMargin(weights, currentPoint, classification);

                if (classification == 1 && geometricMargin < minimumGeometricMarginClass1) {
                    minimumGeometricMarginClass1 = geometricMargin;
                } else if (classification == -1 && geometricMargin < minimumGeometricMarginClassMinus1) {
                    minimumGeometricMarginClassMinus1 = geometricMargin;
                }
            }
            double minimum = minimumGeometricMarginClass1 > minimumGeometricMarginClassMinus1 ? minimumGeometricMarginClassMinus1 : minimumGeometricMarginClass1;
            if (minimum > maximumOfTheMinimumGeometricMargin) {
                System.out.print("\nNEW HYPERPLANE AT: " + lineIndex + " BEING: " + Arrays.toString(lineWeights[lineIndex]) + " COMPARED TO PREVIOUSLY AT: " + bestHyperplaneIndex + " BEING: " + Arrays.toString(lineWeights[bestHyperplaneIndex]) + "\n");
                maximumOfTheMinimumGeometricMargin = minimum;
                bestHyperplaneIndex = lineIndex;
            }
        }

        return lineWeights[bestHyperplaneIndex];
    }
}
