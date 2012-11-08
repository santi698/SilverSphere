package board;

import java.io.Serializable;

/**
 * Clase que representa una posicion a traves de variables (x, y).
 * 
 *
 */
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;
	public final int x;
	public final int y;
	/**
	 * Crea una nueva posicion con coordenadas (x, y).
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Metodo que calcula una nueva {@code Position} a partir de una direccion.
	 * @param d la direccion de la posicion buscada.
	 * @return La position siguiente en la direccion especificada.
	 * @see {@link Direction}
	 */
	public Position next(Direction d) {
		return new Position(x + d.x, y + d.y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
