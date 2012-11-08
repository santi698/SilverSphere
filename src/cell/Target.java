package cell;

import board.Position;

/**
 * Clase que representa el destino del jugador. Una ve alcanzado el juego finaliza y el jugador gana el nivel.
 *
 */
public class Target extends ContainerCell {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible;
	private Position position;
	/**
	 * 
	 * @return position {@code Position} con la posición de la instancia.
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * Metodo que se fija si la instancia se encuentra visible.
	 * @return {@code True} si es visible o {@code False} en caso contrario. 
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * Metodo que hace que la instancia sea visible.
	 */
	public void setVisible() {
		visible = true;
		
	}
	public String toString() {
		return "Destino + " + this.getContent();
	}
	/**
	 * Metodo que fija la posicion de la instancia segun el parametro recibido. 
	 * @param pos
	 */
	public void setPosition(Position pos) {

		position = pos;
	}

}
