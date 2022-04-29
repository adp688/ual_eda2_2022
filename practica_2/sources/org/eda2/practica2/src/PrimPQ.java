package org.eda2.practica2.src;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class PrimPQ {

	private static class Edge implements Comparable<Edge> {

		public Edge(String origen, String destino, double weight) {
			super();
			this.origen = origen;
			this.destino = destino;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

		public String origen, destino;
		public double weight;
	}

	public static HashMap<String, ArrayList<String>> mst(Grafo grafo) {

		HashSet<String> remain = new HashSet<String>();
		for (String v : grafo.map.keySet())
			remain.add(v);
		remain.remove(grafo.getOrigen());

		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();

		HashMap<String, ArrayList<String>> resultado = new HashMap<String, ArrayList<String>>();
		String origen = grafo.getOrigen();
		String destino = "";
		while (!remain.isEmpty()) {
			for (Entry<String, Double> entry : grafo.map.get(origen).entrySet()) {
				destino = entry.getKey();
				if (remain.contains(destino)) {
					queue.add(new Edge(origen, destino, entry.getValue()));
				}
			}
			System.out.println("dest: " + destino);
			Edge edge = null;
			do {
				System.out.println("dest: " + destino);
				edge = queue.poll();
				destino = edge.destino;
			} while (!remain.contains(destino));
			origen = edge.origen;

			remain.remove(destino);

			if (!resultado.containsKey(origen)) {
				resultado.put(origen, new ArrayList<String>());
			}
			resultado.get(origen).add(destino);

			origen = destino;
		}

		return resultado;
	}
}
