package src;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Cueva {
	public static HashMap<Tesoro, Integer> espacios = new HashMap<Tesoro, Integer>();
	private static int size;

	public static void generarTesoros(int cantidad, int numTesoros, int pesoMax, double valorMax) {
		if (numTesoros > cantidad)
			throw new RuntimeException("ERROR: La cantidad es superior al numero de tesoros unicos especificada.");
		size = cantidad;
		espacios.clear();
		int[] n = getUnidadesAleatorias(cantidad, numTesoros);

		for (int i = 0; i < n.length; i++) {
			int pesoAleatorio = new Random().nextInt(pesoMax);
			double valorAleatorio = new Random().nextDouble() * valorMax;
			Tesoro tesoro = new Tesoro(pesoAleatorio, valorAleatorio);
			espacios.put(tesoro, n[i]);
		}
	}

	public static void generarTesoros(int cantidad, int numTesoros, double pesoMax, double valorMax) {
		if (numTesoros > cantidad)
			throw new RuntimeException("ERROR: La cantidad es superior al numero de tesoros unicos especificada.");

		size = cantidad;
		espacios.clear();
		int[] n = getUnidadesAleatorias(cantidad, numTesoros);

		for (int i = 0; i < n.length; i++) {
			double pesoAleatorio = new Random().nextDouble() * pesoMax;
			double valorAleatorio = new Random().nextDouble() * valorMax;
			Tesoro tesoro = new Tesoro(pesoAleatorio, valorAleatorio);
			espacios.put(tesoro, n[i]);
		}
	}

	private static int[] getUnidadesAleatorias(int cantidad, int numTesoros) {
		int[] n = new int[numTesoros];
		for (int i = 0; i < n.length; i++) {
			int maximo = (cantidad - (numTesoros - i));
			int unidades = new Random().nextInt(maximo + 1) + 1;
			cantidad -= unidades;
			if (i == n.length - 1 && cantidad > 0)
				unidades += cantidad;
			n[i] = unidades;
		}
		return n;
	}

	public static String print() {
		String str = "Cueva del tesoro:\n";
		for (int i = 0; i < 32; i++)
			str += "-";
		for (Entry<Tesoro, Integer> espacio : espacios.entrySet())
			str += "\n" + espacio.getKey().toString() + "\n\tNumero de tesoros de este tipo: "
					+ espacio.getValue().toString();
		str += "\n\n";
		for (int i = 0; i < 32; i++)
			str += "-";
		str += "\nNumero de tesoros unicos: " + espacios.size();
		str += "\nTotal de tesoros: " + size;
		return str;
	}
}
