package cell;

import java.awt.Point;

import board.Board;
import board.Direction;


/**
 * Clase que representa el contenido de una celda del tablero del juego.
 * @author santi698
 *
 */
public abstract class CellContent {
	public Point position = null;
	public abstract boolean move(Board board, Direction direction);

	public void setPosition(Point point) {
		position = point;
	}
}