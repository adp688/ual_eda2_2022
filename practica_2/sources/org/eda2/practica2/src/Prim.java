package org.eda2.practica2.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Prim {

	private static HashMap<String, String> heap = new HashMap<String, String>();
	private static HashMap<String, Double> weight = new HashMap<String, Double>();

	public static HashMap<String, ArrayList<String>> mst(Grafo grafo) {

		HashSet<String> remain = new HashSet<String>();
		for (String v : grafo.map.keySet())
			remain.add(v);
		remain.remove(grafo.getOrigen());

		heap.clear();
		weight.clear();
		for (String v : remain) {
			Double w = grafo.getWeight(grafo.getOrigen(), v);
			if (w != null) {
				heap.put(v, grafo.getOrigen());
				weight.put(v, w);
			} else {
				heap.put(v, null);
				weight.put(v, Double.MAX_VALUE);
			}
		}

		heap.put(grafo.getOrigen(), grafo.getOrigen());
		weight.put(grafo.getOrigen(), 0.0);

		HashMap<String, ArrayList<String>> resultado = new HashMap<String, ArrayList<String>>();
		while (!remain.isEmpty()) {
			// Obtenemos el valor minimo
			double minValue = Double.MAX_VALUE;
			String minKey = "";
			for (String v : remain) {
				double w = weight.get(v);
				if (w < minValue) {
					minValue = w;
					minKey = v;
				}
			}

			if (minKey.isEmpty())
				break;

			remain.remove(minKey);

			// Añadir resultado
			String origen = heap.get(minKey);
			if (!resultado.containsKey(origen)) {
				resultado.put(origen, new ArrayList<String>());
			}
			resultado.get(origen).add(minKey);

			for (String v : remain) {
				Double w = grafo.getWeight(minKey, v);
				if (w != null && w < weight.get(v)) {
					weight.put(v, w);
					heap.put(v, minKey);
				}
			}
		}
		return resultado;
	}
}
