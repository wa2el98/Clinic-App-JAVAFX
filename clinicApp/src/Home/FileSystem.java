package Home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import connectivity.ConnectionClass;

public class FileSystem {

	static FileSystem _instance = null;

	/*
	 * 
	 * 
	 * 
	 * 
	 * All
	 * 
	 * 
	 * 
	 * 
	 */

	public static FileSystem getInstance() {
		if (_instance == null) {
			// System.out.println("new logger");
			_instance = new FileSystem();
		}
		return _instance;
	}

	public void insertInFile(String path, String encryptedText) throws IOException {

		List<Integer> prnt = new ArrayList<Integer>();
		File outputFile = new File(path);

		for (int i = 0; i < encryptedText.length(); i++) {
			prnt.add((int) encryptedText.charAt(i));
		}

		byte[] outputBytes = new byte[prnt.size()];
		int i = 0;
		for (Integer element : prnt)
			outputBytes[i++] = element.byteValue();

		FileOutputStream outputStream = new FileOutputStream(outputFile);
		outputStream.write(outputBytes);
		outputStream.close();
	}

	public static String extractFile(String path) throws IOException {

		String data = "";

		int asci;
		char ch;

		File inputFile = new File(path);

		FileInputStream inputStream = new FileInputStream(inputFile);
		byte[] inputBytes = new byte[(int) inputFile.length()];
		inputStream.read(inputBytes);
		inputStream.close();

		ArrayList<Integer> list = new ArrayList<Integer>(inputBytes.length);
		for (byte b : inputBytes)
			list.add(b & 0xFF);

		while (!list.isEmpty()) {
			asci = list.remove(0);
			ch = (char) asci;

			data += ch;
		}

		return data;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * Staff
	 * 
	 * 
	 * 
	 * 
	 */

	/*
	 * New Patient
	 */

	public void initFile(String patientID) throws IOException, ClassNotFoundException, SQLException {

		String firstfileName = ConnectionClass.getDirectory();

		String path = (firstfileName + "\\" + patientID);
		File f1 = new File(path);
		// Creating a folder using mkdir() method
		boolean bool = f1.mkdir();
		if (bool) {
			System.out.println("Folder is created successfully");
		} else {
			System.out.println("Error Found!");
		}
	}

	/*
	 * Search
	 */

	public ArrayList<String> extractAction(String patientID) throws IOException, ClassNotFoundException, SQLException {

		ArrayList<String> list = new ArrayList<String>();

		String firstfileName = ConnectionClass.getDirectory();

		String fileName = (firstfileName + "\\" + patientID);

		final File folder = new File(fileName);

		if (folder.exists()) {
			list = listFilesForFolder(folder);
			return list;
		} else
			return null;
	}

	public static ArrayList<String> listFilesForFolder(final File folder) throws IOException {

		ArrayList<String> list = new ArrayList<String>();
		String date;

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				list.add(fileEntry.getName());
				BasicFileAttributes attr = Files.readAttributes(fileEntry.toPath(), BasicFileAttributes.class);
				date = attr.creationTime().toString();
				list.add(date.substring(0, 10));
			}
		}

		return list;

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * MANAGER
	 * 
	 * 
	 * 
	 * 
	 */

	// get list of all the files from a directory
	public static List<File> listf(String directoryName) {
		File directory = new File(directoryName);

		List<File> resultList = new ArrayList<File>();

		// get all the files from a directory
		File[] fList = directory.listFiles();
		resultList.addAll(Arrays.asList(fList));
		for (File file : fList) {
			if (file.isFile()) {
				System.out.println(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				resultList.addAll(listf(file.getAbsolutePath()));
			}
		}
		return resultList;
	}

}
