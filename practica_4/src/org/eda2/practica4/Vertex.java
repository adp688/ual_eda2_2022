package org.eda2.practica4;

public class Vertex {

//	private final int x;
//	private final int y;
	private String name;
	private double weight;

	public Vertex(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Vertex)
			return this.name.equals(((Vertex) other).getName());
		return false;
	}
}
