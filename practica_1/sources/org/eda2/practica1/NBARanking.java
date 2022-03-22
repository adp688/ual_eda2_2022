package org.eda2.practica1;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class NBARanking {

	private static final String PATH = System.getProperty("user.dir") + "\\";

	public static void main(String[] args) {

		String FILENAME = PATH + "NbaStats.csv";

		System.out.println(FILENAME);

		ArrayList<Player> playerList = parseData(CsvReader.readBooksFromCSV(FILENAME));

		writeToFile(playerList, "listaNoOrdenada");
		sort(playerList);
		writeToFile(playerList, "listaOrdenada");

		// Full Ranking imprime la lista completa, top ranking solo del 1 al 10.
		printTopRanking(playerList);
	}

	public static void sort(ArrayList<Player> playerList) {
		if (playerList.isEmpty()) {
			System.out.println("Array vacio");
		}

		Player[] playerArray = new Player[playerList.size()];
		playerList.toArray(playerArray);

		sort(playerArray, 0, playerArray.length - 1);

		playerList.clear();
		playerList.addAll(Arrays.asList(playerArray));
	}

	public static void sort(Player[] player, int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;

			// ordena la primera y segunda mitad.
			sort(player, left, middle);
			sort(player, middle + 1, right);

			// une las dos en una sola ordenandolas.
			merge(player, left, middle, right);
		}
	}

	private static void merge(Player[] player, int left, int middle, int right) {
		int nL = middle - left + 1;
		int nR = right - middle;

		Player[] leftArray = new Player[nL];
		Player[] rightArray = new Player[nR];

		// Copiar player[] a cada lado de los arrays.
		for (int i = 0; i < nL; ++i) {
			leftArray[i] = new Player(player[left + i]);
		}
		for (int i = 0; i < nR; ++i) {
			rightArray[i] = new Player(player[middle + 1 + i]);
		}

		int index = left;
		int leftIndex = 0;
		int rightIndex = 0;

		while (leftIndex < nL && rightIndex < nR) {
			if (leftArray[leftIndex].getScore() > rightArray[rightIndex].getScore()) {
				player[index] = new Player(leftArray[leftIndex]);
				leftIndex++;
			} else {
				player[index] = new Player(rightArray[rightIndex]);
				rightIndex++;
			}
			index++;
		}
		while (leftIndex < nL) {
			player[index] = new Player(leftArray[leftIndex]);
			leftIndex++;
			index++;
		}
		while (rightIndex < nR) {
			player[index] = new Player(rightArray[rightIndex]);
			rightIndex++;
			index++;
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

	public static ArrayList<Player> parseData(ArrayList<String> data) {
		ArrayList<Player> playerData = new ArrayList<>();

		for (String element : data) {
			String[] attributes = element.split(";");

			String name = attributes[2];
			String team = attributes[6];
			String position = attributes[5];
			int score = Integer.parseInt(attributes[8].replace(",", ""));

			// Checkear si esta dentro.
			int index = -1;
			for (int i = 0; i < playerData.size(); i++) {
				if (playerData.get(i).getPlayerName().toLowerCase().equals(attributes[2].toLowerCase())) {
					index = i;
					break;
				}
			}

			Player player = null;
			if (index < 0) {
				player = new Player(name, team, position, score);
				playerData.add(player);
			} else {
				player = playerData.get(index);

				ArrayList<String> playerTeams = player.getTeams();
				if (!playerTeams.contains(team)) {
					playerTeams.add(attributes[6]);
					player.setTeams(playerTeams);
				}

				ArrayList<String> playerPositions = player.getPositions();
				if (!playerPositions.contains(position)) {
					playerPositions.add(attributes[5]);
					player.setPositions(playerPositions);
				}

				player.setScore((player.getScore() + score) / 2);

				playerData.set(index, player);
			}
		}
		return playerData;
	}

	private static void writeToFile(ArrayList<Player> playerList, String filename) {
		try {
			PrintStream filestream = new PrintStream(new File(filename + ".txt"));
			for (Player player : playerList) {
				filestream.println(player.toString());
			}
			filestream.close();
			System.out.println("Successfully wrote to the file " + filename + ".txt");
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
}