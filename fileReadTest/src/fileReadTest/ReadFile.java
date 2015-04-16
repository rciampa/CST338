package fileReadTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		readData("");

		/*
		 * String[] strTemp = "100,Y. Byun,ybyun@csumb.edu,111-111-1111"
		 * .split("\\,"); for (String str : strTemp) { System.out.println(str);
		 * }
		 */

	}

	/**
	 * Used by the client application to read data from a file.
	 * 
	 * @param filePath
	 *            The file name and path to open for reading
	 */
	public static void readData(String filePath) throws IOException,
			FileNotFoundException {

		String line;
		filePath = "/home/rciampa/Documents/CST338/pj1/file";
		BufferedReader wordFileBuffer = null;

		try {
			wordFileBuffer = new BufferedReader(new FileReader(filePath));

			for (int s = 0; s < 3; s++) {
				
				line = wordFileBuffer.readLine();
				int loop = Integer.parseInt(line.trim());
				for (int i = 0; i < loop; i++) {
					line = wordFileBuffer.readLine();
					String[] strTemp = line.split("\\,");

					for (int r = 0; r < strTemp.length; r++) {
						System.out.println(strTemp[r]);
					}
				}
			}

		} catch (FileNotFoundException fnfEx) {// Extended exception
			System.err.println("There was an error accessing the file.");
			System.err.println(fnfEx.toString());
		} catch (IOException ioEx) {// Base exception
			System.err.println(ioEx.toString());
		} finally {
			if (wordFileBuffer != null) {
				wordFileBuffer.close();
			}
		}

	}

}
