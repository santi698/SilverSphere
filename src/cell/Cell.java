package cell;

import java.io.Serializable;


public abstract class Cell implements Serializable{

	private static final long serialVersionUID = 1L;
	private CellContent content = null;
	/**
	 * 
	 * @return {@code true} si la celda está vacía, {@code false} sino.
	 */
	public boolean isEmpty() {
		return content == null;
	}
	/**
	 * Reemplaza el contenido de la celda con {@code c}
	 * @param c El nuevo contenido de la celda.
	 */
	public void setContent(CellContent c) {
		content = c;
	}
	/**
	 * Retorna el contenido de la celda.
	 * @return El contenido de la celda.
	 */
	public CellContent getContent() {
		return content;
	}
	
}
