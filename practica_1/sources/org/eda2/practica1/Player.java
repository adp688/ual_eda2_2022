package org.eda2.practica1;

import java.util.ArrayList;

public class Player {

	private String playerName;
	private ArrayList<String> teams;
	private ArrayList<String> positions;
	private int score;

	public Player(String playerName, String team, String position, int score) {
		this.playerName = playerName;
		this.teams = new ArrayList<String>();
		this.teams.add(team);
		this.positions = new ArrayList<String>();
		this.positions.add(position);
		this.score = score;
	}

	public Player(String playerName, ArrayList<String> teams, ArrayList<String> positions, int score) {
		this.playerName = playerName;
		this.teams = teams;
		this.positions = positions;
		this.score = score;
	}

	public Player(Player player) {
		this.playerName = player.getPlayerName();
		this.teams = player.getTeams();
		this.positions = player.getPositions();
		this.score = player.score;
	}

//	public void SetData(Player player) {
//		this.playerName = player.getPlayerName();
//		this.teams = player.getTeams();
//		this.positions = player.getPositions();
//		this.score = player.score;
//	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public ArrayList<String> getTeams() {
		return this.teams;
	}

	public void setTeams(ArrayList<String> teams) {
		this.teams = teams;
	}

	public ArrayList<String> getPositions() {
		return this.positions;
	}

	public void setPositions(ArrayList<String> positions) {
		this.positions = positions;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Name=" + playerName + ", Team=" + teams + ", Position=" + positions + ", Score=" + score;
	}

}