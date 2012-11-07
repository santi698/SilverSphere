package cell;

import java.io.Serializable;
import java.util.ArrayList;

import board.Board;
import board.Direction;
import board.Position;


/**
 * Clase que representa el contenido de una celda del tablero del juego.
 * @author santi698
 *
 */
public abstract class CellContent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public Position position;
	
	public abstract ArrayList<Position> move(Board board, Direction direction);
	
	public void setPosition(Position point) {
		position = point;
	}
}