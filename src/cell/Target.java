package cell;

import java.awt.Point;

public class Target extends ContainerCell {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible;
	private Point position;
	
	public Point getPosition() {
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
	public void setPosition(Point point) {

		position = point;
	}

}
