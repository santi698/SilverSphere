package provisorio;


import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cell.Box;
import cell.Cell;
import cell.CellContent;
import cell.Character;
import cell.Destino;
import cell.EmptyCell;
import cell.FloatingBox;
import cell.IceBlock;
import cell.Interruptor;
import cell.Tree;
import cell.Water;

public class GameImages {
	public static Map<Class<? extends Cell>, Image> cellImages;
	public static Map<Class<? extends CellContent>, Image> cellContentImages;
	static {
		cellImages = new HashMap<Class<? extends Cell>, Image>();
		cellContentImages = new HashMap<Class<? extends CellContent>, Image>();
		try {
			cellImages.put(EmptyCell.class, ImageUtils.loadImage("./resources/images/cell.png"));
			cellImages.put(Tree.class, ImageUtils.loadImage("./resources/images/tree.png"));
			cellImages.put(Destino.class, ImageUtils.loadImage("./resources/images/target.png"));
			cellImages.put(Water.class, ImageUtils.loadImage("./resources/images/water.png"));
			cellImages.put(Interruptor.class, ImageUtils.loadImage("./resources/images/ice-box-target.png"));
			cellImages.put(FloatingBox.class, ImageUtils.loadImage("./resources/images/box.png"));
			cellContentImages.put(IceBlock.class, ImageUtils.loadImage("./resources/images/ice-box.png"));
			cellContentImages.put(Character.class, ImageUtils.loadImage("./resources/images/player.png"));
			cellContentImages.put(Box.class, ImageUtils.loadImage("./resources/images/box.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
