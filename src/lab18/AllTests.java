package lab18;

import org.junit.Test;

import java.util.Arrays;

public class AllTests {

    @Test
    public void testGeometricMargin() {

        double[][] positive_x = {{1, 2,7},{1, 8,3},{1, 7,5},{1, 4,4},{1, 4,6},{1, 1,3},{1, 2,5},
         {1, 8,7},{1, 4,10},{1, 9,7},{1, 7,10},{1, 9,6},{1, 4,8},{1, 10,10}};
        double[] y = {1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1};
        double b = 8;
        double[] weights = {b, -0.4, -1};
        for(int i = 0; i < positive_x.length; i++) {
            double[] x = positive_x[i];
            double classification = y[i];
            double margin = MatrixUtils.getGeometricMargin(weights, x, classification);
            System.out.println(margin);
        }

    }
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
//        double[] vector = {1, 1, 1};
//        double classification = 1;
//        double[] grad = {5, 2, 1};
//        System.out.println("HERE: " + MatrixUtils.getGeometricMargin(grad, vector, classification));

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
                double classification = point.getClassification();
                double functionalMargin = Math.abs(MatrixUtils.getGeometricMargin(gradient, feature, classification));

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
        double[] vector1 = {1, 3, 6};
        double[] vector2 = {1, 5, 10, 10};

        System.out.println("POLY KERNEL: " + MatrixUtils.polynomialKernel(vector1, vector2, 2));
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

    @Test
    public void getClassification() {
        double[] augmentedX = {1,0,0,8,0,8,7,0,0,0,0,16,4,13,16,2,0,0,2,14,2,7,16,6,0,0,7,12,0,0,13,7,0,0,5,13,0,0,12,8,0,0,5,16,8,4,15,7,0,0,3,16,16,16,16,1,0,0,0,7,14,10,3,0,0,0};
        double[] weights = {3.0, 	0.22552646194179204, 	-0.6413271618458647, 	-6.1223264835972735, 	1.795312159961803, 	-57.35119685611399, 	-0.7099978210199218, 	-13.261477937537805, 	0.7216055204982815, 	0.8369363877962344, 	-13.773440478716148, 	23.04470042914685, 	21.75948057017773, 	20.123981936443215, 	24.140412281033036, 	2.998603044521129, 	-1.7512998964271986, 	0.8578377349083045, 	-10.444690742733663, 	5.555020536734549, 	-44.57908972217958, 	-27.379151808103998, 	17.144403201828005, 	16.539262676350056, 	-1.1154750686624242, 	0.6339420681032026, 	15.445199957412939, 	5.174171920738445, 	-83.19661016497587, 	-33.91567522534574, 	-5.15296177333849, 	4.901067912902178, 	0.401581567182944, 	0.6020973139117786, 	8.468317841967956, 	67.38064005234388, 	-84.9707650824668, 	-99.1222239542093, 	-61.46614132687011, 	37.34203106950048, 	0.6580817223746603, 	0.4605383802190176, 	-1.6128136027807116, 	75.51183199062592, 	-53.6595773070313, 	-70.89387857793969, 	-61.6717947677052, 	5.199434832678257, 	-1.138456148927196, 	0.6536524706597556, 	-14.946368029954112, 	24.241166172134257, 	15.54312699341287, 	31.30229101441131, 	5.595729531805809, 	-7.087148849079796, 	-7.08600631424307, 	0.3314143521941233, 	-0.6957073926447652, 	-9.891065663624946, 	3.7545608075634718, 	-25.237820134569503, 	-23.402498296718264, 	-8.44398994743323, 	0.8426749545009299, 	0.0};
        System.out.println(MatrixUtils.getHypothesis(augmentedX, weights));
    }

    @Test
    public void getHypothesisSoftMargin() {
        double y_intercept = -10;
        double[] gradient = {y_intercept, 0.4, 1.0}; //w0 = y-intercept
        double[] point = {1, 6, 8};
        int zeta = 2;
        double y = -1;
        double hardMargin = MatrixUtils.getHypothesisSoftMargin(gradient, point, -1, 0);
        double softMargin = MatrixUtils.getHypothesisSoftMargin(gradient, point, -1, 2);
        System.out.println("HARD: " + hardMargin);
        System.out.println("SOFT: " + softMargin);
    }

    @Test
    public void getMaximumGeometricMargin() {
//        double
    }
}
