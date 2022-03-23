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
	public void testA_PlayerSort() {
		ArrayList<Player> arraylistTest1 = NBARanking.generateRandomPlayerList(100);

		ArrayList<Player> arraylistTest2 = arraylistTest1;
		Collections.shuffle(arraylistTest2);

		NBARanking.sort(arraylistTest2);

		assertArrayEquals(arraylistTest1.toArray(), arraylistTest2.toArray());
	}
	
	@Test
	public void testB_PlayerGetDatos_ListaNoOrdenada() {
		ArrayList<String> teams = new ArrayList<String>(Arrays.asList("WSB"));
		ArrayList<String> pos = new ArrayList<String>(Arrays.asList("23", "24"));
		Player player = new Player("A.J. English", teams, pos, 326);

		assertEquals(player.getPlayerName(), playerList.get(2).getPlayerName());
		assertArrayEquals(player.getTeams().toArray(), playerList.get(2).getTeams().toArray());
		assertArrayEquals(player.getPositions().toArray(), playerList.get(2).getPositions().toArray());
		assertEquals(player.getScore(), playerList.get(2).getScore());
	}

	@Test
	public void testC_PlayerListSort() {
		ArrayList<Player> arraylistTop = new ArrayList<Player>();
		arraylistTop.add(new Player("LeBron James", "t", "p", 1031));
		arraylistTop.add(new Player("James Harden", "t", "p", 994));
		arraylistTop.add(new Player("Stephen Curry", "t", "p", 975));
		arraylistTop.add(new Player("Karl-Anthony Towns", "t", "p", 965));
		arraylistTop.add(new Player("Russell Westbrook", "t", "p", 931));

		arraylistTop.add(new Player("Anthony Davis", "t", "p", 894));
		arraylistTop.add(new Player("Kevin Durant", "t", "p", 864));
		arraylistTop.add(new Player("Damian Lillard", "t", "p", 825));
		arraylistTop.add(new Player("DeMar DeRozan", "t", "p", 820));
		arraylistTop.add(new Player("Wilt Chamberlain*", "t", "p", 785));

		NBARanking.sort(playerList);

		for (int i = 0; i < 10; i++) {
			assertEquals(arraylistTop.get(i).getPlayerName(), playerList.get(i).getPlayerName());
			assertEquals(arraylistTop.get(i).getScore(), playerList.get(i).getScore());
		}
	}
}
