package lab18;

public class Utils {

    /**
     * Calculates the hypothesis
     * @param gradient      is the gradient, which is a vector
     * @param y_intercept   is the y-intercept
     * @return              +1 if hypothesis is more than or equal to 0, otherwise returns -1
     */
    public static int getHypothesis(double[] vector, double[] gradient, double y_intercept) {
        double dotProduct = getDotProduct(vector, gradient);
        double hypothesisResult = dotProduct + y_intercept;

        if(hypothesisResult >= 0)
            return 1;
        else
            return -1;
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


}
