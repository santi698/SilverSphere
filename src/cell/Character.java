package cell;

import java.util.ArrayList;

import board.Board;
import board.Direction;
import board.Position;

public class Character extends CellContent {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Character";
	}

	public ArrayList<Position> move(Board board, Direction direction) {
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		ArrayList<Position> changed = new ArrayList<Position>();
		if (nextCell instanceof ContainerCell) {
			ContainerCell nextCellAsContainer = (ContainerCell) nextCell;
			ArrayList<Position> nextCellChanged = null;
			if (!nextCellAsContainer.isEmpty()) {
				nextCellChanged = nextCellAsContainer.getContent().move(board, direction);
				changed.addAll(nextCellChanged);
			}
			if (nextCellChanged != null &&
					nextCellChanged.isEmpty())
				return changed;
			else {
				changed.add(position);
				changed.add(position.next(direction));
				board.getCell(position.x, position.y).setContent(null);
				nextCell.setContent(this);
				this.setPosition(position.next(direction));
				if (nextCell instanceof Water || 
						(nextCell instanceof Target && ((Target)nextCell).isVisible()))
					return changed;
			}
		}
		return changed;

	}
}
