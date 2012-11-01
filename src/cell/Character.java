package cell;

import java.awt.Point;

import board.Board;
import board.Direction;


public class Character extends CellContent {
	private Point position;
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Character";
	}

	public boolean move(Board board, Direction direction) {
		// TODO Auto-generated method stub
		return false;
		
	}

}
