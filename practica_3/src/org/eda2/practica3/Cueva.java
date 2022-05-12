/*
 * 
 */
package org.eda2.practica3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Cueva.
 */
public class Cueva {

	/** The Constant DATASET_PATH. */
	private static final String DATASET_PATH = System.getProperty("user.dir") + "\\" + "datasets\\";

	/** The tesoros. */
	public static ArrayList<Tesoro> tesoros = new ArrayList<Tesoro>();
	
	/** The size. */
	private static int size;

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public static int getSize() {
		return tesoros.size();
	}

	/**
	 * Prints the.
	 */
	public static void print() {
		String str = "Cueva del tesoro:\n";
		for (int i = 0; i < 32; i++)
			str += "-";
		for (Tesoro tesoro : tesoros)
			str += "\n" + tesoro.toString();
		str += "\n\n";
		for (int i = 0; i < 32; i++)
			str += "-";
		str += "\nTotal de tesoros: " + size;
		System.out.println(str);
	}

//	public static void generarTesoros(int numTesoros, int pesoMax, double valorMax) {
//		size = numTesoros;
//		tesoros.clear();
//
//		for (int i = 0; i < numTesoros; i++) {
//			int pesoAleatorio = new Random().nextInt(pesoMax) + 1;
//			double valorAleatorio = new Random().nextDouble() * valorMax + 1;
//			Tesoro tesoro = new Tesoro(pesoAleatorio, valorAleatorio);
//			tesoros.add(tesoro);
//		}
//		Collections.sort(tesoros);
//	}

	/**
 * Generar tesoros.
 *
 * @param numTesoros the num tesoros
 * @param pesoMax the peso max
 * @param valorMax the valor max
 */
public static void generarTesoros(int numTesoros, double pesoMax, double valorMax) {
		size = numTesoros;
		tesoros.clear();

		for (int i = 0; i < numTesoros; i++) {
			double pesoAleatorio = new Random().nextDouble() * pesoMax + 1;
			double valorAleatorio = new Random().nextDouble() * valorMax + 1;
			Tesoro tesoro = new Tesoro(pesoAleatorio, valorAleatorio);
			tesoros.add(tesoro);
		}
		Collections.sort(tesoros);
	}

	/**
	 * Cargar archivo.
	 *
	 * @param filename the filename
	 */
	public static void cargarArchivo(String filename) {
		tesoros.clear();
		String line = "";
		BufferedReader br = null;
		FileReader file = null;
		try {
			file = new FileReader(DATASET_PATH + filename + "_c.txt");
			br = new BufferedReader(file);

			Mochila.setCapacidad(Integer.parseInt(br.readLine().trim()));
			br.close();

		} catch (Exception excepcion) {
			System.out.println("No se pudo encontrar el archivo " + filename);
			System.exit(-1);
		}

		try {
			file = new FileReader(DATASET_PATH + filename + "_w.txt");
			br = new BufferedReader(file);

			int i = 1;
			while ((line = br.readLine()) != null) {
				if (line.contains(".")) {
					Cueva.tesoros.add(new Tesoro("Tesoro_" + i, Double.parseDouble(line.trim()), 0));
					i++;
				} else {
					Cueva.tesoros.add(new Tesoro("Tesoro_" + i, Integer.parseInt(line.trim()), 0));
					i++;
				}
			}
			br.close();
		} catch (Exception excepcion) {
			System.out.println("No se pudo encontrar el archivo " + filename);
			System.exit(-1);
		}

		try {
			file = new FileReader(DATASET_PATH + filename + "_p.txt");
			br = new BufferedReader(file);
			int i = 0;
			while ((line = br.readLine()) != null) {
				Cueva.tesoros.get(i).setValor(Double.parseDouble(line.trim()));
				i++;
			}

			br.close();
		} catch (Exception excepcion) {
			System.out.println("No se pudo encontrar el archivo " + filename);
			System.exit(-1);
		}
		Collections.sort(tesoros);
	}
}
