package lab02;

public class ApplicationHardCodedClassifyingLine {
	public static void main(String[] args) {
		PointLab2 point1; //hardcoded point 1
		PointLab2 point2; //hardcoded point 2, these two points will help to create the classifying line
		Line classifyingLine; //line that separates the A's and B's in the data file.

		System.out.println("-------------------\nLINE CLASS 1\n-------------------");
		point1 = new PointLab2(3, 0);
		point2 = new PointLab2(4, 6);
		classifyingLine = new Line(point1, point2);
		readDataAndPrintPoints("lineClass1.txt");
		System.out.println(classifyingLine + "\n Connected these two hardcoded points: " + point1 + " and " + point2);

		System.out.println("\n-------------------\nLINE CLASS 2\n-------------------");
		point1 = new PointLab2(-20, -13);
		point2 = new PointLab2(45, 26);
		classifyingLine = new Line(point1, point2);
		readDataAndPrintPoints("lineClass2.txt");
		System.out.println(classifyingLine + "\n Connected these two hardcoded points: " + point1 + " and " + point2);
	}

	/**
	 * This function reads the data file, and prints the points.
	 * @param fileName	file to read
	 */
	public static void readDataAndPrintPoints(String fileName) {
		FileReaderLab2 fileReader = new FileReaderLab2(fileName); // must be a .txt file
		PointLab2[] points = fileReader.getData();
		System.out.println("POINTS");
		PointLab2.printPoints(points);
	}
}
