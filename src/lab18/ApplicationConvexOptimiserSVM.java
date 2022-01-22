package lab18;

import java.util.Arrays;

public class ApplicationConvexOptimiserSVM {

    private static FileReaderLab18 fileReader = new FileReaderLab18("LineClassSVM_Tutorial.txt"); //https://www.desmos.com/calculator/at8qwz5rsu
    private static PointLab18[] allPoints = fileReader.getData();
    /**
     * X, y = get_dataset(ls.get_training_examples)
     * m = X.shape[0]
     * # Gram matrix - The matrix of all possible inner products of X.
     * K = np.array([np.dot(X[i], X[j]) for j in range(m)
     * for i in range(m)]).reshape((m, m)) P = cvxopt.matrix(np.outer(y, y) * K)
     * q = cvxopt.matrix(-1 * np.ones(m))
     */

    public static void main(String[] args) {
        double[][] augmentedX = getAugmentedX(); //where x0 = 1, x1 = feature 1 etc..
        double[] y = getClassifications(); //the classifications
        int numberOfRows = augmentedX.length; // The number of rows.

        double[][] gramMatrix = MatrixUtils.getGramMatrix(augmentedX, y); //Gram matrix - The matrix of all possible inner products of X.
//for(int i = 0; i < gramMatrix.length; i++)
//        System.out.println(Arrays.toString(gramMatrix[i]));

        double[] q = MatrixUtils.ones(numberOfRows);
        MatrixUtils.multiplyVector(q, -1); //all -1

        //Equality constraints
        double[] A = getClassifications();
        double[] b = {0};

        //Inequality constraints
        double[]

        System.out.println(Arrays.toString(q));
    }

    //Gets all augmented features from allPoints
    public static double[][] getAugmentedX() {
        double[][] augmentedX = new double[allPoints.length][];
        for(int index = 0; index < allPoints.length; index++) {
            double[] augmentedFeatures = allPoints[index].getFeatures();
            augmentedX[index] = augmentedFeatures;
        }

        return augmentedX;
    }

    //Gets all classifications of allPoints
    public static double[] getClassifications() {
        double[] classifications = new double[allPoints.length];
        for(int index = 0; index < allPoints.length; index++) {
            double augmentedFeatures = allPoints[index].getClassification();
            classifications[index] = augmentedFeatures;
        }

        return classifications;
    }
}
