package org.eda2.practica4;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

// TODO: Auto-generated Javadoc
/**
 * The Class Display.
 */
public class Display {

	/** The Constant nodestyle. */
	static final String nodestyle = "node {" + "	size: 10px;" + "	text-background-mode:plain;"
			+ "	text-alignment:under;" + "	text-style:bold;" + "}";

	/**
	 * Mostrar.
	 *
	 * @param adj the adj
	 * @param source the source
	 * @param result the result
	 */
	public static void mostrar(HashMap<Vertex, HashMap<Vertex, Double>> adj, Vertex source, ArrayList<Vertex> result) {
		System.setProperty("org.graphstream.ui", "javafx");
		Graph graph = new SingleGraph("EDAland");
		graph.setAttribute("ui.stylesheet", nodestyle);

		// Añadir nodos
		for (Vertex entry : adj.keySet()) {
			Node n = graph.addNode(entry.getName());
			n.setAttribute("ui.label", entry.getName());
			n.setAttribute("ui.style", "fill-color: rgb(0,0,0);");
		}

		// Añadir conexiones entre nodos
		for (Vertex key : adj.keySet()) {
			for (Entry<Vertex, Double> entry : adj.get(key).entrySet()) {
				String vertexID = entry.getKey().getName();
				
				String ID = key.getName() + "-" + vertexID;
				String invertedID = vertexID + "-" + key.getName();
				if (graph.getEdge(ID) == null && graph.getEdge(invertedID) == null)
					graph.addEdge(ID, key.getName(), vertexID);
			}
		}

		// Marcar camino optimo
		for (int i = 0; i < result.size(); i++) {
			if (i < result.size() - 1) {
				String ID = result.get(i).getName() + "-" + result.get(i + 1).getName();
				String invertedID = result.get(i + 1).getName() + "-" + result.get(i).getName();

				Edge e = graph.getEdge(ID);
				if (e == null)
					e = graph.getEdge(invertedID);

				e.setAttribute("ui.style", "fill-color: rgb(0,100,255);");
				e.getNode1().setAttribute("ui.style", "fill-color: rgb(0,100,255);");
				e.getNode0().setAttribute("ui.style", "fill-color: rgb(0,100,255);");
			}
		}

		graph.getNode(source.getName()).setAttribute("ui.style", "fill-color: rgb(255, 0, 0);");

		graph.display();
	}
}