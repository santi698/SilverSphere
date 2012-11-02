package cell;

import java.io.Serializable;


public abstract class Cell implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CellContent content = null;
	
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
