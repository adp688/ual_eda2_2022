package org.eda2.practica4;

// TODO: Auto-generated Javadoc
/**
 * The Class Vertex.
 */
public class Vertex {

	/** The x. */
	private final int x;
	
	/** The y. */
	private final int y;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new vertex.
	 *
	 * @param x the x
	 * @param y the y
	 * @param name the name
	 */
	public Vertex(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	/**
	 * Instantiates a new vertex.
	 *
	 * @param name the name
	 */
	public Vertex(String name) {
		this.x = Integer.MAX_VALUE;
		this.y = Integer.MAX_VALUE;
		this.name = name;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	/**
	 * Equals.
	 *
	 * @param other the other
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Vertex)
			return this.name.equals(((Vertex) other).getName());
		return false;
	}

}
