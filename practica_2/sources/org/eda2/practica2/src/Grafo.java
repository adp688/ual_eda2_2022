package org.eda2.practica2.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Grafo {

	public HashMap<String, HashMap<String, Double>> map = new HashMap<String, HashMap<String, Double>>();

	private boolean dirigido = false;
	private String origen = "";
	private int numV;
	private int numA;
	private int size;

	public Grafo(String filename) {
		cargarGrafo(filename);
		this.size = this.map.size();
	}

	public Grafo(int numVertices, int numAristas) {
		generarGrafo(numVertices, numAristas);
		this.size = this.map.size();
	}

	private void generarGrafo(int numVertices, int numAristas) {

	}

	private void cargarGrafo(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = "";
			line = br.readLine().trim();

			if (Integer.parseInt(line) != 0)
				dirigido = true;

			while (line != null) {
				line = br.readLine();
				numV = Integer.parseInt(line);
				for (int i = 0; i < numV; i++) {
					line = br.readLine();
					if (i == 0)
						origen = line;
					map.put(line, new HashMap<String, Double>());
				}
				line = br.readLine();
				numA = Integer.parseInt(line);
				for (int i = 0; i < numA; i++) {
					line = br.readLine();
					String[] atributo = line.split(" ");

					map.get(atributo[0]).put(atributo[1], tryParseDouble(atributo[2]));
					if (!dirigido)
						map.get(atributo[1]).put(atributo[0], tryParseDouble(atributo[2]));
				}
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void print() {
		for (Entry<String, HashMap<String, Double>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":");

			int c = 1;
			for (Entry<String, Double> valueEntry : entry.getValue().entrySet()) {
				System.out.println("\t" + c++ + ":");
				System.out.println("\t\tDestino: " + valueEntry.getKey() + "\n\t\tDistancia: " + valueEntry.getValue());
			}
			if (c == 1)
				System.out.println("\t" + "No entry");
		}
	}

	static Double tryParseDouble(String text) {
		try {
			return Double.parseDouble(text);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Double getWeight(String v1, String v2) {
		return map.containsKey(v1) && map.get(v1).containsKey(v2) ? map.get(v1).get(v2) : null;
	}

	public int getSize() {
		return size;
	}

	public String getOrigen() {
		return origen;
	}

	public boolean isDirigido() {
		return dirigido;
	}

	public int getNumA() {
		return numA;
	}

	public int getNumV() {
		return numV;
	}

}
