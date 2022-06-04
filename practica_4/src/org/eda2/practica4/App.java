package org.eda2.practica4;

public class App {

	private static final String PATH = System.getProperty("user.dir") + "\\";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("The number of arguments is: " + args.length);
		System.out.println("Your first argument is: " + args[0]);
		System.out.println("Your second argument is: " + args[1]);

		TSP tsp = new TSP();

		tsp.load(PATH + "datasets" + "\\" + args[1]);
		
		tsp.print();
	}
}
