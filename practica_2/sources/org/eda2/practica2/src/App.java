package org.eda2.practica2.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Map.Entry;

public class App {

	private static Grafo grafo;

	private static final String DATASET_PATH = System.getProperty("user.dir") + "\\" + "dataset\\";
	private static String filename;

	public static void main(String[] args) {
		System.out.println("Selecciona fichero de datos:\n" + "0: graphPrimKruskal.txt\n" + "1: graphEDAland.txt\n"
				+ "2: graphEDAlandLarge.txt\n" + "3: grafo aleatorio");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		switch (input) {
		case 0:
			filename = "graphPrimKruskal.txt";
			break;

		case 1:
			filename = "graphEDAland.txt";
			break;

		case 2:
			filename = "graphEDAlandLarge.txt";
			break;

		case 3:
			filename = "";
			break;

		default:
			System.out.println("No has elegido ninguna opcion valida");
			sc.close();
			System.exit(0);
			break;
		}
		
		if (filename.isEmpty()) {
			System.out.println("Selecciona el tamaño del grafo:");
			input = sc.nextInt();
			System.out.println("Selecciona la densidad (%): ");
			int densidad = sc.nextInt();
			grafo = new Grafo(input,densidad);
		} else {
			System.out.println(DATASET_PATH);
			grafo = new Grafo(DATASET_PATH + filename);
		}

		grafo.print();
		System.out.println("Selecciona algoritmo:\n" + "0: Prim\n" + "1: Prim con PQ\n" + "2: Kruskal");
		input = sc.nextInt();

		long startNano = System.nanoTime();
		HashMap<String, ArrayList<String>> resultado = null;
		switch (input) {
		case 0:
			resultado = Prim.mst(grafo);
			break;

		case 1:
			resultado = Prim.mstPQ(grafo);
			break;

		case 2:
			resultado = Kruskal.mst(grafo);
			break;

		default:
			System.out.println("No has elegido ninguna opcion valida");
			sc.close();
			System.exit(0);
			break;
		}
		long endNano = System.nanoTime();
		
		if (resultado == null) {
			System.out.println("No hay resultados.");
		} else {
			print(resultado);
			System.out.println("Tiempo de ejecución para algoritmo PrimPQ: " + (endNano - startNano) + " ns " + " o "
					+ TimeUnit.MILLISECONDS.convert((endNano - startNano), TimeUnit.NANOSECONDS) + " ms.");
			long cost = cost(resultado, grafo);
			System.out.println("\n\tEl coste es: " + cost);

			System.out.println("Mostrar visualizacion por pantalla:\n" + "0: No\n" + "1: Si\n");
			input = sc.nextInt();
			if (input == 1)
				Display.dibujarGrafo(grafo, resultado);
		}
		
		sc.close();
	}

	private static long cost(HashMap<String, ArrayList<String>> resultado, Grafo grafo) {
		long sum = 0;
		for (Entry<String, ArrayList<String>> entry : resultado.entrySet()) {
			String key = entry.getKey();
			ArrayList<String> values = entry.getValue();

			for (String value : values) {
				HashMap<String, Double> map = grafo.map.get(value);
				if (map != null) {
					for (Entry<String, Double> adjMapEntry : map.entrySet()) {
						if (adjMapEntry.getKey() == key) {
							sum += adjMapEntry.getValue();
						}
					}
				}
			}
		}
		return sum;
	}

	private static void print(HashMap<String, ArrayList<String>> resultado) {
		System.out.println("\nMST is : ");
		for (Entry<String, ArrayList<String>> entry : resultado.entrySet()) {
			for (String value : entry.getValue()) {
				System.out.println("\t" + entry.getKey() + " -- " + value);
			}
		}
	}
}
