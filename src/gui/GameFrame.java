package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 30;

	private BoardPanel bp;
	
	public GameFrame(BoardPanel bp) {
		this.bp=bp;
		setLayout(null);
		Toolkit t = getToolkit();
		setSize(bp.getWidth(), bp.getHeight() + 20);
		setResizable(false);
		bp.setBackground(Color.WHITE);
		add(bp, BorderLayout.NORTH);
	}
}
