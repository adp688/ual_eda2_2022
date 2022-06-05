package org.eda2.practica4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class TSP.
 */
public class TSP {
	
	/** The adj. */
	// mapa de adyacencias
	public HashMap<Vertex, HashMap<Vertex, Double>> adj = new HashMap<Vertex, HashMap<Vertex, Double>>();

	/** The source. */
	public Vertex source;
	
	/** The dirigido. */
	private boolean dirigido;

	/** The solutions. */
	// lista de soluciones alcanzadas
	private ArrayList<ArrayList<Vertex>> solutions = new ArrayList<ArrayList<Vertex>>();
	
	/** The shortest distance circuit. */
	// solucion optima
	private double shortestDistanceCircuit = Double.MAX_VALUE;
	
	/** The shortest circuit. */
	public ArrayList<Vertex> shortestCircuit;

	/** The min edge value. */
	public double minEdgeValue = 0;

	/**
	 * Number of vertices.
	 *
	 * @return the int
	 */
	public int numberOfVertices() {
		return adj.size();
	}

	/**
	 * Load.
	 *
	 * @param filename the filename
	 */
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

						Vertex v = new Vertex(line);
						if (i == 0)
							this.source = v;
						adj.put(v, new HashMap<Vertex, Double>());
						i++;
					}
				} else if (numA < 0) {
					numA = Integer.parseInt(line);

					for (int i = 0; i < numA;) {
						line = br.readLine();
						if (line.length() <= 0)
							continue;

						String[] atributo = line.split(" ");

						Vertex v1 = new Vertex(atributo[0]);
						Vertex v2 = new Vertex(atributo[1]);

						adj.get(v1).put(v2, Double.parseDouble(atributo[2]));
						if (!dirigido)
							adj.get(v2).put(v1, Double.parseDouble(atributo[2]));
						i++;
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Prints the.
	 */
	public void print() {
		for (Vertex key : adj.keySet()) {
			System.out.println(key.getName() + ":");

			int c = 1;
			for (Entry<Vertex, Double> entry : adj.get(key).entrySet()) {
				System.out.println("\t" + c++ + ":");
				System.out.println("\t\tDestino: " + entry.getKey().getName() + "\n\t\tDistancia: " + entry.getValue());
			}
			if (c == 1)
				System.out.println("\t" + "No entry");
		}

		System.out.println("\nEl vertice de origen es: " + source.getName());
	}

	/**
	 * Solution to string.
	 *
	 * @param list the list
	 * @return the string
	 */
	public String solutionToString(ArrayList<Vertex> list) {
		String str = "";
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			str += list.get(i).getName();
			if (i != list.size() - 1)
				str += " - ";
		}
		str += " - " + source.getName();
		return str;
	}

	/**
	 * Sets the shortest circuit.
	 *
	 * @param results the new shortest circuit
	 */
	public void setShortestCircuit(ArrayList<Vertex> results) {

		solutions.add(results);

		System.out.print("\n\nSolucion Nº " + solutions.size() + ": ");
		System.out.print(solutionToString(results));

		double dist = 0;
		Vertex current = results.get(0);

		for (Vertex vertex : results) {
			if (!current.equals(vertex))
				dist += adj.get(current).get(vertex);
			current = vertex;
		}

		dist += weight(current, source);

		System.out.print(" ==> " + dist);
		if (dist < shortestDistanceCircuit) {
			shortestDistanceCircuit = dist;
			shortestCircuit = new ArrayList<Vertex>(results);
		}
	}

	/**
	 * TSP backtracking.
	 */
	public void TSPBacktracking() {

		if (adj.get(source) == null)
			return;

		solutions.clear();
		ArrayList<Vertex> aux;
		int lvl;

		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		for (Vertex vertex : adj.keySet())
			visited.put(vertex.getName(), false);

		visited.put(source.getName(), true);

		// Inicio
		for (Vertex vertex : adj.get(source).keySet()) {
			aux = new ArrayList<Vertex>();
			aux.add(source);
			visited.put(source.getName(), true);

			if (!visited.get(vertex.getName())) {
				lvl = 2;
				aux.add(vertex);
				visited.put(vertex.getName(), true);

				Backtracking(aux, lvl, source, visited, vertex);

				visited.put(vertex.getName(), false);
				aux.remove(vertex);
			}
		}

		System.out.println("\n\nShortest circuit:");
		System.out.print(solutionToString(shortestCircuit));
		System.out.print(" ==> " + shortestDistanceCircuit);
	}

	/**
	 * Backtracking.
	 *
	 * @param aux the aux
	 * @param lvl the lvl
	 * @param source the source
	 * @param visited the visited
	 * @param next the next
	 */
	private void Backtracking(ArrayList<Vertex> aux, int lvl, Vertex source, HashMap<String, Boolean> visited,
			Vertex next) {

		if (lvl + 1 == adj.size() + 1) {
			if (adj.get(next).containsKey(source))
				setShortestCircuit(aux);
			return;
		}

		for (Vertex vertex : adj.get(next).keySet()) {
			if (!visited.get(vertex.getName())) {
				aux.add(vertex);
				visited.put(vertex.getName(), true);

				Backtracking(aux, lvl + 1, source, visited, vertex);

				aux.remove(vertex);
				visited.put(vertex.getName(), false);
			}
		}
	}

	/**
	 * The Class PathNode.
	 */
	protected class PathNode implements Comparable<PathNode> {
		
		/** The res. */
		private ArrayList<Vertex> res; // result
		
		/** The visited vertices. */
		private int visitedVertices; // The number of the visited vertices
		
		/** The total cost. */
		private double totalCost; // The total cost of the path taken so far
		
		/** The estimated cost. */
		private double estimatedCost; // Representing the lower bound of this state. * Priority

		/**
		 * Instantiates a new path node.
		 *
		 * @param vertexToVisit the vertex to visit
		 */
		PathNode(Vertex vertexToVisit) {
			res = new ArrayList<Vertex>();
			res.add(vertexToVisit);
			visitedVertices = 1;
			totalCost = 0.0;
			estimatedCost = numberOfVertices() * minEdgeValue;
		}

		/**
		 * Instantiates a new path node.
		 *
		 * @param parentPathNode the parent path node
		 */
		PathNode(PathNode parentPathNode) {
			this.res = new ArrayList<Vertex>(parentPathNode.res);
			this.visitedVertices = parentPathNode.visitedVertices;
			this.totalCost = parentPathNode.totalCost;
			this.estimatedCost = parentPathNode.estimatedCost;
		}

		/**
		 * Compare to.
		 *
		 * @param p the p
		 * @return the int
		 */
		@Override
		public int compareTo(PathNode p) {
			if (estimatedCost == p.estimatedCost)
				return 0;
			return estimatedCost > p.estimatedCost ? 1 : -1;
		}

		/**
		 * Gets the res.
		 *
		 * @return the res
		 */
		public ArrayList<Vertex> getRes() {
			return res;
		}

		/**
		 * Adds the vertex res.
		 *
		 * @param v the v
		 */
		public void addVertexRes(Vertex v) {
			this.res.add(v);
		}

		/**
		 * Last vertex res.
		 *
		 * @return the vertex
		 */
		public Vertex lastVertexRes() {
			return res.get(res.size() - 1);
		}

		/**
		 * Checks if is vertex visited.
		 *
		 * @param v the v
		 * @return true, if is vertex visited
		 */
		public boolean isVertexVisited(Vertex v) {
			return res.contains(v);
		}

		/**
		 * Gets the visited vertices.
		 *
		 * @return the visited vertices
		 */
		public int getVisitedVertices() {
			return visitedVertices;
		}

		/**
		 * Sets the visited vertices.
		 *
		 * @param visitedVertices the new visited vertices
		 */
		public void setVisitedVertices(int visitedVertices) {
			this.visitedVertices = visitedVertices;
		}

		/**
		 * Gets the total cost.
		 *
		 * @return the total cost
		 */
		public double getTotalCost() {
			return totalCost;
		}

		/**
		 * Sets the total cost.
		 *
		 * @param totalCost the new total cost
		 */
		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}

		/**
		 * Gets the estimated cost.
		 *
		 * @return the estimated cost
		 */
		public double getEstimatedCost() {
			return estimatedCost;
		}

		/**
		 * Sets the estimated cost.
		 *
		 * @param estimatedCost the new estimated cost
		 */
		public void setEstimatedCost(double estimatedCost) {
			this.estimatedCost = estimatedCost;
		}
	}

	/**
	 * Contains edge.
	 *
	 * @param from the from
	 * @param source the source
	 * @return true, if successful
	 */
	public boolean containsEdge(Vertex from, Vertex source) {
		if (adj.get(from).containsKey(source))
			return true;
		return false;
	}

	/**
	 * Weight.
	 *
	 * @param from the from
	 * @param source the source
	 * @return the double
	 */
	public double weight(Vertex from, Vertex source) {
		return adj.get(from).get(source);
	}

	/**
	 * Minimum edge value.
	 *
	 * @return the double
	 */
	// return the minimum value of the edges
	public double minimumEdgeValue() {
		double minimum = Double.MAX_VALUE;
		for (HashMap<Vertex, Double> value : adj.values()) {
			for (Double distance : value.values()) {
				if (distance < minimum)
					minimum = distance;
			}
		}
		return minimum;
	}

	/**
	 * TSP ba B.
	 *
	 * @return the array list
	 */
	// TSP - BaB - Best-First
	public ArrayList<Vertex> TSPBaB() {
		HashMap<Vertex, Double> neighborMap = adj.get(source);
		if (neighborMap == null)
			return null;

		minEdgeValue = minimumEdgeValue();

		PathNode firstNode = new PathNode(source);

		PriorityQueue<PathNode> priorityQueue = new PriorityQueue<>();

		priorityQueue.add(firstNode);

		shortestCircuit = null;
		double bestCost = Double.MAX_VALUE;

		while (priorityQueue.size() > 0) {
			PathNode Y = priorityQueue.poll();

			if (Y.getEstimatedCost() >= bestCost)
				break;
			else {
				Vertex from = Y.lastVertexRes();
				if ((Y.getVisitedVertices() == numberOfVertices()) && (containsEdge(from, source))) {
					Y.addVertexRes(source);
					Y.setTotalCost(Y.totalCost + weight(from, source));
					if (Y.getTotalCost() < bestCost) {
						bestCost = Y.getTotalCost();
						shortestDistanceCircuit = Y.getTotalCost();
						shortestCircuit = Y.getRes();
					}
				} else {
					for (Vertex to : adj.get(from).keySet()) {
						if (!Y.isVertexVisited(to)) {
							PathNode X = new PathNode(Y);

							X.addVertexRes(to);
							X.setVisitedVertices(X.getVisitedVertices() + 1);
							X.setTotalCost(X.totalCost + weight(from, to));

							Double cost = X.totalCost;
							cost += ((numberOfVertices() - X.getVisitedVertices() + 1) * minEdgeValue);
							X.setEstimatedCost(cost);

							if (X.getEstimatedCost() < bestCost) {
								priorityQueue.add(X);
							}
						}
					}
				}
			}
		}
		System.out.println("\n\nShortest circuit:");
		System.out.print(solutionToString(shortestCircuit));
		System.out.print(" ==> " + shortestDistanceCircuit);
		return shortestCircuit;
	}
}
