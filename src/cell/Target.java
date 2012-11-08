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
	 * @return position {@code Position} con la posici�n de la instancia.
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * M�todo que se fija si la instancia se encuentra visible.
	 * @return {@code True} si es visible o {@code False} en caso contrario. 
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * M�todo que hace que la instancia sea visible.
	 */
	public void setVisible() {
		visible = true;
		
	}
	public String toString() {
		return "Destino + " + this.getContent();
	}
	/**
	 * M�todo que fija la posici�n de la instancia seg�n el par�metro recibido. 
	 * @param pos
	 */
	public void setPosition(Position pos) {

		position = pos;
	}

}
