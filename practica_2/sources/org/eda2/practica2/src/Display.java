package org.eda2.practica2.src;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Display {

	static final String nodestyle = "node {" + "	size: 5px;" + "	text-background-mode:plain;"
			+ "	text-alignment:under;" + "	text-style:bold;" + "}";

	public static void dibujarGrafo(Grafo grafo, HashMap<String, ArrayList<String>> results) {
		System.setProperty("org.graphstream.ui", "javafx");
		Graph graph = new SingleGraph("EDAland");
		graph.setAttribute("ui.stylesheet", nodestyle);

		Set<String> set = grafo.map.keySet();
		for (String i : set) {
			Node n = graph.addNode(i);
			n.setAttribute("ui.label", i);
			if (i.contentEquals(grafo.getOrigen())) {
				n.setAttribute("ui.style", "fill-color: rgb(0,100,255);");
			} else {
				n.setAttribute("ui.style", "fill-color: rgb(0,0,0);");
			}
		}

		for (String entry : results.keySet()) {
			for (String value : results.get(entry)) {
				graph.addEdge(entry + "-" + value, entry, value);
			}
		}
		graph.display();
	}
}
