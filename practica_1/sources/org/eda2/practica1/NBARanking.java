package org.eda2.practica1;

import java.util.ArrayList;
import java.util.Arrays;

public class NBARanking {

	private static final String PATH = System.getProperty("user.dir") + "\\";

	public static void main(String[] args) {

		String FILENAME = PATH + "NbaStats.csv";

		System.out.println(FILENAME);

		ArrayList<Player> playerList = parseData(CsvReader.readBooksFromCSV(FILENAME));

		mergeSort(playerList);

		//Full Ranking imprime la lista completa, top ranking solo del 1 al 10.
		
		// printFullRanking(playerList);
		printTopRanking(playerList);
	}

	public static void mergeSort(ArrayList<Player> playerList) {
		if (playerList.isEmpty()) {
			System.out.println("Array vacio");
		}

		Player[] playerArray = new Player[playerList.size()];
		playerList.toArray(playerArray);

		mergeSort(playerArray, 0, playerArray.length - 1);

//		for (Player player : playerArray) {
//			System.out.println(player.toString());
//		}

		playerList.clear();
		playerList.addAll(Arrays.asList(playerArray));
	}

	public static void mergeSort(Player[] player, int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;
			// System.out.println(left + "L, " + right + "R");
			// ordena la primera y segunda mitad.
			mergeSort(player, left, middle);
			mergeSort(player, middle + 1, right);

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
			// System.out.println(leftArray[i].toString());
		}
		for (int i = 0; i < nR; ++i) {
			rightArray[i] = new Player(player[middle + 1 + i]);
			// System.out.println(rightArray[i].toString());
		}
		// System.out.println("- - - - - - -");
		int index = left;
		int leftIndex = 0;
		int rightIndex = 0;

		while (leftIndex < nL && rightIndex < nR) {
			if (leftArray[leftIndex].getScore() < rightArray[rightIndex].getScore()) {
				player[index] = new Player(leftArray[leftIndex]);
				leftIndex++;
			} else {
				player[index] = new Player(rightArray[rightIndex]);
				rightIndex++;
			}
			index++;
		}
		while (leftIndex < nL) {
			player[index].SetData(leftArray[leftIndex]);
			leftIndex++;
			index++;
		}
		while (rightIndex < nR) {
			player[index].SetData(rightArray[rightIndex]);
			rightIndex++;
			index++;
		}

//		for (int i = left; i <= right; i++) {
//			System.out.println("#" + i + player[i].toString());
//		}
//		System.out.println("- - - - - - -");
	}

	public static void printFullRanking(ArrayList<Player> playerList) {
		for (int i = 0; i < playerList.size(); ++i)
			System.out.print("\n#" + i + ": " + playerList.get(i).toString());
		System.out.println();
	}

	public static void printTopRanking(ArrayList<Player> playerList) {
		int c = 1;
		for (int i = playerList.size() - 1; i > playerList.size() - 11; --i) {
			System.out.print("\n#" + c + ": " + playerList.get(i).toString());
			c++;
		}
		System.out.println();
	}

	private static ArrayList<Player> parseData(ArrayList<String> data) {
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

				playerData.get(index).SetData(player);
			}
		}
		return playerData;
	}
}