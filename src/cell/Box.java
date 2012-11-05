package cell;

import java.awt.Point;
import java.util.ArrayList;

import board.Board;
import board.Direction;



/** Representa una caja que sera contenida en una celda
 * 
 * @author santi698
 *
 */
public class Box extends CellContent {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Box";
	}

	@Override
	public ArrayList<Point> move(Board board, Direction direction) {
		ArrayList<Point> changed = new ArrayList<Point>();
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		if (!(nextCell instanceof ContainerCell) || !((ContainerCell)nextCell).isEmpty())
			return changed;
		changed.add(position);
		changed.add(new Point(position.x + direction.x, position.y + direction.y));
		board.getCell(position.x, position.y).setContent(null);
		if(nextCell instanceof Water)
			board.setCell(position.x + direction.x, position.y + direction.y, new FloatingBox());
		else {
			nextCell.setContent(this);
			this.setPosition(new Point(position.x + direction.x, position.y + direction.y));
		}
		return changed;
	}
}
