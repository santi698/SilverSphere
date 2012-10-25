package cell;

import java.awt.Image;
import java.io.IOException;

import gui.ImageUtils;

/** Representa una caja que sera contenida en una celda
 * 
 * @author santi698
 *
 */
public class Box extends CellContent implements Movable {
	static {
		try {
			image = ImageUtils.loadImage("resources/box.png");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
	private static Image image;
	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}
	public Image getImage() {
		return image;
	}

}
