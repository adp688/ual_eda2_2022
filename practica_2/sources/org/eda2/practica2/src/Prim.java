package org.eda2.practica2.src;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Prim {

	static void primMST(Grafo grafo) {

		TreeMap<String, String> MST = new TreeMap<String, String>();

		Set<String> set = grafo.adjMap.keySet();
		for (String i : set) {
			grafo.addHeap(i, Integer.MAX_VALUE);
		}

		while (grafo.heap.size() != 0) {
			String minEdgeVertex = grafo.minKey(); // get the vertex with minimum edge in the heap
			grafo.heap.remove(minEdgeVertex); // node removed from heap and its moves to the Set s.

			TreeMap<String, Integer> tree = grafo.adjMap.get(minEdgeVertex);
			for (Entry<String, Integer> entry : tree.entrySet()) {
				if ((grafo.heap.containsKey(entry.getKey())) && (entry.getValue() < Integer.MAX_VALUE)) {
					grafo.heap.put(entry.getKey(), entry.getValue()); // decreasing node value
					MST.put(entry.getKey(), minEdgeVertex);
				}
			}
		}
		printMST(MST);

		long cost = MSTCost(MST, grafo);
		System.out.println("\n\tCost is :" + cost);
	}

	private static long MSTCost(TreeMap<String, String> mST, Grafo grafo) {
		Set<Entry<String, String>> set = mST.entrySet();

		long sum = 0;
		for (Entry<String, String> entry : set) {

			String key = entry.getKey();
			String value = entry.getValue();

			TreeMap<String, Integer> tree = grafo.adjMap.get(value);
			if (tree != null) {
				for (Entry<String, Integer> adjMapEntry : tree.entrySet()) {
					if (adjMapEntry.getKey() == key) {
						sum += adjMapEntry.getValue();
					}
				}
			}
		}
		return sum;
	}

	private static void printMST(TreeMap<String, String> MST) {

		System.out.println("\nMST is : ");
		Set<Entry<String, String>> set = MST.entrySet();

		for (Entry<String, String> entry : set) {
			System.out.println("\t" + entry.getKey() + " -- " + entry.getValue());
		}
	}

}
