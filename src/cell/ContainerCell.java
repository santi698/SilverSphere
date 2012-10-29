package cell;
import cell.Cell;


public abstract class ContainerCell extends Cell {
	private CellContent content;
	@Override
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
