package cell;

import java.awt.Image;

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
	 * es Movable o hasta alcanzar uno de tipo Water
	 */
	
	public MoveReturnValue move(Board board, Direction direction) {
		// TODO Auto-generated method stub
		return MoveReturnValue.UNABLE_TO_MOVE;
	}
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	public String toString() {
		return "Ice Block";
	}

}
