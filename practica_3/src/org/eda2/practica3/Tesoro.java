/*
 * 
 */
package org.eda2.practica3;

import java.text.DecimalFormat;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Tesoro.
 */
public class Tesoro implements Comparable<Tesoro> {

	/** The cantidad. */
	private double cantidad = 0;

	/** The nombre. */
	private String nombre;

	/** The peso. */
	private double peso;

	/** The valor. */
	private double valor;

	/**
	 * Instantiates a new tesoro.
	 *
	 * @param nombre the nombre
	 * @param peso   the peso
	 * @param valor  the valor
	 */
	public Tesoro(String nombre, double peso, double valor) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.valor = valor;
	}

	/**
	 * Instantiates a new tesoro.
	 *
	 * @param peso  the peso
	 * @param valor the valor
	 */
	public Tesoro(double peso, double valor) {
		super();
		this.nombre = generarNombre();
		this.peso = peso;
		this.valor = valor;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(Tesoro o) {
		int value = Double.compare(this.peso, o.peso);
		return value == 0 ? Double.compare(this.valor, this.valor) : value;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");

		String str = "";
		str += "\n\t" + this.nombre;
		str += "\n\t";
		for (int i = 0; i < 32; i++)
			str += "-";
		str += "\n\t|  Peso: " + df.format(this.peso) + " | Valor: " + df.format(this.valor) + "  |";
		return str;
	}

	/**
	 * Generar nombre.
	 *
	 * @return the string
	 */
	private String generarNombre() {
		String[] tesoro = { "Moneda", "Lingote", "Copa", "Anillo", "Espada", "Corona", "Collar", "Brazalete",
				"Pendiente", };
		String[] caracteristica = { "de oro", "de plata", "de mitrilo", "de diamante", "de esmeralda", "de zafiro",
				"de la muerte", "de vida", "de poder", "de la gloria", };
		String[] origen = { "de Zrokk", "enano", "elfico", "divino", "demoniaco", };

		return tesoro[new Random().nextInt(tesoro.length)] + " "
				+ caracteristica[new Random().nextInt(caracteristica.length)] + " "
				+ origen[new Random().nextInt(origen.length)];
	}
}
