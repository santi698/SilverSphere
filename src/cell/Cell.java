package cell;


public abstract class Cell {
	private CellContent content;
	
	public boolean isEmpty() {
		return content != null;
	}
	public void setContent(CellContent c) {
		content = c;
	}
	public CellContent getContent() {
		return content;
	}
	
}
