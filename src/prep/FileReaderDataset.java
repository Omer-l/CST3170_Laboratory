package prep;

import tools.MyFileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderDataset extends MyFileReader{
	private final int numberOfInputs = 64;
	private final String delimiter = ",";

	public FileReaderDataset(String fileName) {
		super(fileName);
	}
	
	/**
	 * This function gets the Points that each line 'lineClass1.txt' contains
	 */
	public DataSet[] getData() {

		DataSet[] points = new DataSet[getNumberOfLines()];
		int pointIterator = 0;

		try {
			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				String[] bits = input.nextLine().split(delimiter);
				double[] inputsForPoint = new double[numberOfInputs];

				for(int inputNumber = 0; inputNumber < bits.length-1; inputNumber++) {
					inputsForPoint[inputNumber] = Integer.parseInt(bits[inputNumber]);
				}

				int classification = Integer.parseInt(bits[bits.length - 1]);
				
				points[pointIterator] = new DataSet(inputsForPoint, classification);
				pointIterator++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return points;
	}

}
