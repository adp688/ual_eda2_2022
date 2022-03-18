package org.eda2.practica1;

import java.util.ArrayList;
import java.util.Arrays;

public class NBARanking {

	private static final String PATH = System.getProperty("user.dir") + "\\";

	public static void main(String[] args) {

		String FILENAME = PATH + "NbaStats.csv";

		System.out.println(FILENAME);

		ArrayList<Player> playerList = parseData(CsvReader.readBooksFromCSV(FILENAME));

		playerList = mergeSort(playerList);

		printRanking(playerList);
	}

	public static ArrayList<Player> mergeSort(ArrayList<Player> playerList) {
		if (playerList.isEmpty()) {
			System.out.println("Array vacio");
			return null;
		}

		Player[] playerArray = new Player[playerList.size()];
		playerList.toArray(playerArray);

		mergeSort(playerArray, 0, playerArray.length - 1);

		playerList.clear();
		playerList.addAll(Arrays.asList(playerArray));

		return playerList;
	}

	public static void mergeSort(Player[] player, int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;
			mergeSort(player, left, middle);
			mergeSort(player, middle + 1, right);
			merge(player, left, middle, right);
		}
	}

	private static void merge(Player[] player, int left, int middle, int right) {
		Player[] leftArray = new Player[middle - left + 1];
		Player[] rightArray = new Player[right - middle];

		// Copiar player[] a cada lado de los arrays.
		for (int i = 0; i < leftArray.length; ++i)
			leftArray[i] = player[left + i];
		for (int i = 0; i < rightArray.length; ++i) {
			rightArray[i] = player[middle + 1 + i];
		}
		int index = 0;
		int leftIndex = 0;
		int rightIndex = 0;

		while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
			if (leftArray[leftIndex].getScore() < rightArray[rightIndex].getScore()) {
				player[index] = leftArray[leftIndex];
				leftIndex++;
			} else {
				player[index] = rightArray[rightIndex];
				rightIndex++;
			}
			index++;
		}
		while (leftIndex < leftArray.length) {
			player[index] = leftArray[leftIndex];
			leftIndex++;
			index++;
		}
		while (rightIndex < rightArray.length) {
			player[index] = rightArray[rightIndex];
			rightIndex++;
			index++;
		}
	}

	public static void printRanking(ArrayList<Player> playerList) {
		for (int i = 0; i < playerList.size(); ++i)
			System.out.print("\n#" + i + ": " + playerList.get(i).toString());
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

				playerData.get(index).AddData(player);
			}
		}
		return playerData;
	}
}