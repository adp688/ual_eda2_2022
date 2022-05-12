package org.eda2.practica3;

import java.text.DecimalFormat;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Mochila.
 */
public class Mochila {

	/** The inventario. */
	private static ArrayList<Tesoro> inventario = new ArrayList<Tesoro>();

	/** The peso total. */
	private static double pesoTotal;

	/** The valor total. */
	private static double valorTotal;

	/** The capacidad. */
	private static int capacidad;

	/**
	 * Gets the inventario.
	 *
	 * @return the inventario
	 */
	public static ArrayList<Tesoro> getInventario() {
		return inventario;
	}

	/**
	 * Gets the peso total.
	 *
	 * @return the peso total
	 */
	public static double getPesoTotal() {
		return pesoTotal;
	}

	/**
	 * Gets the valor total.
	 *
	 * @return the valor total
	 */
	public static double getValorTotal() {
		return valorTotal;
	}

	/**
	 * Gets the capacidad.
	 *
	 * @return the capacidad
	 */
	public static int getCapacidad() {
		return capacidad;
	}

	/**
	 * Sets the capacidad.
	 *
	 * @param capacidad the new capacidad
	 */
	public static void setCapacidad(int capacidad) {
		Mochila.capacidad = capacidad;
	}

	/**
	 * Max.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the double
	 */
	private static double max(double a, double b) {
		return (a > b) ? a : b;
	}

	/**
	 * Prints the.
	 */
	public static void print() {
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\n Capacidad de la mochila: " + capacidad);
		System.out.println(" Tesoros dentro de la mochila: ");
		for (Tesoro t : inventario) {
			System.out.println(t.toString());
		}
		System.out.println("\n La mochila actualmente pesa: " + df.format(pesoTotal));
		System.out.println(" La mochila actualmente vale: " + df.format(valorTotal));
	}

	/**
	 * Dp integer.
	 *
	 * @return the double
	 */
	public static double dpInteger() {
		if (Cueva.tesoros.isEmpty())
			return -1;
		inventario.clear();
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\nAlgoritmo DP: ");

		double[][] dp = new double[Cueva.getSize() + 1][capacidad + 1];

		double valor = 0;
		int peso = 0;
		Tesoro tesoroActual;
		// Comienzo del Algoritmo
		for (int i = 0; i <= Cueva.getSize(); i++) {
			if (i > 0) {
				tesoroActual = Cueva.tesoros.get(i - 1);
				valor = tesoroActual.getValor();
				peso = (int) tesoroActual.getPeso();
			}

			for (int j = 0; j <= capacidad; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
					continue;
				}

				if (peso <= j)
					dp[i][j] = max(valor + dp[i - 1][j - peso], dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}

		System.out.println("Resultado: " + df.format(dp[Cueva.getSize()][capacidad]));
		return addTesoros(dp);
	}

	/**
	 * Dp infinito integer.
	 *
	 * @return the double
	 */
	public static double dpInfinitoInteger() {
		if (Cueva.tesoros.isEmpty())
			return -1;
		inventario.clear();
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\nAlgoritmo DP infinito: ");

		double[] dp = new double[capacidad + 1];

		double valor = 0;
		int peso = 0;
		Tesoro tesoroActual;
		// Comienzo del Algoritmo
		for (int i = 0; i <= capacidad; i++) {
			for (int j = 0; j < Cueva.getSize(); j++) {
				tesoroActual = Cueva.tesoros.get(j);
				valor = tesoroActual.getValor();
				peso = (int) tesoroActual.getPeso();

				if (peso <= i)
					dp[i] = max(valor + dp[i - peso], dp[i]);
			}
		}

		System.out.println("Resultado: " + df.format(dp[capacidad]));
		return addTesoros(dp);
	}

	/**
	 * Dp double.
	 *
	 * @return the double
	 */
	public static double dpDouble() {
		if (Cueva.tesoros.isEmpty())
			return -1;
		inventario.clear();
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\nAlgoritmo DP: ");

		double[][] dp = new double[Cueva.getSize() + 1][capacidad + 1];

		double valor = 0;
		int peso = 0;
		Tesoro tesoroActual;
		// Comienzo del Algoritmo
		for (int i = 0; i <= Cueva.getSize(); i++) {
			if (i > 0) {
				tesoroActual = Cueva.tesoros.get(i - 1);
				valor = tesoroActual.getValor();
				peso = (int) Math.round(tesoroActual.getPeso());
			}

			for (int j = 0; j <= capacidad; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
					continue;
				}

				if (peso <= j)
					dp[i][j] = max(valor + dp[i - 1][j - peso], dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}

		System.out.println("Resultado: " + df.format(dp[Cueva.getSize()][capacidad]));
		return addTesoros(dp);
	}

	/**
	 * Greedy.
	 *
	 * @return the double
	 */
	public static double greedy() {
		if (Cueva.tesoros.isEmpty())
			return -1;
		inventario.clear();
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\nAlgoritmo Greedy: ");

		pesoTotal = 0;
		valorTotal = 0;
		Tesoro t;
		for (int i = 0; i < Cueva.getSize(); i++) {
			t = Cueva.tesoros.get(i);
			if (pesoTotal + t.getPeso() <= capacidad) {
				t.setCantidad(1);
				inventario.add(t);
				valorTotal += t.getValor();
				pesoTotal += t.getPeso();
				if (pesoTotal == capacidad)
					break;
			} else {
				t.setCantidad((capacidad - pesoTotal) / t.getPeso());
				inventario.add(t);
				valorTotal += t.getValor() * t.getCantidad();
				pesoTotal += t.getPeso() * t.getCantidad();
				break;
			}
		}

		System.out.println("Resultado: " + df.format(valorTotal));
		print();
		return valorTotal;
	}

	/**
	 * Adds the tesoros.
	 *
	 * @param matriz the matriz
	 * @return the double
	 */
	private static double addTesoros(double[][] matriz) {
		int n = Cueva.getSize();
		int c = capacidad;
		pesoTotal = 0;
		valorTotal = 0;

		Tesoro t;
		while (n != 0) {
			t = Cueva.tesoros.get(n - 1);
			if (matriz[n][c] != matriz[n - 1][c]) {
				inventario.add(t);
				pesoTotal += t.getPeso();
				valorTotal += t.getValor();
				c -= (int) t.getPeso();
			}
			n--;
		}
		print();
		return valorTotal;
	}

	/**
	 * Adds the tesoros.
	 *
	 * @param array the array
	 * @return the double
	 */
	private static double addTesoros(double[] array) {
		int n = Cueva.getSize();
		int c = capacidad;
		pesoTotal = 0;
		valorTotal = 0;

		double pesoMinimo = Cueva.tesoros.get(0).getPeso();
		Tesoro t;
		double valorMaximo;
		int indexTesoro;
		while (c >= pesoMinimo) {
			valorMaximo = 0;
			indexTesoro = -1;
			for (int i = n - 1; i >= 0; i--) {
				t = Cueva.tesoros.get(i);
				if (c - t.getPeso() >= 0) {
					double nuevoValor = array[(int) (c - t.getPeso())] + t.getValor();
					if (nuevoValor > valorMaximo) {
						valorMaximo = nuevoValor;
						indexTesoro = i;
					}
				}
			}

			if (indexTesoro == -1)
				break;

			t = Cueva.tesoros.get(indexTesoro);
			inventario.add(t);
			valorTotal += t.getValor();
			pesoTotal += t.getPeso();
			System.out.print(" " + c);
			c -= (int) t.getPeso();
		}
		System.out.print(" BREAK");
		print();
		return valorTotal;
	}
}
