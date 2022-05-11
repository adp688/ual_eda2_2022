package org.eda2.practica3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Mochila {
	private static ArrayList<Tesoro> inventario = new ArrayList<Tesoro>();
	private static double pesoTotal;
	private static double valorTotal;
	private static int capacidad;

	public static ArrayList<Tesoro> getInventario() {
		return inventario;
	}

	public static double getPesoTotal() {
		return pesoTotal;
	}

	public static double getValorTotal() {
		return valorTotal;
	}

	public static int getCapacidad() {
		return capacidad;
	}

	public static void setCapacidad(int capacidad) {
		Mochila.capacidad = capacidad;
	}

	private static double max(double a, double b) {
		return (a > b) ? a : b;
	}

	public static void print() {
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\n Tesoros dentro de la mochila: ");
		for (Tesoro t : inventario) {
			System.out.println(t.toString());
		}
		System.out.println("\n La mochila actualmente pesa: " + df.format(pesoTotal));
		System.out.println(" La mochila actualmente vale: " + df.format(valorTotal));
	}

	public static double dpInteger() {
		long startNano;
		long endNano;
		if (Cueva.tesoros.isEmpty())
			return -1;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\nAlgoritmo DP: ");

		double[][] dp = new double[Cueva.getSize() + 1][capacidad + 1];

		double valor = 0;
		int peso = 0;
		Tesoro tesoroActual;
		// Comienzo del Algoritmo
		startNano = System.nanoTime();
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
		endNano = System.nanoTime();
		// Final algoritmo
		System.out.println("Tiempo de ejecución para algoritmo: " + (endNano - startNano) + " ns " + " o "
				+ TimeUnit.MILLISECONDS.convert((endNano - startNano), TimeUnit.NANOSECONDS) + " ms.");

		System.out.println("Resultado: " + df.format(dp[Cueva.getSize()][capacidad]));
		return añadirTesoros(dp);
	}

	public static double dpInfinitoInteger() {
		if (Cueva.tesoros.isEmpty())
			return -1;
		long startNano;
		long endNano;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\nAlgoritmo DP infinito: ");

		double[] dp = new double[capacidad + 1];

		double valor = 0;
		int peso = 0;
		Tesoro tesoroActual;
		// Comienzo del Algoritmo
		startNano = System.nanoTime();
		for (int i = 0; i <= capacidad; i++) {
			for (int j = 0; j < Cueva.getSize(); j++) {
				tesoroActual = Cueva.tesoros.get(j);
				valor = tesoroActual.getValor();
				peso = (int) tesoroActual.getPeso();

				if (peso <= i)
					dp[i] = max(valor + dp[i - peso], dp[i]);
			}
		}
		endNano = System.nanoTime();
		// Final algoritmo
		System.out.println("Tiempo de ejecución para algoritmo: " + (endNano - startNano) + " ns " + " o "
				+ TimeUnit.MILLISECONDS.convert((endNano - startNano), TimeUnit.NANOSECONDS) + " ms.");

		System.out.println("Resultado: " + df.format(dp[capacidad]));
		return añadirTesoros(dp);
	}

	public static double greedy() {
		long startNano;
		long endNano;
		if (Cueva.tesoros.isEmpty())
			return -1;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("\nAlgoritmo Greedy: ");

		startNano = System.nanoTime();
		pesoTotal = 0;
		valorTotal = 0;
		double[] cantidad = new double[Cueva.getSize()];
		Tesoro t;
		for (int i = 0; i < Cueva.getSize(); i++) {
			t = Cueva.tesoros.get(i);
			if (pesoTotal + t.getPeso() <= capacidad) {
				cantidad[i] = 0;
				inventario.add(t);
				valorTotal += t.getValor();
				pesoTotal += t.getPeso();
				if (pesoTotal == capacidad)
					break;
			} else {
				cantidad[i] = ((capacidad) - pesoTotal) / t.getPeso();
				inventario.add(t);
				valorTotal += t.getValor() * cantidad[i];
				pesoTotal += (t.getPeso()) * cantidad[i];
				break;
			}
		}
		endNano = System.nanoTime();
		// Final algoritmo
		System.out.println("Tiempo de ejecución para algoritmo: " + (endNano - startNano) + " ns " + " o "
				+ TimeUnit.MILLISECONDS.convert((endNano - startNano), TimeUnit.NANOSECONDS) + " ms.");

		System.out.println("Resultado: " + df.format(valorTotal));
		print();
		return valorTotal;
	}

	private static double añadirTesoros(double[][] matriz) {
		int n = Cueva.getSize();
		int c = capacidad;
		pesoTotal = 0;
		valorTotal = 0;

		inventario.clear();

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

	private static double añadirTesoros(double[] array) {
		int n = Cueva.getSize();
		int c = capacidad;
		pesoTotal = 0;
		valorTotal = 0;

		inventario.clear();

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
