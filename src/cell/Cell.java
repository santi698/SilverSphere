package cell;

public class Cell {

	private CellContent[] celda;
	
	Cell() {
		celda = new CellContent[2];
	}
	CellContent getTop() {
		return celda[1];
	}
	CellContent getBottom() {
		return celda[0];
	}
	CellContent[] toArray() {
		return celda;
	}
}
