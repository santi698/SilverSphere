package provisorio;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import board.Board;
import board.InvalidLevelException;
import cell.Cell;
import cell.ContainerCell;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 30;
	
	BoardPanel boardPanel;
	Board board;
	MenuPanel menuPanel = new MenuPanel();
	JButton newGameButton = new JButton("New Game");
	JButton loadGameButton = new JButton("Load Game");
	JButton exitButton = new JButton("Exit");
	GameFrame() {
		super("SilverSphere");
		
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newGameButton_actionPerformed(e);
			}
		});
		
		loadGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadGameButton_actionPerformed(e);
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		menuPanel.add(newGameButton);
		menuPanel.add(loadGameButton);
		menuPanel.add(exitButton);
		add(menuPanel);
		setSize(300, 500);
		center();
		setVisible(true);
	}
	
	private void center() {
		Toolkit t = getToolkit();
		Dimension d = t.getScreenSize();
		setLocation((d.width - getWidth())/2,(d.height-getHeight())/2);		
	}

	void newGameButton_actionPerformed(ActionEvent e) {
		File f = askForFile();
		try {
			board = loadLevelFromFile(f);
			remove(menuPanel);
			boardPanel = new BoardPanel(board.rows, board.columns, CELL_SIZE);
			setCellContents(board, boardPanel);
			boardPanel.setBackground(Color.WHITE);
			boardPanel.setVisible(true);
			add(boardPanel);
			setSize(boardPanel.getWidth(), boardPanel.getHeight() + 20);
			center();
			repaint();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InvalidLevelException e1) {
			System.err.println(e1.getMessage());
			JOptionPane.showMessageDialog(this,
					e1.getMessage(), "Error al cargar el nivel", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
	void loadGameButton_actionPerformed(ActionEvent e) {
		//TODO
		//File f = askForFile();
	}
	File askForFile() {
		JFileChooser chooser = new JFileChooser("./resources/levels");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();
		return null;
	}
	
	Board loadLevelFromFile(File f) throws IOException, InvalidLevelException {
		char[] cBuf = new char[250];
		String [] sArr;
		BufferedReader reader = null;
		try { 
			
		reader = new BufferedReader(new FileReader(f));
		reader.read(cBuf);
		sArr = new String(cBuf).split("\n");
		}
		finally {
			if (reader != null)
				reader.close();
		}
		return new Board(sArr);
	}

	public static void setCellContents (Board board, BoardPanel panel) throws IOException{
		for (int i = 0; i < board.rows; i++) {
			for (int j = 0; j < board.columns; j++) {
				Cell c = board.getCell(i, j);
				panel.setImage(i, j, ImageUtils.loadImage("./resources/images/cell.png"));
				panel.appendImage(i, j, GameImages.cellImages.get(c.getClass()));
				if (c instanceof ContainerCell && c.getContent() != null)
					panel.appendImage(i, j, GameImages.cellContentImages.get(c.getContent().getClass()));
			}
		}
	}

}
