package lab18;

import org.junit.Test;

import java.util.Arrays;

public class AllTests {
    //    FileReaderLab18 fileReader = new FileReaderLab18("SVM_LineClass1.txt");
FileReaderLab18 fileReader = new FileReaderLab18("lineClass1.txt"); //https://www.desmos.com/calculator/at8qwz5rsu
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
                {14.0, 	-6.414125490630429, 	3.753651706274863,   }, //   	y = 1.708769484368551x + -3.7297013936046954
                {14.0, 	-9.414125490630429, 	4.753651706274863,   }, //   	y = 1.9803986645053735x + -2.9451042829915104
                {17.0, 	-9.414125490630429, 	3.753651706274863,   }, //   	y = 2.5079912115695566x + -4.528923120805701
                {5.0, 	-5.41412549063043, 	3.753651706274863, 	    }, //       y = 1.4423622419682156x + -1.3320362120016769
                {9.0, 	-6.414125490630429, 	4.753651706274863,   }, //   	y = 1.3493048895786215x + -1.8932813247802567
                {7.0, 	-2.41412549063043, 	0.7536517062748631,    }, //     	y = 3.203237610331872x + -9.288110066916031
                {11.0, 	-7.414125490630429, 	4.753651706274863,   },//       y = 1.559669481220872x + -2.314010508064758
                {11.0, 	-7.414125490630429, 	4.753651706274863,},    //      y = 1.7087694843685508x + -2.3976651816030183
                {9.0, 	-6.414125490630429, 	3.753651706274863,},    //      y = 1.9803986645053735x + -2.9451042829915104
                {14.0, 	-9.414125490630429, 	4.753651706274863,}     //      y = 1.7700340728631228x + -2.7347396913492594
          };

        MatrixUtils.getLineEquation(lineWeights[lineWeights.length-1]);
        double maximumOfTheMinimumFunctionalMargin = Double.MIN_VALUE;
        int bestHyperplaneIndex = 0;
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
                double functionalMargin = Math.abs(MatrixUtils.getGeometricMargin(gradient, feature, classificationa));

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
        }
        System.out.println("\n" + MatrixUtils.getLineEquation(lineWeights[bestHyperplaneIndex]) + "INDEX: " + bestHyperplaneIndex);
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
        MatrixUtils.getLineEquation(weights);
    }

    @Test
    public void linearKernel() {
        double[] vector1 = {3, 6};
        double[] vector2 = {10, 10};

        System.out.println("POLY KERNEL: " + MatrixUtils.polynomialKernel(vector1, vector2, 1));
        System.out.println("MY HYPOTHESIS:"+ MatrixUtils.getHypothesis(vector1, vector2));
    }

    @Test
    public void getBinaryClassifications() {
        double[][] allPoints = {{1, 6},{1, 7},{2, 5},{2, 8},
                {4, 2},{4, 3},{5, 1},{5, 2},{5, 3},{6, 1},{6, 2},{9, 4},{9, 7},{10, 5},{10, 6},{11, 6},{5, 9},{5, 10},{5, 11},{6, 9},{6, 10},{7, 10},{8, 11}};

        double[] classifications = {1, 1, 1, 1,
                2, 2, 2, 2, 2, 2, 2,
                3, 3, 3, 3, 3,
                4, 4, 4, 4, 4, 4, 4}; //for every point index, there is a classification.

        System.out.println(Arrays.toString(MatrixUtils.getBinaryClassifications(classifications, 2)));
    }
}
