package cell;
import java.io.Serializable;

import cell.Cell;


public abstract class ContainerCell extends Cell implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CellContent content;
	@Override
	public boolean isEmpty() {
		return content == null;
	}
	public void setContent(CellContent c) {
		content = c;
	}
	public CellContent getContent() {
		return content;
	}
	
}
