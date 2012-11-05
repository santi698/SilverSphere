package cell;

import java.awt.Point;
import java.util.ArrayList;

import board.Board;
import board.Direction;

/**
 * Clase que representa un cubo de hielo
 * @author santi698
 *
 */
public class IceBlock extends CellContent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Mueve el cubo de hielo. 
	 * El mismo se debe mover hasta alcanzar una celda que no 
	 * es {@link ContainerCell} o hasta alcanzar una de tipo {@link Water}
	 * @param board
	 * @param direction
	 * @return El resultado del movimiento 
	 * @see {@link MoveReturnValue}
	 */
	//TODO REVISAR!
	public ArrayList<Point> move(Board board, Direction direction) {
		ArrayList<Point> changed = new ArrayList<Point>();
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		if(!(nextCell instanceof ContainerCell) || !nextCell.isEmpty())
			return changed;
		changed.add(position);
		while(nextCell instanceof ContainerCell && nextCell.isEmpty()) {
			if (nextCell instanceof Water) {
				board.getCell(position.x, position.y).setContent(null);
				return changed;
			}
			board.getCell(position.x, position.y).setContent(null);
			nextCell.setContent(this);
			setPosition(new Point(position.x + direction.x, position.y + direction.y));
			nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		}
		if (board.getCell(position.x, position.y) instanceof IceBlockTarget) {
			((Target)board.getTargetCell()).setVisible();
//			TODO changed.add(board.getTargetCell().getPosition());
		}
		changed.add(position);
		return changed;
	}
	/**
	 * Para debugging
	 */
	public String toString() {
		return "Ice Block";
	}

}
