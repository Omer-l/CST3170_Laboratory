package lab18;

import org.junit.Test;
import static org.junit.Assert.*;

public class AllTests {
//    FileReaderLab18 fileReader = new FileReaderLab18("SVM_LineClass1.txt");
    FileReaderLab18 fileReader = new FileReaderLab18("LineClassSVM_Tutorial.txt");
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
        double[] point = allPoints[8].getFeatures();
        point[0] = 1; // x_0 = 1;

        int expected = -1;
        double actual = Utils.getHypothesis(allPoints[8].getFeatures(), gradient);
        System.out.println(actual);
    }
}
