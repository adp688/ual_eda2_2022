package org.ed2.practica1.test;

import java.util.ArrayList;

import org.eda2.practica1.CsvReader;
import org.eda2.practica1.NBARanking;
import org.eda2.practica1.Player;

public class NBARankingTimeTest {
	private static final String PATH = System.getProperty("user.dir") + "\\";

	public static void main(String args[]) {
		String FILENAME = "NbaStats.csv";

		ArrayList<Player> playerList = NBARanking.parseData(CsvReader.readBooksFromCSV(PATH + FILENAME));

		System.out.println("Resultados obtenidos usando el conjuto de datos (tamaño 3000±): ");
		long start = System.currentTimeMillis();
		NBARanking.sort(playerList);
		long end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start));

		ArrayList<Player> randomList = new ArrayList<Player>();

		randomList.clear();
		randomList.addAll(GenerateRandomPlayerList(10000));
		System.out.println("Resultados obtenidos (tamaño " + 10000 + "): ");
		start = System.currentTimeMillis();
		NBARanking.sort(randomList);
		end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start));

		randomList.clear();
		randomList.addAll(GenerateRandomPlayerList(50000));
		System.out.println("Resultados obtenidos (tamaño " + 50000 + "): ");
		start = System.currentTimeMillis();
		NBARanking.sort(randomList);
		end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start));

		int n = 100000;
		for (int i = 0; i < 10; i++) {
			randomList.clear();
			randomList.addAll(GenerateRandomPlayerList(n));
			System.out.println("Resultados obtenidos (tamaño " + n + "): ");
			start = System.currentTimeMillis();
			NBARanking.sort(randomList);
			end = System.currentTimeMillis();
			System.out.println("Tiempo: " + (end - start));
			n += 100000;
		}
	}

	public static ArrayList<Player> GenerateRandomPlayerList(int size) {
		ArrayList<Player> player = new ArrayList<Player>();

		for (int i = 0; i < size; i++) {
			player.add(GenerateRandomPlayer());
		}
		return player;
	}

	public static Player GenerateRandomPlayer() {

		Player player = new Player("Player", "Team" + String.valueOf(Math.random() * 10),
				String.valueOf(Math.random() * 26), (int) Math.random() * 2000);
		return player;
	}
}
