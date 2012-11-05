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
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible() {
		visible = true;
		
	}
	public String toString() {
		return "Interruptor + " + this.getContent();
	}

}
