package org.eda2.practica2.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

public class Grafo {

	public TreeMap<String, TreeMap<String, Integer>> adjMap = new TreeMap<String, TreeMap<String, Integer>>();
	public TreeMap<String, Integer> heap = new TreeMap<String, Integer>();

	boolean dirigido = false;
	int size;

	String[] ruta;
	double distancia;
	double min_distancia;

	public Grafo(String filename) {
		cargarGrafo(filename);
		this.size = this.adjMap.size();

		this.distancia = 0.0;
		this.min_distancia = -1.0;
	}

	public void cargarGrafo(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = "";
			Integer number;
			line = br.readLine();

			number = tryParse(line);
			if (number != null && number.intValue() != 0)
				dirigido = true;

			while (line != null) {
				line = br.readLine();
				number = tryParse(line);
				if (number != null) {
					if (adjMap.isEmpty()) {
						for (int i = 0; i < number.intValue(); i++) {
							line = br.readLine();
							adjMap.put(line, new TreeMap<String, Integer>());
							// System.out.println(line);
						}
					} else {
						for (int i = 0; i < number.intValue(); i++) {
							line = br.readLine();
							String[] atributo = line.split(" ");

							adjMap.get(atributo[0]).put(atributo[1], tryParse(atributo[2]));
						}
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void addHeap(String ciudad, Integer distancia) {
		if (heap == null)
			heap = new TreeMap<String, Integer>();

		heap.put(ciudad, distancia);
	}

	public String minKey() {
		Set<Entry<String, Integer>> list = heap.entrySet();
		String minKey = heap.firstKey();
		int minValue = Integer.MAX_VALUE;
		if (list != null) {
			for (Entry<String, Integer> entry : list) {
				if (minValue > entry.getValue()) {
					minValue = entry.getValue();
					minKey = entry.getKey();
				}
			}
		}
		return minKey;
	}

	public void print() {
		for (Entry<String, TreeMap<String, Integer>> entry : adjMap.entrySet()) {
			System.out.println(entry.getKey() + ":");

			int c = 1;
			for (Entry<String, Integer> valueEntry : entry.getValue().entrySet()) {
				System.out.println("\t" + c++ + ":");
				System.out.println("\t\tDestino: " + valueEntry.getKey() + "\n\t\tDistancia: " + valueEntry.getValue());
			}
			if (c == 1)
				System.out.println("\t" + "No entry");
		}
	}

	public static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
