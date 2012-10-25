package sample;

import java.awt.Image;

import javax.swing.JFrame;

public class SampleApp {

	public static void main(String[] args) {
		SampleFrame frame = new SampleFrame();
		gui.BoardPanel panel = new gui.BoardPanel(10, 10, 5);
		Image image = null;
		panel.setImage(0, 0, image);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
