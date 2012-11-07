package board;

import java.io.Serializable;


public class Position implements Serializable {
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position next(Direction d) {
		return new Position(x + d.x, y + d.y);
	}
}
