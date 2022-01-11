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
	public PointLab12[] getData() {

		PointLab12[] points = new PointLab12[getNumberOfLines()];
		int pointIterator = 0;

		try {
			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				String[] bits = input.nextLine().split(",");
				int a = Integer.parseInt(bits[0].trim()); // adds point x
				int b = Integer.parseInt(bits[1].trim()); // add point y
				char classification = bits[2].trim().toCharArray()[0];
				
				points[pointIterator] = new PointLab12(a, b, classification);
				pointIterator++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return points;
	}

}
