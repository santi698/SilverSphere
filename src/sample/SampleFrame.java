package sample;

import gui.BoardPanel;
import gui.ImageUtils;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;

public class SampleFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 30;

	private BoardPanel bp;
	private Image img1, img2, img3, img4;

	public SampleFrame() {
		setLayout(null);
		setSize(10 * CELL_SIZE + 40, 10 * CELL_SIZE + 40);

		try {
			img1 = ImageUtils.loadImage("resources/box.png");
			img2 = ImageUtils.colorize(img1, new Color(128, 178, 191));
			img3 = ImageUtils.colorize(img1, new Color(171, 136, 51)); 
			img4 = ImageUtils.loadImage("resources/player.png");
		} catch (IOException e) {
			System.out.println("Error al cargar imagenes.");
		}
		
		bp = new BoardPanel(10, 10, CELL_SIZE);
		bp.setBackground(Color.WHITE);
		add(bp);
		
		bp.setImage(4, 4, img2);
		bp.setImage(6, 4, img3);
		bp.appendImage(4,  4, img4);
	}
}
