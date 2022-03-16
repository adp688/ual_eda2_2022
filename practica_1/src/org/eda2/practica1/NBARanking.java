package org.eda2.practica1;

import java.util.ArrayList;

public class NBARanking {

	private static final String PATH = System.getProperty("user.dir") + "\\";

	public static void main(String[] args) {

		String FILENAME = PATH + "NbaStats.csv";

		System.out.println(FILENAME);

		ArrayList<Player> players = ParseData(CsvReader.readBooksFromCSV(FILENAME));

		for (Player player : players) {
			System.out.println(player.toString());
		}
	}

	private static ArrayList<Player> ParseData(ArrayList<String> data) {
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