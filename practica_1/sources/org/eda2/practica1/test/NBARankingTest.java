package org.eda2.practica1.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.eda2.practica1.src.CsvReader;
import org.eda2.practica1.src.NBARanking;
import org.eda2.practica1.src.Player;

public class NBARankingTest {
	private static final String PATH = System.getProperty("user.dir") + "\\";

	static ArrayList<Player> playerList;

	public static void main(String args[]) {
		String FILENAME = "NbaStats.csv";

		System.out.println("Obteniendo datos del archivo: " + FILENAME + "\n");
		playerList = NBARanking.parseData(CsvReader.readBooksFromCSV(PATH + FILENAME));

		System.out.println("Escribe que tipo de test quieres pasar(0 - SortTest, 1 - TimeTest): ");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		if (input == 0)
			SortTest();
		else
			TimeTest();
		sc.close();
	}

	private static void SortTest() {

		writeToFile(playerList, "listaNoOrdenada");
		NBARanking.sort(playerList);
		writeToFile(playerList, "listaOrdenada");

		printTopRanking(playerList);
	}

	private static void TimeTest() {
		System.out.println("Resultados obtenidos usando el conjuto de datos (tamaño 3000±): ");
		long start = System.currentTimeMillis();
		NBARanking.sort(playerList);
		long end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start));

		ArrayList<Player> randomList = new ArrayList<Player>();

		randomList.clear();
		randomList.addAll(NBARanking.generateRandomPlayerList(10000));
		System.out.println("Resultados obtenidos (tamaño " + 10000 + "): ");
		start = System.currentTimeMillis();
		NBARanking.sort(randomList);
		end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start));

		randomList.clear();
		randomList.addAll(NBARanking.generateRandomPlayerList(50000));
		System.out.println("Resultados obtenidos (tamaño " + 50000 + "): ");
		start = System.currentTimeMillis();
		NBARanking.sort(randomList);
		end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start));

		int n = 100000;
		for (int i = 0; i < 10; i++) {
			randomList.clear();
			randomList.addAll(NBARanking.generateRandomPlayerList(n));
			System.out.println("Resultados obtenidos (tamaño " + n + "): ");
			start = System.currentTimeMillis();
			NBARanking.sort(randomList);
			end = System.currentTimeMillis();
			System.out.println("Tiempo: " + (end - start));
			n += 100000;
		}
	}

	public static void printTopRanking(ArrayList<Player> playerList) {
		int c = 1;
		for (int i = 0; i < 10; ++i) {
			System.out.print("\n#" + c + ": " + playerList.get(i).toString());
			c++;
		}
		System.out.println();
	}

	public static void writeToFile(ArrayList<Player> playerList, String filename) {
		try {
			PrintStream filestream = new PrintStream(new File(filename + ".txt"));
			for (Player player : playerList) {
				filestream.println(player.toString());
			}
			filestream.close();
			System.out.println("Se escribio el archivo satisfactoriamente " + filename + ".txt");
		} catch (IOException e) {
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
	}
}
