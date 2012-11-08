package cell;

/**
 * Representa un interruptor que al ser presionado muestra el destino
 * @author santi698
 *
 */
public class IceBlockTarget extends ContainerCell {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible;
	
	/**
	 * Metodo que determina si el {@code IcesBlockTarget} es visible o no. 
	 * @return {@code True} si es visible o {@code False} en caso contrario. 
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * Metodo que determina que la instancia es visible
	 */
	public void setVisible() {
		visible = true;
		
	}
	public String toString() {
		return "Interruptor + " + this.getContent();
	}

}
