package lab13;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void testInitialise4Queens() {
        Application application = new Application(4);
        System.out.println(arrayToString(application.getMatrix()));
    }

    @Test
    public void testInitialise8Queens() {
        Application application = new Application(8);
        System.out.println(arrayToString(application.getMatrix()));
    }

    @Test
    public void majorDiagonalValid() {
        Application application = new Application(4);
        char[][] testMatrx = {
                {' ', ' ', ' ', 'Q'},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '}
        };
        application.setMatrix(testMatrx);
        boolean expected = true;
        boolean actual = application.majorDiagonalValid(3, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void test4Queens() {
        Application application = new Application(4);
    }


    public static String arrayToString(char[][] matrix) {
        StringBuilder s = new StringBuilder();

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++)
                s.append("|" + matrix[row][column]);
            s.append("|\n");
        }

        return s.toString();
    }
}
