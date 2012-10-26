package cell;

public class Cell {

	private CellContent[] celda;
	
	Cell() {
		celda = new CellContent[2];
	}
	public CellContent getTop() {
		return celda[1];
	}
	public CellContent getBottom() {
		return celda[0];
	}
	CellContent[] toArray() {
		return celda;
	}
}
