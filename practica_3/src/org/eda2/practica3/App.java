package org.eda2.practica3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

	private static final String DATASET_PATH = System.getProperty("user.dir") + "\\" + "datasets\\";

	public static void main(String[] args) {
		System.out.println("\n\t   Bienvenido \n\t\ta \n   ¡La Cueva del Tesoro de Zrokk!\n");

		System.out.println(
				"Selecciona una opcion:\n" + "1: Caso 1 - Algoritmo DP\n" + "2: Caso 2 - Algoritmo DP Infinito\n"
						+ "3: Caso 3 - Algoritmo DP vs Greedy (Fraccionable)\n" + "5: Realizar experimento.");

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		if (input <= 0 || input > 5) {
			System.out.println("No has elegido ninguna opcion valida.");
			sc.close();
			System.exit(0);
		}

		System.out.println("Cantidad de tesoros a generar: ");
		int numTesoros = sc.nextInt();
		System.out.println("Peso maximo de los tesoros: ");
		int pesoMax = sc.nextInt();
		System.out.println("Valor maximo de los tesoros: ");
		double valorMax = sc.nextDouble();
		System.out.println("Capacidad de la mochila: ");
		int capacidad = sc.nextInt();

		Cueva.generarTesoros(numTesoros, pesoMax, valorMax);
		Cueva.print();
		Mochila.setCapacidad(capacidad);
		System.out.println("Capacidad de la mochila es \"" + Mochila.getCapacidad() + "\"");

		switch (input) {
		case 1:
			Mochila.dpInteger();
			break;

		case 2:
			Mochila.dpInfinitoInteger();
			break;

		case 3:
			Mochila.greedy();
			break;

		case 4:
			calcularResultadosExperimentales();
			break;

		default:
			System.out.println("No has elegido ninguna opcion valida.");
			sc.close();
			System.exit(0);
			break;
		}
		sc.close();
	}

	public void cargarArchivo(String filename) throws NumberFormatException, IOException {
		String line;
		BufferedReader br = null;
		br = getBufferedReader(filename + "_c.txt");
		Mochila.setCapacidad(Integer.parseInt(br.readLine().trim()));
		br.close();

		br = getBufferedReader(filename + "_w.txt");
		int i = 1;
		while ((line = br.readLine()) != null) {
			if (line.contains(".")) {
				Cueva.tesoros.add(new Tesoro("Tesoro " + i, Double.parseDouble(line.trim()), 0));
				i++;
			} else {
				Cueva.tesoros.add(new Tesoro("Tesoro " + i, Integer.parseInt(line.trim()), 0));
				i++;
			}
		}
		br.close();
		br = getBufferedReader(filename + "_p.txt");
		i = 0;
		while ((line = br.readLine()) != null) {
			Cueva.tesoros.get(i).setValor(Double.parseDouble(line.trim()));
			i++;
		}
		br.close();
	}

	private static BufferedReader getBufferedReader(String filename) throws FileNotFoundException {
		FileReader file = new FileReader(DATASET_PATH + filename);
		BufferedReader br = null;
		try {
			br = new BufferedReader(file);
		} catch (Exception excepcion) {
			System.out.println("No se pudo encontrar el archivo " + filename);
			System.exit(-1);
		}
		return br;
	}

	private static void calcularResultadosExperimentales() {
		
	}
}
