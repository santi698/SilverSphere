package cell;

import java.awt.Image;

/**
 * Representa la celda de destino, la celda a la que debe llegar
 * el jugador para completar el nivel
 * @author santi698
 *
 */
public class Destino extends ContainerCell {

	private boolean visible;
	
	public boolean isVisible() {
		return visible;
	}
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	public String toString() {
		return "Destino";
	}
}
