package org.eda2.practica2.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Kruskal {

	private static HashMap<String, String> heap = new HashMap<String, String>();

	public static HashMap<String, ArrayList<String>> mst(Grafo grafo) {

		HashMap<String, Double> remain = new HashMap<String, Double>();
		for (String v : grafo.map.keySet())
			remain.put(v, Double.MAX_VALUE);
		remain.remove(grafo.getOrigen());

		String minKey = grafo.getOrigen();
		String to, from;
		boolean firstLoop = true;
		HashMap<String, ArrayList<String>> resultado = new HashMap<String, ArrayList<String>>();
		while (!remain.isEmpty()) {
			// Obtenemos el valor minimo
			double minValue = Double.MAX_VALUE;
			if (firstLoop)
				firstLoop = false;
			else
				minKey = remain.keySet().stream().findFirst().toString();

			for (Entry<String, Double> entry : remain.entrySet()) {
				if (entry.getValue() < minValue) {
					minValue = entry.getValue();
					minKey = entry.getKey();
				}
			}

			remain.remove(minKey);

			for (Entry<String, Double> entry : grafo.map.get(minKey).entrySet()) {
				to = entry.getKey();
				Double weight = grafo.getWeight(heap.get(to), to);

				weight = (weight == null) ? Double.MAX_VALUE : weight;

				if (remain.containsKey(to) && entry.getValue() < Double.MAX_VALUE && entry.getValue() < weight) {
					remain.put(to, entry.getValue());
					heap.put(to, minKey);
				}
			}
		}
		// Añadir resultado
		for (Entry<String, String> entry : heap.entrySet()) {
			to = entry.getKey();
			from = entry.getValue();
			System.out.println(to + " , " + from);
			if (!resultado.containsKey(from))
				resultado.put(from, new ArrayList<String>());
			resultado.get(from).add(to);
		}
		return resultado;
	}
}