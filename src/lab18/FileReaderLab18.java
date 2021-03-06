package lab18;

import tools.MyFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderLab18 extends MyFileReader{

	public FileReaderLab18(File file) {
		super(file);
	}

	public FileReaderLab18(String fileName) {
		super(fileName);
	}
	
	/**
	 * This function gets the Points that each line 'lineClass1.txt' contains
	 */
	public PointLab18[] getData() {

		PointLab18[] points = new PointLab18[getNumberOfLines()];
		int pointIterator = 0;

		try {
			Scanner fileInput = new Scanner(file);

			while (fileInput.hasNext()) {
				String[] bits = fileInput.nextLine().split(",");

				//assigns the features
				int numberOfFeatures = bits.length; //x0 = 1, x1 = x, x2 = y
				double[] features = new double[numberOfFeatures];
//				features[0] = 1; //augmented inputs, where x0 = 1, and w0 = y-intercept
				for(int  featureNumber = 0; featureNumber < bits.length - 1; featureNumber++) {
					double feature = Double.parseDouble(bits[featureNumber]);
					features[featureNumber] = feature;
				}

				//assigns the classification, the last column
				int classificationIndex = bits.length - 1;
				double classification = Double.parseDouble(bits[classificationIndex]);
				
				points[pointIterator] = new PointLab18(features, classification);
				pointIterator++;
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Couldn't read the file!");
			fileNotFoundException.printStackTrace();
		}

		return points;
	}

}
