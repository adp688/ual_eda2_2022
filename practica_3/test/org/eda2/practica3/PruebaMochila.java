/*
 * 
 */
package org.eda2.practica3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class PruebaMochila.
 */
class PruebaMochila {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	/**
 * Test mochila DP integer.
 */
@Test
	public void test_MochilaDPInteger() {
		Cueva.cargarArchivo("p02");
		Cueva.print();
		ArrayList<Tesoro> expected = new ArrayList<Tesoro>();
		expected.add(new Tesoro("Tesoro_2", 7, 13));
		expected.add(new Tesoro("Tesoro_3", 11, 23));
		expected.add(new Tesoro("Tesoro_4", 8, 15));
		Mochila.dpInteger();
		Collections.sort(expected);
		Collections.sort(Mochila.getInventario());
		assertEquals(expected.toString(), Mochila.getInventario().toString());
	}

	/**
	 * Test mochila DP infinito.
	 */
	@Test
	public void test_MochilaDPInfinito() {
		Cueva.cargarArchivo("p04");
		Cueva.print();
		ArrayList<Tesoro> expected = new ArrayList<Tesoro>();
		expected.add(new Tesoro("Tesoro_4", 19, 37));
		expected.add(new Tesoro("Tesoro_1", 31, 70));
		
		Mochila.dpInfinitoInteger();
		Collections.sort(expected);
		Collections.sort(Mochila.getInventario());
		assertEquals(expected.toString(), Mochila.getInventario().toString());
	}

	/**
	 * Test mochila greedy.
	 */
	@Test
	public void test_MochilaGreedy() {
		Cueva.cargarArchivo("p03");
		Cueva.print();
		ArrayList<Tesoro> expected = new ArrayList<Tesoro>();
		expected.add(new Tesoro("Tesoro_3", 80, 64));
		expected.add(new Tesoro("Tesoro_5", 75, 50));
		expected.add(new Tesoro("Tesoro_4", 64, 46));
		Mochila.greedy();
		Collections.sort(expected);
		Collections.sort(Mochila.getInventario());
		assertEquals(expected.toString(), Mochila.getInventario().toString());
	}
}
