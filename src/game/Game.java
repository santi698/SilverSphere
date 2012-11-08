package game;

import javax.swing.JFrame;

/**
 * 
 *
 */
public class Game {
	
	/**
	 * Metodo que carga el cuadro principal del juego
	 */
	public void run() {
		GameFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
