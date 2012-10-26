package cell;

import java.awt.Image;

/**
 * Representa la celda de destino, la celda a la que debe llegar
 * el jugador para completar el nivel
 * @author santi698
 *
 */
public class Destino extends CellContent implements Walkable {

	private boolean visible;
	public boolean isVisible() {
		//TODO method stub
		return false;
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
}
