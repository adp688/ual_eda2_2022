package org.eda2.practica4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class TSP {

	public HashMap<Vertex, ArrayList<Vertex>> adj = new HashMap<Vertex, ArrayList<Vertex>>();

	private boolean dirigido;
	private double shortestDistanceCircuit;
	private ArrayList<Vertex> shortestCircuit;

	Vertex source;

	public int minEdgeValue = 0;

	public void load(String filename) {
		adj.clear();
		int numV, numA;
		numV = numA = -1;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = "";
			line = br.readLine().trim();

			if (Integer.parseInt(line) != 0)
				dirigido = true;

			while (line != null) {
				line = br.readLine();
				if (line == null || line.length() <= 0)
					continue;

				if (numV < 0) {
					numV = Integer.parseInt(line);

					for (int i = 0; i < numV;) {
						line = br.readLine();
						if (line.length() <= 0)
							continue;

						Vertex v = new Vertex(line, -1);
						if (i == 0)
							this.source = v;
						adj.put(v, new ArrayList<Vertex>());
						i++;
					}
				} else if (numA < 0) {
					numA = Integer.parseInt(line);

					for (int i = 0; i < numA;) {
						line = br.readLine();
						if (line.length() <= 0)
							continue;

						String[] atributo = line.split(" ");

						adj.get(new Vertex(atributo[0], -1))
								.add(new Vertex(atributo[1], Double.parseDouble(atributo[2])));
						if (!dirigido)
							adj.get(new Vertex(atributo[1], -1))
									.add(new Vertex(atributo[0], Double.parseDouble(atributo[2])));
						i++;
					}
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public int numberOfVertices() {
		return adj.size();
	}

	public void print() {
		for (Entry<Vertex, ArrayList<Vertex>> entry : adj.entrySet()) {
			System.out.println(entry.getKey().getName() + ":");

			int c = 1;
			for (Vertex valueEntry : entry.getValue()) {
				System.out.println("\t" + c++ + ":");
				System.out
						.println("\t\tDestino: " + valueEntry.getName() + "\n\t\tDistancia: " + valueEntry.getWeight());
			}
			if (c == 1)
				System.out.println("\t" + "No entry");
		}

		System.out.println("\nEl vertice de origen es: " + source.getName());
	}

	// quitar abstract
	protected abstract class PathNode implements Comparable<PathNode> {
		private ArrayList<Vertex> res; // result
		private int visitedVertices; // The number of the visited vertices
		private double totalCost; // The total cost of the path taken so far
		private double estimatedCost; // Representing the lower bound of this state. * Priority

		PathNode(Vertex vertexToVisit) {
			res = new ArrayList<Vertex>();
			res.add(vertexToVisit);
			visitedVertices = 1;
			totalCost = 0.0;
			estimatedCost = numberOfVertices() * minEdgeValue;
		}

		PathNode(PathNode parentPathNode) {
			// Constructor copia
		}

//		@Override
//		public int compareTo(PathNode p) {
//			// El criterio de comparacion es 'estimatedCost' que se correponde con la
//			// prioridad
//		}
//
//		public ArrayList<Vertex> getRes() {
//			return res;
//		}
//
//		public void addVertexRes(Vertex v) {
//			this.res.add(v);
//		}
//
//		public Vertex lastVertexRes() {
//			// Devuelve el ultimo vertice que se ha anadido al camino (ultimo elemento de
//			// 'res')
//
//		}
//
//		public boolean isVertexVisited(Vertex v) {
//			// Se ha visitado el vertice v si esta actualmente en 'res'. Una sola linea
//
//		}

		public int getVisitedVertices() {
			return visitedVertices;
		}

		public void setVisitedVertices(int visitedVertices) {
			this.visitedVertices = visitedVertices;
		}

		public double getTotalCost() {
			return totalCost;
		}

		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}

		public double getEstimatedCost() {
			return estimatedCost;
		}

		public void setEstimatedCost(double estimatedCost) {
			this.estimatedCost = estimatedCost;
		}
	}

	// return the minimum value of the edges
	public double minimumEdgeValue() {
		double minimum = Double.MAX_VALUE;
		// Devuelve el menor valor de arista del grafo
		// Dos bucles 'for' anidados

		return minimum;
	}

	// TSP - BaB - Best-First
//	public ArrayList<Vertex> TSPBaB(Vertex source) {
//		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(source);
//		if (neighborMap == null)
//			return null;
//
//		minEdgeValue = minimumEdgeValue();
//
//		// Constructor de clase PathNode
//		PathNode firstNode = new PathNode(source);
//
//		PriorityQueue<PathNode> priorityQueue = new PriorityQueue<>();
//
//		priorityQueue.add(firstNode);
//
//		shortestCircuit = null;
//		double bestCost = Double.MAX_VALUE;
//
//		while(priorityQueue.size() > 0) {
//
//			// Y (PathNode) = menorElemento de la cola de prioridad en funcion de 'estimatedCost'
//			PathNode Y = priorityQueue.poll();
//
//			if (Y.getEstimatedCost() >= bestCost)
//                break;
//			else {
//				Vertex from = Y.lastVertexRes();
//				// Si el numero de vertices visitados es n
//				// y existe una arista que conecte 'from' con source
//				if ((Y.getVisitedVertices() == numberOfVertices()) &&
//					(containsEdge(from, source))) {
//					// Actualizar 'res' en Y añadiendo el vertice 'source'
//					// Actualizar 'totalCost' en Y con Y.totalCost + weight(from, source)
//
//					if (Y.getTotalCost() < bestCost) {
//						// Actualizar 'bestCost', 'shortestDistanceCircuit' y 'shortestCircuit'
//
//					}
//				}
//				else {
//					// Iterar para todos los vertices adyacentes a from,
//					// a cada vertice lo denominamos 'to'
//
//						if (vertice 'to' todavia no ha sido visitado en Y) { // hacer uso de la funcion 'isVertexVisited(vertex)' de PathNode
//
//
//							PathNode X = new PathNode(Y); // Uso de constructor copia
//							// Anadir 'to' a 'res' en X
//							// Incrementar en 1 los vertices visitados en X
//							// Actualizar 'totalCost' en X con Y.totalCost + weight(from, to)
//
//							// Actualizar 'estimatedCost' en X con X.totalCost + ((nVertices - X.getVisitedVertices() + 1) * minEdgeValue)
//
//
//							if (X.getEstimatedCost() < bestCost) {
//								priorityQueue.add(X);
//							}
//						}
//					}
//				}
//			}
//		}
//
//	return shortestCircuit;
//}
}
