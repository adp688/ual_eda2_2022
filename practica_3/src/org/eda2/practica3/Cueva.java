package org.eda2.practica3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cueva {

	public static ArrayList<Tesoro> tesoros = new ArrayList<Tesoro>();
	private static int size;
	private static boolean exacto;

	public static void generarTesoros(int numTesoros, int pesoMax, double valorMax) {
		exacto = true;
		size = numTesoros;
		tesoros.clear();

		for (int i = 0; i < numTesoros; i++) {
			int pesoAleatorio = new Random().nextInt(pesoMax) + 1;
			double valorAleatorio = new Random().nextDouble() * valorMax + 1;
			Tesoro tesoro = new Tesoro(pesoAleatorio, valorAleatorio);
			tesoros.add(tesoro);
		}
		Collections.sort(tesoros);
	}

//	public static void generarTesoros(int cantidad, int numTesoros, double pesoMax, double valorMax) {
//		exacto = true;
//		size = numTesoros;
//		tesoros.clear();
//
//		for (int i = 0; i < numTesoros; i++) {
//			double pesoAleatorio = new Random().nextDouble() * pesoMax + 1;
//			double valorAleatorio = new Random().nextDouble() * valorMax + 1;
//			Tesoro tesoro = new Tesoro(pesoAleatorio, valorAleatorio);
//			tesoros.add(tesoro);
//		}
//		Collections.sort(tesoros);
//	}

	
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

	public static int getSize() {
		return size;
	}

	public static boolean isExacto() {
		return exacto;
	}
}
