package cell;

import java.awt.Point;

import board.Board;
import board.Direction;

public class Character extends CellContent {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Character";
	}

	public MoveReturnValue move(Board board, Direction direction) {
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		if (nextCell instanceof ContainerCell) {
			if (!((ContainerCell)nextCell).isEmpty() && 
					(((ContainerCell)nextCell).getContent().move(board, direction)
							== MoveReturnValue.UNABLE_TO_MOVE))
				return MoveReturnValue.UNABLE_TO_MOVE;
			else {
				board.getCell(position.x, position.y).setContent(null);
				nextCell.setContent(this);
				this.setPosition(new Point(position.x + direction.x, position.y + direction.y));
				if (nextCell instanceof Water)
					return MoveReturnValue.WATER_REACHED;
				if (nextCell instanceof Target && ((Target)nextCell).isVisible())
					return MoveReturnValue.TARGET_REACHED;
				return MoveReturnValue.MOVED;
			}
		}
		else
			return MoveReturnValue.UNABLE_TO_MOVE;

	}
}
