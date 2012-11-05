package cell;

import java.awt.Point;
import java.util.ArrayList;

import board.Board;
import board.Direction;

public class Character extends CellContent {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Character";
	}

	public ArrayList<Point> move(Board board, Direction direction) {
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		ArrayList<Point> changed = new ArrayList<Point>();
		if (nextCell instanceof ContainerCell) {
			ContainerCell nextCellAsContainer = (ContainerCell) nextCell;
			ArrayList<Point> nextCellChanged = null;
			if (!nextCellAsContainer.isEmpty()) {
				nextCellChanged = nextCellAsContainer.getContent().move(board, direction);
				changed.addAll(nextCellChanged);
			}
			if (nextCellChanged != null &&
					nextCellChanged.isEmpty())
				return changed;
			else {
				changed.add(position);
				changed.add(new Point(position.x + direction.x, position.y + direction.y));
				board.getCell(position.x, position.y).setContent(null);
				nextCell.setContent(this);
				this.setPosition(new Point(position.x + direction.x, position.y + direction.y));
				if (nextCell instanceof Water || 
						(nextCell instanceof Target && ((Target)nextCell).isVisible()))
					return changed;
			}
		}
		return changed;

	}
}
