package lab3;

import lab2.PointLab2;
import tools.MyFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileReaderLab3 extends MyFileReader {
    public FileReaderLab3(File file) {
        super(file);
    }

    public FileReaderLab3(String fileName) {
        super(fileName);
    }

    /**
     * This function gets the data that each line 'lineClass1.txt' contains
     */
    public int[][] getData() {

        int[][] lensesData = new int[getNumberOfLines()][getNumberOfColumns()]; //columns are the features.

        int rowIterator = 0;

        try {
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String[] bits = input.nextLine().split(" ");
                int columnIterator = 0;
                for(String bit : bits) {
                    bit = bit.trim(); //trim bit to get true length. is there a digit?
                    if (bit.length() > 0) {
                        lensesData[rowIterator][columnIterator] = Integer.parseInt(bit);
                        columnIterator++;
                    }
                }

                rowIterator++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return lensesData;
    }

    public int getNumberOfColumns() {
        int columns = 0;

        try {
            Scanner input = new Scanner(file);

            String[] bits = input.nextLine().split(" ");

            for(String bit : bits) {
                bit = bit.trim(); //trim bit to get true length. is there a digit?
                if (bit.length() > 0)
                    if (Character.isDigit(bit.charAt(0)))
                        columns++;
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return columns;
    }
}
