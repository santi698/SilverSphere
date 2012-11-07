package cell;

import board.Position;

public class Target extends ContainerCell {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible;
	private Position position;
	
	public Position getPosition() {
		return position;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible() {
		visible = true;
		
	}
	public String toString() {
		return "Destino + " + this.getContent();
	}
	public void setPosition(Position pos) {

		position = pos;
	}

}
