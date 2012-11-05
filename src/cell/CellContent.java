package cell;

import java.awt.Point;
import java.io.Serializable;

import board.Board;
import board.Direction;


/**
 * Clase que representa el contenido de una celda del tablero del juego.
 * @author santi698
 *
 */
public abstract class CellContent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public Point position;
	
	public abstract MoveReturnValue move(Board board, Direction direction);
	
	public void setPosition(Point point) {
		position = point;
	}
}