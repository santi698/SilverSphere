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
		
		ArrayList<Position> changed = new ArrayList<Position>();
		Position nextPos = position.next(direction);
		Cell nextCell = board.getCell(nextPos);

		if (nextCell instanceof ContainerCell) {
			ContainerCell nextContainer = (ContainerCell) nextCell;
			ArrayList<Position> nextCellChanged = null;
			
			if (!nextContainer.isEmpty()) {
				nextCellChanged = nextContainer.getContent().move(board, direction);
				changed.addAll(nextCellChanged);
			}
			
			if (nextCellChanged != null &&
					nextCellChanged.isEmpty())
				return changed;
			else {
				changed.add(position);
				changed.add(nextPos);
				board.getCell(position).setContent(null);
				nextCell.setContent(this);
				this.setPosition(nextPos);
				if (nextCell instanceof Water || 
						(nextCell instanceof Target && ((Target)nextCell).isVisible()))
					return changed;
			}
		}
		
		return changed;

	}
}
