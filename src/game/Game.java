package game;

import javax.swing.JFrame;

/**
 * 
 *
 */
public class Game {
	
	public void run() {
		GameFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
