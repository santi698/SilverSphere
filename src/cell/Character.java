package cell;

import java.awt.Point;

import board.Board;
import board.Direction;


public class Character extends CellContent {

	@Override
	public String toString() {
		return "Character";
	}

	public boolean move(Board board, Direction direction) {
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		if (nextCell instanceof ContainerCell) {
			if (!((ContainerCell)nextCell).isEmpty() && 
					!((ContainerCell)nextCell).getContent().move(board, direction))
				return false;
			else {
				board.getCell(position.x, position.y).setContent(null);
				nextCell.setContent(this);
				this.setPosition(new Point(position.x+direction.x, position.y + direction.y));
				System.out.println("true");
				return true;
			}
		}
		else
			return false;

	}
}
