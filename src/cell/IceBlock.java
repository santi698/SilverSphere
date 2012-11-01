package cell;

import java.awt.Image;
import java.awt.Point;

import board.Board;
import board.Direction;

/**
 * Clase que representa un cubo de hielo
 * @author santi698
 *
 */
public class IceBlock extends CellContent {
	/**
	 * Mueve el cubo de hielo
	 * El mismo se debe mover hasta alcanzar un objeto que no 
	 * es contenedor o hasta alcanzar uno de tipo Water
	 */
	
	public MoveReturnValue move(Board board, Direction direction) {
		Cell nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		if(!(nextCell instanceof ContainerCell) || !nextCell.isEmpty())
			return MoveReturnValue.UNABLE_TO_MOVE;
		while(nextCell instanceof ContainerCell && nextCell.isEmpty()) {
			if (nextCell instanceof Water) {
				board.getCell(position.x, position.y).setContent(null);
				return MoveReturnValue.MOVED;
			}
			if (nextCell instanceof IceBlockTarget) {
				board.setTargetVisible();
			}
			board.getCell(position.x, position.y).setContent(null);
			nextCell.setContent(this);
			setPosition(new Point(position.x + direction.x, position.y + direction.y));
			nextCell = board.getCell(position.x + direction.x, position.y + direction.y);
		}
		return MoveReturnValue.MOVED;
	}
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	public String toString() {
		return "Ice Block";
	}

}
