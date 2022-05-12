package org.eda2.practica2;

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
				+ "2: graphEDAlandLarge.txt\n" + "3: Grafo aleatorio.\n" + "4: Realizar experimento.");
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

		case 4:
			System.out.println("Nª de iteraciones a realizar: ");
			int iterations = sc.nextInt();
			System.out.println("Nº de aristas por vertice: ");
			int density = sc.nextInt();
			getExperimentalResults(iterations, density);
			break;

		default:
			System.out.println("No has elegido ninguna opcion valida.");
			sc.close();
			System.exit(0);
			break;
		}

		if (filename.isEmpty()) {
			System.out.println("Selecciona el tamaño del grafo:");
			input = sc.nextInt();
			System.out.println("Nº de aristas por vertice: ");
			int densidad = sc.nextInt();
			grafo = new Grafo(input, densidad);
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
			resultado = MST.prim(grafo);
			break;

		case 1:
			resultado = MST.primPQ(grafo);
			break;

		case 2:
			resultado = MST.kruskal(grafo);
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
			System.out.println("Tiempo de ejecución para algoritmo: " + (endNano - startNano) + " ns " + " o "
					+ TimeUnit.MILLISECONDS.convert((endNano - startNano), TimeUnit.NANOSECONDS) + " ms.");
			long cost = cost(resultado, grafo);
			System.out.println("\n\tNª Vertices: " + grafo.getNumV() + " Nª Aristas: " + grafo.getNumA());
			System.out.println("\tEl coste es: " + cost);
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
						if (adjMapEntry.getKey() != key) {
							sum += adjMapEntry.getValue();
						}
					}
				}
			}
		}
		return sum;
	}

	private static void print(HashMap<String, ArrayList<String>> resultado) {
		System.out.println("\nMST is: ");
		for (Entry<String, ArrayList<String>> entry : resultado.entrySet()) {
			for (String value : entry.getValue()) {
				System.out.println("\t" + entry.getKey() + " -- " + value);
			}
		}
	}

	private static void getExperimentalResults(int iteraciones, int densidad) {

		String primResults, primPQResults, kruskalResults;
		primResults = primPQResults = kruskalResults = "";
		int pow = 2;
		int numVertices;
		long start;
		long end;
		for (int i = 0; i < iteraciones; i++) {
			numVertices = (int) Math.pow(2, pow);
			Grafo grafo = new Grafo(numVertices, densidad);
			System.out.println(" Iteracion Nº " + (i + 1) + "\n Nº de vertices: " + grafo.getNumV()
					+ " | Nº de aristas: " + grafo.getNumA());
			System.out.println("...");
			// prim:
			start = System.currentTimeMillis();
			MST.prim(grafo);
			end = System.currentTimeMillis();

			primResults += "\n" + numVertices + ";" + ((end - start) <= 0 ? 1 : (end - start));

			// prim pq:
			start = System.currentTimeMillis();
			MST.primPQ(grafo);
			end = System.currentTimeMillis();
			primPQResults += "\n" + numVertices + ";" + ((end - start) <= 0 ? 1 + "<" : (end - start));

			// kruskal:
			start = System.currentTimeMillis();
			MST.kruskal(grafo);
			end = System.currentTimeMillis();
			kruskalResults += "\n" + numVertices + ";" + ((end - start) <= 0 ? 1 : (end - start));

			pow++;
		}

		System.out.println("\n" + "Resultados Prim:");
		System.out.println(primResults);
		System.out.println("\n" + "Resultados PrimPQ:");
		System.out.println(primPQResults);
		System.out.println("\n" + "Resultados Kruskal:");
		System.out.println(kruskalResults);
		System.exit(0);
	}

}
