package lab18;

import org.junit.Test;
import static org.junit.Assert.*;

public class AllTests {
    FileReaderLab18 fileReader = new FileReaderLab18("SVM_LineClass1.txt");
    PointLab18[] allPoints = fileReader.getData();
    PointLab18[] A = PointLab18.getClassifiedPoints(allPoints, 10);
    PointLab18[] B = PointLab18.getClassifiedPoints(allPoints, 20);

    @Test
    public void dotProduct() {
        double expected = 5;
        double actual = A[0].getDotProduct(B[0]);

        assertEquals(expected, actual, 1e-15);
    }
}
