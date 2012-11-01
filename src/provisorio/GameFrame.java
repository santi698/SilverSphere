package provisorio;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

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
				try {
					loadGameButton_actionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		setSizeAndCenter(300, 500);
		setVisible(true);
	}
	
	void newGameButton_actionPerformed(ActionEvent e) {
		File f = askForFile("./resources/levels");
		try {
			board = loadLevelFromFile(f);
			remove(menuPanel);
			boardPanel = new BoardPanel(board.rows, board.columns, CELL_SIZE);
			setCellContents(board, boardPanel);
			boardPanel.setBackground(Color.WHITE);
			boardPanel.setVisible(true);
			add(boardPanel);
			setSizeAndCenter(boardPanel.getWidth(), boardPanel.getHeight() + 20);
			repaint();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InvalidLevelException e1) {
			System.err.println(e1.getMessage());
			JOptionPane.showMessageDialog(this,
					e1.getMessage(), "Error al cargar el nivel", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
	private void setSizeAndCenter(int width, int height) {
		super.setSize(width, height);
		Toolkit t = getToolkit();
		Dimension d = t.getScreenSize();
		setLocation((d.width - getWidth())/2,(d.height-getHeight())/2);		

	}

	void loadGameButton_actionPerformed(ActionEvent e) throws IOException {
		File f = askForFile("./saved");
		ObjectInputStream inStream = null;
		try {
			inStream = new ObjectInputStream(new FileInputStream(f));
			board = (Board) inStream.readObject();
			boardPanel = (BoardPanel) inStream.readObject();
			remove(menuPanel);
			add(boardPanel);
			setSizeAndCenter(boardPanel.getWidth(), boardPanel.getHeight() + 20);
			repaint();
			
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "El archivo está mal formado", 
					"Error al cargar el juego", JOptionPane.ERROR_MESSAGE);
		} catch (StreamCorruptedException e1) {
			JOptionPane.showMessageDialog(this, "El archivo está mal formado", 
					"Error al cargar el juego", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			if (inStream != null)
				inStream.close();
		}
	}
	File askForFile(String path) {
		JFileChooser chooser = new JFileChooser(path);
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
