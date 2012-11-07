package cell;

import java.util.ArrayList;

import board.Board;
import board.Direction;
import board.Position;

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
	 * es {@link ContainerCell} o hasta alcanzar una de tipo {@link Water}. El cubo de hielo se frena
	 * o se "derrite" (desaparece), respectivamente.
	 * @param board
	 * @param direction
	 * @return una lista de las celdas modificadas como resultado de movimiento
	 * @see {@link MoveReturnValue}
	 */
	
	//TODO REVISAR!
	public ArrayList<Position> move(Board board, Direction direction) {
		
		ArrayList<Position> changed = new ArrayList<Position>();
		Position nextPos = position.next(direction);
		Cell nextCell = board.getCell(nextPos);
		
		if(!(nextCell instanceof ContainerCell) || !nextCell.isEmpty())
			return changed;
		
		changed.add(position);
		while(nextCell instanceof ContainerCell && nextCell.isEmpty()) {
			if (nextCell instanceof Water) {
				board.getCell(position).setContent(null);
				return changed;
			}
			
			board.getCell(position).setContent(null);
			nextCell.setContent(this);
			setPosition(nextPos);
			nextPos = position.next(direction);
			nextCell = board.getCell(nextPos);
		}
		
		if (board.getCell(position) instanceof IceBlockTarget) {
			board.getTargetCell().setVisible();
			changed.add(board.getTargetCell().getPosition());
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
