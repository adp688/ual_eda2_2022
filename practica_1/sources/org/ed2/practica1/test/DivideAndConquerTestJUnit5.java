package org.ed2.practica1.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.eda2.practica1.CsvReader;
import org.eda2.practica1.NBARanking;
import org.eda2.practica1.Player;
import org.junit.Before;
import org.junit.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DivideAndConquerTestJUnit5 {

	private static final String PATH = System.getProperty("user.dir") + "\\";

	ArrayList<Player> playerList;

	@Before
	public void setUp() throws Exception {
		String FILENAME = "NbaStats.csv";

		playerList = NBARanking.parseData(CsvReader.readBooksFromCSV(PATH + FILENAME));
	}

	@Test
	public void testA_PlayerGetDatos() {
		ArrayList<String> teams = new ArrayList<String>(Arrays.asList("WSB"));
		ArrayList<String> pos = new ArrayList<String>(Arrays.asList("23", "24"));
		Player player = new Player("A.J. English", teams, pos, 751);

		assertEquals(player.getPlayerName(), playerList.get(2).getPlayerName());
		assertArrayEquals(player.getTeams().toArray(), playerList.get(2).getTeams().toArray());
		assertArrayEquals(player.getPositions().toArray(), playerList.get(2).getPositions().toArray());
		assertEquals(player.getScore(), playerList.get(2).getScore());
	}

	@Test
	public void testB_PlayerSort() {
		ArrayList<Player> arraylistTest1 = new ArrayList<Player>();
		arraylistTest1.add(
				new Player("A", "team" + String.valueOf(Math.random() * 10), String.valueOf(Math.random() * 26), 50));
		arraylistTest1.add(
				new Player("B", "team" + String.valueOf(Math.random() * 10), String.valueOf(Math.random() * 26), 60));
		arraylistTest1.add(
				new Player("C", "team" + String.valueOf(Math.random() * 10), String.valueOf(Math.random() * 26), 70));
		arraylistTest1.add(
				new Player("D", "team" + String.valueOf(Math.random() * 10), String.valueOf(Math.random() * 26), 80));
		arraylistTest1.add(
				new Player("E", "team" + String.valueOf(Math.random() * 10), String.valueOf(Math.random() * 26), 90));

		ArrayList<Player> arraylistTest2 = arraylistTest1;
		Collections.shuffle(arraylistTest2);

		NBARanking.sort(arraylistTest2);

		assertArrayEquals(arraylistTest1.toArray(), arraylistTest2.toArray());
	}

	@Test
	public void testC_PlayerListSort() {
		ArrayList<Player> arraylistTop = new ArrayList<Player>();
		arraylistTop.add(new Player("James Harden", "t", "p", 2258));
		arraylistTop.add(new Player("Russell Westbrook", "t", "p", 2154));
		arraylistTop.add(new Player("Stephen Curry", "t", "p", 2031));
		arraylistTop.add(new Player("LeBron James", "t", "p", 1929));
		arraylistTop.add(new Player("Damian Lillard", "t", "p", 1900));

		arraylistTop.add(new Player("DeMar DeRozan", "t", "p", 1809));
		arraylistTop.add(new Player("Karl-Anthony Towns", "t", "p", 1782));
		arraylistTop.add(new Player("Anthony Davis", "t", "p", 1768));
		arraylistTop.add(new Player("Michael Jordan*", "t", "p", 1734));
		arraylistTop.add(new Player("Andrew Wiggins", "t", "p", 1732));

		NBARanking.sort(playerList);

		for (int i = 0; i < 10; i++) {
			assertEquals(arraylistTop.get(i).getPlayerName(), playerList.get(i).getPlayerName());
			assertEquals(arraylistTop.get(i).getScore(), playerList.get(i).getScore());
		}
	}
}
