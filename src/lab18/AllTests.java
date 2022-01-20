package lab18;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AllTests {
    //    FileReaderLab18 fileReader = new FileReaderLab18("SVM_LineClass1.txt");
//FileReaderLab18 fileReader = new FileReaderLab18("LineClassSVM_Tutorial.txt");
    FileReaderLab18 fileReader = new FileReaderLab18("lineClass1.txt");
    PointLab18[] allPoints = fileReader.getData();
//    PointLab18[] A = PointLab18.getClassifiedPoints(allPoints, 10);
//    PointLab18[] B = PointLab18.getClassifiedPoints(allPoints, 20);

    @Test
    public void getDotProduct() {
        double expected = 5;
//        double actual = Utils.getDotProduct(A[0].getFeatures(), B[0].getFeatures());

//        assertEquals(expected, actual, 1e-15); SVM_LineClass1.txt
    }

    @Test
    public void getHypothesis() {
        double y_intercept = -9;
        double[] gradient = {y_intercept, 0.4, 1.0}; //w0 = y-intercept
        double[] point = allPoints[6].getFeatures();
        point[0] = 1; // x_0 = 1;

        int expected = -1;
        double actual = Utils.getHypothesis(allPoints[6].getFeatures(), gradient);
        System.out.println(actual);
    }

    @Test
    public void getHypothesis2() {
        double[] gradient = {8.0, -5.414125490630429, 3.753651706274863}; //w0 = y-intercept
        double[] point = {1, 4, 1}; //weights * vector + yIntercept = 0, ideally this should be close zero

        int expected = -1;
        double actual = Utils.getHypothesis(point, gradient);
        System.out.println(actual);
    }

    //A functional margin is the margin of the closest points to the separating line.
    @Test
    public void getMaximumOfAllFunctionalMargins() {
//        double[] gradient = {8.0, -5.414125490630429, 3.753651706274863}; //w0 = y-intercept
        double[][] lineWeights = {
                {12.0, -6.414125490630429, 3.753651706274863}, //   y = 1.7087694843685508x + -3.1968869088040246
                {5.0, -4.41412549063043, 2.753651706274863}, //     y = 1.6030079187472313x + -1.815770668674723
                {13.0, -7.414125490630429, 4.753651706274863}, //   y = 1.559669481220872x + -2.7347396913492594
                {8.0, -5.41412549063043, 2.753651706274863}, //     y = 1.9661620524821757x + -2.9052330698795568
                {14.0, -9.414125490630429, 4.753651706274863}, //   y = 1.9803986645053735x + -2.9451042829915104
                {10.0, -7.414125490630429, 4.753651706274863}, //   y = 1.5596694812208722x + -2.1036459164225074
                {7.0, -2.41412549063043, 0.7536517062748631}, //    y = 3.203237610331872x + -9.288110066916031
                {5.0, -4.41412549063043, 2.753651706274863}, //     y = 1.6030079187472313x + -1.815770668674723
                {10.0, -8.414125490630429, 5.753651706274863}, //   y = 1.4623974338686656x + -1.738026649943743
                {8.0, -5.414125490630429, 2.753651706274863},};//   y = 1.9661620524821752x + -2.9052330698795568

        double[] maximumOfTheMinimumFunctionalMargin = {Double.MIN_VALUE, Double.MIN_VALUE};
        int bestHyperplaneIndex = -1;
        for(int lineIndex = 0; lineIndex < lineWeights.length; lineIndex++) {
            double[] gradient = lineWeights[lineIndex];
            double minimumFunctionalMarginClass1 = Double.MAX_VALUE;
            double minimumFunctionalMarginClassMinus1 = Double.MAX_VALUE;

            System.out.print("\nLINE: " + lineIndex + " MARGINS: ");
            //gets the point with the minimum margin
            for (int pointIndex = 0; pointIndex < allPoints.length; pointIndex++) {
                PointLab18 point = allPoints[pointIndex];
                double[] feature = point.getFeatures();
                double classification = point.getClassification();
                double functionalMargin = Utils.getFunctionalMargin(feature, gradient, classification);

                if(classification == 1 && functionalMargin < minimumFunctionalMarginClass1) {
                    minimumFunctionalMarginClass1 = functionalMargin;
                } else if(classification == -1 && functionalMargin < minimumFunctionalMarginClassMinus1) {
                    minimumFunctionalMarginClassMinus1 = functionalMargin;
                }
                System.out.print(functionalMargin + ": " + classification + ", ");
            }
            //Identifies the line that maximises the minimum of the functional margin
            if(minimumFunctionalMarginClass1 > maximumOfTheMinimumFunctionalMargin[0] || minimumFunctionalMarginClassMinus1 > maximumOfTheMinimumFunctionalMargin[1]) {
                maximumOfTheMinimumFunctionalMargin[0] = minimumFunctionalMarginClass1;
                maximumOfTheMinimumFunctionalMargin[1] = minimumFunctionalMarginClassMinus1;
                bestHyperplaneIndex = lineIndex;
            }
            System.out.print("MINIMUM: " + minimumFunctionalMarginClass1 + "... " + minimumFunctionalMarginClassMinus1);
        }
    }
}
