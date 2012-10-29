package gui;

import java.awt.Image;

import javax.swing.JFrame;

import board.Board;

import cell.Cell;
import cell.Direction;

import sample.SampleFrame;

public class App {

	/**
	 * @param args
	 */
	public static void setCellContents (Board board, gui.BoardPanel panel) {
		for (int i = 0; i < panel.getBounds().height; i++) {
			for (int j = 0; j < panel.getBounds().width; j++) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SampleFrame frame = new SampleFrame();
		gui.BoardPanel panel = new gui.BoardPanel(10, 10, 5);
		Image image = null;
		panel.setImage(0, 0, image);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	

}
