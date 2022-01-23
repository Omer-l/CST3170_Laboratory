package lab18;

import org.junit.Test;

import java.util.Arrays;

public class AllTests {
    //    FileReaderLab18 fileReader = new FileReaderLab18("SVM_LineClass1.txt");
FileReaderLab18 fileReader = new FileReaderLab18("LineClassSVM_Tutorial.txt"); //https://www.desmos.com/calculator/at8qwz5rsu
//    FileReaderLab18 fileReader = new FileReaderLab18("lineClass2.txt");
    PointLab18[] allPoints = fileReader.getData();
//    PointLab18[] allPoints = {
//
//    };
//    PointLab18[] A = PointLab18.getClassifiedPoints(allPoints, 10);
//    PointLab18[] B = PointLab18.getClassifiedPoints(allPoints, 20);

    @Test
    public void getDotProduct() {
        double expected = 5;
//        double actual = MatrixUtils.getDotProduct(A[0].getFeatures(), B[0].getFeatures());

//        assertEquals(expected, actual, 1e-15); SVM_LineClass1.txt
    }

    @Test
    public void getHypothesis() {
        double y_intercept = -9;
        double[] gradient = {y_intercept, 0.4, 1.0}; //w0 = y-intercept
        double[] point = allPoints[6].getFeatures();
        point[0] = 1; // x_0 = 1;

        int expected = -1;
        double actual = MatrixUtils.getHypothesis(allPoints[6].getFeatures(), gradient);
        System.out.println(actual);
    }

    @Test
    public void getHypothesis2() {
        double[] gradient = {8.0, -5.414125490630429, 3.753651706274863}; //w0 = y-intercept
        double[] point = {1, 4, 1}; //weights * vector + yIntercept = 0, ideally this should be close zero

        int expected = -1;
        double actual = MatrixUtils.getHypothesis(point, gradient);
        System.out.println(actual);
    }

    //A functional margin is the margin of the closest points to the separating line.
    @Test
    public void getMaximumOfAllFunctionalMargins() {
        // * x = np.array([1,1])
        // * y = 1
        // * b_1 = 5
        // * w_1 = np.array([2,1])
        // * w_2 = w_1*10
        // * b_2 = b_1*10
        // * print(example_geometric_margin(w_1, b_1, x, y)) # 3.577708764
        double[] vector = {1, 1, 1};
        double classification = 1;
        double[] grad = {5, 2, 1};
        System.out.println("HERE: " + MatrixUtils.getGeometricMargin(grad, vector, classification));

//        double[] t = {7.0, -2.41412549063043, 0.7536517062748631};
//        double[] v1 = {1, 1, 1};
//        double[] v2 = {1, 4, 1};
//        double hyp1 = MatrixUtils.getHypothesis(v1, t);
//        double hyp2 = MatrixUtils.getHypothesis(v2, t);
//        double[] gradient = {8.0, -5.414125490630429, 3.753651706274863}; //w0 = y-intercept
        double[][] lineWeights = {
                {33.0, -1.414125490630429, -4.246348293725138}, //  y = -0.3330215500033507x + 7.771383249170672
                {50.0, -2.414125490630429, -6.246348293725138}, //  y = -0.3864858917738504x + 8.00467691662795
                {50.0, -1.4141254906304326, -6.246348293725138}, // y = -0.22639235344129197x + 8.00467691662795
                {71.0, -3.4141254906304326, -8.246348293725138}, // y = -0.4140166494335838x + 8.60987160268573
                {44.0, -1.414125490630429, -5.246348293725138}, //  y = -0.2695447216727462x + 8.386785919765549
                {37.0, -1.4141254906304308, -4.246348293725138}, // y = -0.33302155000335115x + 8.713369097554995
                {8, -0.4, -1}, //
          };

        MatrixUtils.printLineEquation(lineWeights[lineWeights.length-1]);
        double maximumOfTheMinimumFunctionalMargin = Double.MIN_VALUE;
        int bestHyperplaneIndex = -1;
        for (int lineIndex = 0; lineIndex < lineWeights.length; lineIndex++) {
            double[] gradient = lineWeights[lineIndex];
            double minimumFunctionalMarginClass1 = Double.MAX_VALUE;
            double minimumFunctionalMarginClassMinus1 = Double.MAX_VALUE;

            System.out.print("\nLINE: " + lineIndex + " MARGINS: ");
            //gets the point with the minimum margin
            for (int pointIndex = 0; pointIndex < allPoints.length; pointIndex++) {
                PointLab18 point = allPoints[pointIndex];
                double[] feature = point.getFeatures();
                double classificationa = point.getClassification();
                double functionalMargin = MatrixUtils.getGeometricMargin(gradient, feature, classificationa);

                if (classification == 1 && functionalMargin < minimumFunctionalMarginClass1) {
                    minimumFunctionalMarginClass1 = functionalMargin;
                } else if (classification == -1 && functionalMargin < minimumFunctionalMarginClassMinus1) {
                    minimumFunctionalMarginClassMinus1 = functionalMargin;
                }
            }
            double minimum = minimumFunctionalMarginClass1 > minimumFunctionalMarginClassMinus1 ? minimumFunctionalMarginClassMinus1 : minimumFunctionalMarginClass1;
            System.out.print("->>>>>>>>MINIMUM: " + (minimumFunctionalMarginClass1 > minimumFunctionalMarginClassMinus1 ? "CLASS -1: " + minimumFunctionalMarginClassMinus1 : "CLASS 1: " +  minimumFunctionalMarginClass1));
            //Identifies the line that maximises the minimum of the functional margin
            if (minimum > maximumOfTheMinimumFunctionalMargin) {
                maximumOfTheMinimumFunctionalMargin = minimum;
                bestHyperplaneIndex = lineIndex;
                System.out.print("\nNEW HYPERPLANE INDX: " + bestHyperplaneIndex);
            }
//            ApplicationPerceptron.printLineEquation(lineWeights[lineIndex]);
        }
    }

