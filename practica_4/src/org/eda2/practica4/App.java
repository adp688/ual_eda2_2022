package org.eda2.practica4;

public class App {

	private static final String PATH = System.getProperty("user.dir") + "\\";

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("The number of arguments is: " + args.length);
		System.out.println("Your first argument is: " + args[0]);
		System.out.println("Your second argument is: " + args[1]);

		TSP tsp = new TSP();

		tsp.load(PATH + args[1]);

		tsp.print();
		if (args.length > 2 && args[2].isEmpty())
			tsp.source = new Vertex(args[2]);

		switch (args[0]) {
		case "TSP_Backtracking":
			tsp.TSPBacktracking();
			break;

		case "TSP_BaB":
			tsp.TSPBaB();
			break;

		default:
			break;
		}

		Display.mostrar(tsp.adj, tsp.source, tsp.shortestCircuit);
	}
}
