package gui;

import java.awt.Color;
import java.awt.Image;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import board.Board;
import board.InvalidLevelException;

import cell.*;

import sample.SampleFrame;

public class App {

	/**
	 * @param args
	 */
	public static void setCellContents (Board board, gui.BoardPanel panel) throws IOException{
		for (int i = 0; i < board.rows; i++) {
			for (int j = 0; j < board.columns; j++) {
				Cell c = board.getCell(i, j);
				panel.setImage(i, j, cellToImage(c));
				if (c instanceof ContainerCell)
					panel.appendImage(i, j, cellContentToImage(c.getContent()));
			}
		}
	}
	private static Image cellContentToImage(CellContent content) throws IOException {
		if (content instanceof IceBlock) {
			return ImageUtils.loadImage("./resources/images/ice-box.png");
		}
		if (content instanceof Box) {
			return ImageUtils.colorize(ImageUtils.loadImage("./resources/images/box.png"),new Color(171, 136, 51));
		}
		else
			return ImageUtils.loadImage("./resources/images/player.png");
	}
	private static Image cellToImage(Cell c) throws IOException {
		
		if (c instanceof EmptyCell) {
			return ImageUtils.loadImage("./resources/images/cell.png");
		}
		if (c instanceof Destino) {
			return ImageUtils.loadImage("./resources/images/target.png");
		}
		if (c instanceof Interruptor) {
			return ImageUtils.loadImage("./resources/images/ice-box-target.png");
		}
		if (c instanceof Water) {
			return ImageUtils.loadImage("./resources/images/water.png");
		}
		if (c instanceof Tree) {
			return ImageUtils.loadImage("./resources/images/tree.png");
		}
		else
			return ImageUtils.colorize(ImageUtils.loadImage("./resources/images/box.png"), new Color(128, 178, 191));
	}
	private static String [] readFileToStringArray (String file) throws IOException{
		FileReader inStream = null;
		char[] cbuf = new char[250];
		try {
			inStream = new FileReader (file);
			inStream.read(cbuf);
		} 
		finally {
			if (inStream != null) {
				inStream.close();
			}
		}
		String[] sArr = (new String(cbuf)).split("\n"); //WTF pone como ultimo string del arreglo al original completo o.O
		return sArr;
	}

	
	public static void main(String[] args) throws IOException {
		gui.BoardPanel panel = new gui.BoardPanel(20, 20, 5);
		String [] s = readFileToStringArray("./resources/levels/v01.txt");
		try {
			Board b = new Board(s);
			setCellContents(b, panel);
		} catch (InvalidLevelException e) {
			System.out.println(e.getMessage());
		}
		SampleFrame frame = new SampleFrame();
		Image image = null;
		panel.setImage(0, 0, image);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	

}
