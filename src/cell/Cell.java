package cell;

import java.awt.Image;

public abstract class Cell {
	private Image image;
	public abstract boolean isEmpty();
	public Image getImage() {
		return image;
	}
	public CellContent getContent() {
		// TODO Auto-generated method stub
		return null;
	}
}