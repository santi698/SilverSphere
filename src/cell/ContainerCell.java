package cell;
import java.io.Serializable;

import cell.Cell;

/**
 * Clase que representa las celdas del tablero sobre las cuales los objetos se pueden mover.
 *
 */
public class ContainerCell extends Cell implements Serializable{

	private static final long serialVersionUID = 1L;
	private CellContent content;
	public ContainerCell(CellContent content) {
		this.content = content;
	}
	public ContainerCell() {
		
	}
	/**
	 * M�todo que se fija si un ContainerCell se encuentra vac�o.
	 * @return {@code True} si est� vac�o o {@code False} en el otro caso. 
	 */
	@Override
	public boolean isEmpty() {
		return content == null;
	}
	/**
	 * M�todo que setea la variable {@code c} con el par�metro recibido.
	 * @param c
	 */
	public void setContent(CellContent c) {
		content = c;
	}
	/**
	 * M�todo que devuelve el contenido.
	 * @return content {@code CellContent}
	 */
	public CellContent getContent() {
		return content;
	}
	public String toString() {
		if (isEmpty())
			return "Empty";
		return content.toString();
	}
	
}
