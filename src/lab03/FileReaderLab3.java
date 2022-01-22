package lab03;

import tools.MyFileReader;

import java.io.File;
import java.io.FileNotFoundException;
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
     * @return  an array, for which, each row of the array is a training set.
     */
    public int[][] getData() {

        int[][] lensesData = new int[getNumberOfLines()][getNumberOfColumns() - 1]; //columns are the features. Also, -1 because one column is the index of the row.

        int rowIterator = 0;

        try {
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String[] bits = input.nextLine().split(" ");
                bits[0] = " "; //turns into a space to avoid parsing row number as a class of a feature.
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
