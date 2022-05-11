package pruebaDelEsca;

public class Mochila {
	
	private int pesoMochila;
	private Tesoro[] tesoros;
	private int peso;
	private int valor;
	
	public Mochila(int pesoMaximo, int numTesoros) {
		this.pesoMochila = pesoMochila;
		this.tesoros = new Tesoro[numTesoros];
		this.valor = 0;
		this.peso = 0;
	}

	public int getPesoMochila() {
		return pesoMochila;
	}

	public void setPesoMochila(int pesoMochila) {
		this.pesoMochila = pesoMochila;
	}

	public Tesoro[] getTesoros() {
		return tesoros;
	}

	public void setTesoros(Tesoro[] tesoros) {
		this.tesoros = tesoros;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	/** 
	 * Este codigo lo que hace es meter elementos en la mochila
	 */
	public void añadirTesororo(Tesoro a) {
		for (int i = 0; i<this.tesoros.length; i++) {
			if (this.tesoros[i] == null) {
				this.tesoros[i] = a; //lo añade
				this.valor += a.getValor(); //aumenta el valor
				this.peso += a.getPeso();
				break;
			}	
		}
	}
	
	/**
	 * Con este limpiamos la mochila
	 */
	public void vaciarMochila() {
		this.peso=0;
		this.valor = 0;
		for (int i = 0; i< this.tesoros.length; i++) {
			this.tesoros[i]=null;
		}
		
	}
	
	/**
	 * Elimina un elemento especifico de la mochila
	 */
	public void eliminarTesoro (Tesoro a) {
		for (int i = 0; i < this.tesoros.length; i++) {
				if (this.tesoros[i].equals(a)) {
					this.tesoros[i]=null; //el elemento que he quitado situado en el indice i
					this.peso -= a.getPeso(); //resta el peso
					this.valor -= a.getValor();//resta valor
					break;
				}
		}
	
	}
	
	/**
	 * Indica si existe un tesoro (Esto es para que no se repita el tesoro)
	 */
	public boolean existeTesoro (Tesoro a) {
		for (int i=0; i< this.tesoros.length; i++) {
			if (this.tesoros[i] != null && this.tesoros[i].equals(a)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Enseña la mochila
	 */
	public String toString() {
		String m = "";
		for (int i = 0; i<this.tesoros.length; i++) {
			if (this.tesoros[i] != null) {
				m += tesoros[i] + "\n";
			}
		}
		m+="Peso mochila = "+ getPeso() + "\n";
		m+="Valor mochila = "+ getValor() + "\n";
		return m;
	}
}
