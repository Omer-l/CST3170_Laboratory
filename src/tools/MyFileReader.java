package tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class MyFileReader {
	protected File file;
	
	public MyFileReader(String fileName) {
		this.file = new File(System.getProperty("user.dir") + "/Resources/" + fileName);
	}
	
	public MyFileReader(File file) {
		this.file = file;
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
				String tmp = input.nextLine();
				trainingSetsCounter++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		return trainingSetsCounter;
	}
}
