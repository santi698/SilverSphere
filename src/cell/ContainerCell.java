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
	 * Metodo que se fija si un ContainerCell se encuentra vacio.
	 * @return {@code True} si esta vacio o {@code False} en el otro caso. 
	 */
	@Override
	public boolean isEmpty() {
		return content == null;
	}
	/**
	 * Metodo que setea la variable {@code c} con el parametro recibido.
	 * @param c
	 */
	public void setContent(CellContent c) {
		content = c;
	}
	/**
	 * Metodo que devuelve el contenido.
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
