package prep;

import java.io.File; //Getting the File
import java.io.FileNotFoundException; //In case file is not found
import java.util.Scanner; //Reading the file

/**
 * Class for reading in the training and testing dataset files
 */
public class FileReaderDataset {
	private final int numberOfInputs = 64; //number of inputs per line.
	private final String delimiter = ","; //splits the line to get the inputs and the classification
	private final File file; //file to read
	private static final String FILE_NOT_FOUND_ERR = "FILE NOT FOUND";

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
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(FILE_NOT_FOUND_ERR);
			fileNotFoundException.printStackTrace();
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
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(FILE_NOT_FOUND_ERR);
			fileNotFoundException.printStackTrace();
		}

		return trainingSetsCounter;
	}

}
