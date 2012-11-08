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

	/**
	 * m�todo que valida y ejecuta el movimiento del objeto.
	 * @param board
	 * @param direction
	 * @return ArrayList<Position> 
	 */
	@Override
	public ArrayList<Position> move(Board board, Direction direction) {
		
		ArrayList<Position> changed = new ArrayList<Position>();
		Position nextPos = position.next(direction);
		Cell nextCell = board.getCell(nextPos);
		if (!(nextCell instanceof ContainerCell) || !((ContainerCell)nextCell).isEmpty())
			return changed;
		changed.add(position);
		changed.add(nextPos);
		((ContainerCell)board.getCell(position.x, position.y)).setContent(null);
		if(nextCell instanceof Water)
			board.setCell(nextPos, new FloatingBox());
		else {
			((ContainerCell)nextCell).setContent(this);
			this.setPosition(nextPos);
		}
		return changed;
	}
}
