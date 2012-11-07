package cell;

import java.util.ArrayList;

import board.Board;
import board.Direction;
import board.Position;



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
	public ArrayList<Position> move(Board board, Direction direction) {
		ArrayList<Position> changed = new ArrayList<Position>();
		Cell nextCell = board.getCell(position.next(direction));
		if (!(nextCell instanceof ContainerCell) || !((ContainerCell)nextCell).isEmpty())
			return changed;
		changed.add(position);
		changed.add(position.next(direction));
		board.getCell(position.x, position.y).setContent(null);
		if(nextCell instanceof Water)
			board.setCell(position.next(direction), new FloatingBox());
		else {
			nextCell.setContent(this);
			this.setPosition(position.next(direction));
		}
		return changed;
	}
}
