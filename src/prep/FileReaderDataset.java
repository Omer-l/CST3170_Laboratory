package prep;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderDataset {
	private final int numberOfInputs = 64; //number of inputs per line.
	private final String delimiter = ","; //splits the line to get the inputs and the classification
	private final File file; //file to read

	public FileReaderDataset(String fileName) {
		this.file = new File(System.getProperty("user.dir") + "/Resources/" + fileName);
	}
	
	/**
	 * This function gets the Row's inputs and classification that for each line
	 */
	public Row[] getData() {

		Row[] points = new Row[getNumberOfLines()];
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
				
				points[pointIterator] = new Row(inputsForPoint, classification);
				pointIterator++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return points;
	}

	/**
	 * This function counts the number of lines there are in a file.
	 * @return number of lines in file.
	 */
	public int getNumberOfLines() {
		int trainingSetsCounter = 0; // counter

		try {
			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				input.nextLine();
				trainingSetsCounter++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return trainingSetsCounter;
	}

}
