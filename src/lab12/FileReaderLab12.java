package lab12;

import tools.MyFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderLab12 extends MyFileReader{

	public FileReaderLab12(File file) {
		super(file);
	}

	public FileReaderLab12(String fileName) {
		super(fileName);
	}
	
	/**
	 * This function gets the Points that each line 'lineClass1.txt' contains
	 */
	public Row[] getData() {

		Row[] points = new Row[getNumberOfLines()];
		int pointIterator = 0;

		try {
			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				String[] bits = input.nextLine().split(",");
				double[] inputsForPoint = new double[64];

				for(int inputNumber = 0; inputNumber < bits.length-1; inputNumber++) {
					inputsForPoint[inputNumber] = Integer.parseInt(bits[inputNumber]);
				}

				int classification = Integer.parseInt(bits[bits.length - 1]);
				
				points[pointIterator] = new Row(inputsForPoint, classification);
				pointIterator++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return points;
	}

}
