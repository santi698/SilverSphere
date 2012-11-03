package cell;
import java.io.Serializable;

import cell.Cell;


public class ContainerCell extends Cell implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CellContent content;
	public ContainerCell(CellContent content) {
		this.content = content;
	}
	public ContainerCell() {
		
	}
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
	public String toString() {
		if (isEmpty())
			return "Empty";
		return content.toString();
	}
	
}