    @Test
    public void getGramMatrix() {

        double[][] augmentedX = new double[allPoints.length][allPoints[0].getFeatures().length];
        double[] y = new double[allPoints.length];
        for (int pointIndex = 0; pointIndex < allPoints.length; pointIndex++) {
            PointLab18 point = allPoints[pointIndex];
            augmentedX[pointIndex] = point.getFeatures();
        }

        for (int i = 0; i < allPoints.length; i++) {
            y[i] = allPoints[i].getClassification();
        }

        double[][] result = MatrixUtils.getGramMatrix(augmentedX, y);

        for (int rowIndex = 0; rowIndex < result.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < result.length; columnIndex++) {
                System.out.print(result[rowIndex][columnIndex] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testGetOuterProduct() {
        double[] y = new double[allPoints.length];

        for (int i = 0; i < allPoints.length; i++) {
            y[i] = allPoints[i].getClassification();
        }

        double[][] result = MatrixUtils.getOuterProduct(y, y);
        for(int rowIndex = 0; rowIndex < result.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < result.length; columnIndex++) {
                System.out.print(result[rowIndex][columnIndex] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void ones() {
        double[] ones = MatrixUtils.ones(5);
        MatrixUtils.multiplyVector(ones, -1);
        System.out.println(Arrays.toString(ones));
    }

    @Test
    public void mainDiagonalInsertion() {
        double[][] zeros2D = new double[14][];

        for(int i = 0; i < 14; i++)
            zeros2D[i] = MatrixUtils.zeros(14);

        MatrixUtils.mainDiagonalInsertion(zeros2D, -1);
        for(double[] zeros : zeros2D)
            System.out.println(Arrays.toString(zeros));
    }

    @Test
    public void quadraticProgramming() {
        double[] weights = {-9.666666925153795, 0.44444453, 1.11111128};
        MatrixUtils.printLineEquation(weights);
    }

    @Test
    public void linearKernel() {
        double[] vector1 = {3, 6};
        double[] vector2 = {10, 10};

        System.out.println(MatrixUtils.polynomialKernel(vector1, vector2, 2));
    }
}
