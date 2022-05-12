/*
 * 
 */
package org.eda2.practica3;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class App.
 */
public class App {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("\n\t   Bienvenido \n\t\ta \n   La Cueva del Tesoro de Zrokk!\n");

		System.out.println("Selecciona una opcion:\n" + "1: Caso 1 - Algoritmo DP\n"
				+ "2: Caso 2 - Algoritmo DP Infinito\n" + "3: Caso 3 - Algoritmo DP Doubles\n"
				+ "4: Caso 4 - Algoritmo DP vs Greedy (Fraccionable)\n" + "5: Realizar experimento.");

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		if (input <= 0 || input > 5) {
			System.out.println("No has elegido ninguna opcion valida.");
			sc.close();
			System.exit(0);
		}

		if (input == 5) {
			System.out.println("Numero de iteraciones: ");
			int iteraciones = sc.nextInt();
			calcularResultadosExperimentales(iteraciones);
			sc.close();
		}

		System.out.println("Cantidad de tesoros a generar: ");
		int numTesoros = sc.nextInt();
		System.out.println("Peso maximo de los tesoros: ");
		double pesoMax = sc.nextDouble();
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
			Mochila.dpDouble();
			break;

		case 4:
//			Mochila.dpFractional();
//			Collections.sort(Cueva.tesoros);
			Mochila.greedy();
			break;

		default:
			System.out.println("No has elegido ninguna opcion valida.");
			sc.close();
			System.exit(0);
			break;
		}
		sc.close();
	}

	/**
	 * Calcular resultados experimentales.
	 *
	 * @param iteraciones the iteraciones
	 */
	private static void calcularResultadosExperimentales(int iteraciones) {
		long start;
		long end;
		String DP, DPInfinito, DPDouble, greedy;
		DP = DPInfinito = DPDouble = greedy = "";
		String tc = "";
		int t;
		int c;
		int pow = 4;
		for (int i = 0; i < iteraciones; i++) {
			t = (int) Math.pow(2, pow);
			c = (int) Math.pow(2, pow - 1);
			Cueva.generarTesoros(t * 2, 100, 100);
			Mochila.setCapacidad(c);
			tc = t + ";" + c;

			start = System.currentTimeMillis();
			Mochila.dpInteger();
			end = System.currentTimeMillis();
			DP += tc + ";" + ((end - start) <= 0 ? 1 : (end - start)) + "\n";

			start = System.currentTimeMillis();
			Mochila.dpInfinitoInteger();
			end = System.currentTimeMillis();
			DPInfinito += tc + ";" + ((end - start) <= 0 ? 1 : (end - start)) + "\n";

			start = System.currentTimeMillis();
			Mochila.dpDouble();
			end = System.currentTimeMillis();
			DPDouble += tc + ";" + ((end - start) <= 0 ? 1 : (end - start)) + "\n";

			start = System.currentTimeMillis();
			Mochila.greedy();
			end = System.currentTimeMillis();
			greedy += tc + ";" + ((end - start) <= 0 ? 1 : (end - start)) + "\n";

			pow++;
		}
		
		System.out.println("\n" + "Resultados DP Integer:");
		System.out.println(DP);
		System.out.println("\n" + "Resultados DP Infinito:");
		System.out.println(DPInfinito);
		System.out.println("\n" + "Resultados DP Double:");
		System.out.println(DPDouble);
		System.out.println("\n" + "Resultados greedy:");
		System.out.println(greedy);
		System.exit(0);
	}
}
