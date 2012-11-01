package cell;

/**
 * Representa un interruptor que al ser presionado muestra el destino
 * @author santi698
 *
 */
public class IceBlockTarget extends ContainerCell {

	//FIXME no muestra el contenido
	public String toString() {
		return "Interruptor + " + this.getContent();
	}
}
