package org.eda2.practica2.src;

public class EDAland {

	private static final String PATH = System.getProperty("user.dir") + "\\";
	private static final String DATASET = "dataset\\";

	public static void main(String[] args) {
		String file = "graphEDAland";
		System.out.println(PATH + DATASET + file + ".txt");

		Grafo grafo = new Grafo(PATH + DATASET + file + ".txt");
		
		grafo.print();
		
		Prim.primMST(grafo);
	}
	
}
