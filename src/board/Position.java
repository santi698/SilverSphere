package board;

import java.awt.Point;

public class Position extends Point {
	private static final long serialVersionUID = 1L;

	public Position(int x, int y) {
		super(x, y);
	}

	public Position next(Direction d) {
		return new Position(x + d.x, y + d.y);
	}
}
