package org.eda2.practica1.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
	public static ArrayList<String> readBooksFromCSV(String fileName) {
		ArrayList<String> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				data.add(line);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return data;
	}
}
