package board;
/**
 * Enumerativo para agrupar los tipos de movimientos posibles
 * @author santi698
 *
 */
public enum Direction {
	LEFT(-1, 0),
	UP(0, -1),
	RIGHT(1, 0),
	DOWN(0, 1);
	public final int x;
	public final int y;
	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
