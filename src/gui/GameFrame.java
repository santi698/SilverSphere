package gui;


import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 30;

	private BoardPanel bp;
	
	public GameFrame(BoardPanel bp) {
		this.bp=bp;
		setLayout(null);
		setSize(bp.getWidth() * CELL_SIZE + 40, bp.getHeight() * CELL_SIZE + 40);		
		bp.setBackground(Color.WHITE);
		add(bp);
	}
}
