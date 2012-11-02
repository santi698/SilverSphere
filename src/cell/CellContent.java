package cell;

import java.awt.Point;
import java.io.Serializable;

import com.sun.xml.internal.ws.api.model.SEIModel;

import board.Board;
import board.Direction;


/**
 * Clase que representa el contenido de una celda del tablero del juego.
 * @author santi698
 *
 */
public abstract class CellContent implements Serializable{
	public Point position = null;
	public abstract MoveReturnValue move(Board board, Direction direction);

	public void setPosition(Point point) {
		position = point;
	}
}