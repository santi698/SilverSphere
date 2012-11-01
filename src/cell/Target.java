package cell;


/**
 * Representa la celda de destino, la celda a la que debe llegar
 * el jugador para completar el nivel
 * @author santi698
 *
 */
public class Target extends ContainerCell {

	private boolean visible;
	
	public boolean isVisible() {
		return visible;
	}
	//FIXME no muestra el contenido
	public String toString() {
		return "Destino";
	}
}
