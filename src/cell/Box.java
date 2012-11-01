package cell;

import java.awt.Point;

import board.Board;
import board.Direction;



/** Representa una caja que sera contenida en una celda
 * 
 * @author santi698
 *
 */
public class Box extends CellContent {
		
	@Override
	public String toString() {
		return "Box";
	}

	@Override
	public MoveReturnValue move(Board board, Direction direction) {
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		if (!(nextCell instanceof ContainerCell) || !((ContainerCell)nextCell).isEmpty())
			return MoveReturnValue.UNABLE_TO_MOVE;
		board.getCell(position.x, position.y).setContent(null);
		if(nextCell instanceof Water)
			board.setCell(position.x + direction.x, position.y + direction.y, new FloatingBox());
		else {
			nextCell.setContent(this);
			this.setPosition(new Point(position.x + direction.x, position.y + direction.y));
		}
		return MoveReturnValue.MOVED;
	}
}
