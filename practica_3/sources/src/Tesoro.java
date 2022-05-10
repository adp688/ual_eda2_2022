package src;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Tesoro implements Comparable<Tesoro> {

	public static ArrayList<Tesoro> tesoros;
	private static int size;

	private String nombre;
	private int ID;
	private double peso;
	private double valor;

	public Tesoro(String nombre, double peso, double valor) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.valor = valor;
		this.ID = size++;
	}

	public Tesoro(double peso, double valor) {
		super();
		this.nombre = generarNombre();
		this.peso = peso;
		this.valor = valor;
		this.ID = size++;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPeso() {
		return peso;
	}

	public double getValor() {
		return valor;
	}

	public int getID() {
		return ID;
	}

	@Override
	public int compareTo(Tesoro o) {
		int value = Double.compare(this.peso, o.peso);
		return value == 0 ? Double.compare(this.valor, this.valor) : value;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");

		String str ="";
		str += "\n\t" + this.nombre;
		str += "\n\tID:" + this.ID;
		str +="\n\t";
		for (int i = 0; i < 32; i++)
			str += "-";
		str += "\n\t|  Peso: " + df.format(this.peso) + " | Valor: " + df.format(this.valor) + "  |";
		return str;
	}

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
