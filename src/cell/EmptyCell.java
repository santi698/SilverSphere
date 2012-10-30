package cell;
//TODO elegir un mejor nombre para esta clase, no está siempre vacia...
public class EmptyCell extends ContainerCell {
	
	public EmptyCell() {
		super.setContent(null);
	}
	public EmptyCell(CellContent c) {
		super.setContent(c);
	}
	public String toString() {
		if (super.getContent() == null) {
			return "Empty";
		}
		return super.getContent().toString();
	}
}
