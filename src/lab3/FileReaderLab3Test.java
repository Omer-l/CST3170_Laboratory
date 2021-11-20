package lab3;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderLab3Test {

    @org.junit.jupiter.api.Test
    void getNumberOfColumns() {
        FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");

        int expectedNumberOfColumns = 5;
        int actualNumberOfColumns = fileReader.getNumberOfColumns();

        assertEquals(expectedNumberOfColumns, actualNumberOfColumns);
    }

    @org.junit.jupiter.api.Test
    void getData() {
        FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");

        int[] expected = {1, 1, 1, 1, 1, 3};
        int[] actual = fileReader.getData()[0];

        assertArrayEquals(expected, actual);
    }
}