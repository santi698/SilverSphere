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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
