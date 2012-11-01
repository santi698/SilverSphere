package game;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import board.Board;
import board.Direction;
import board.InvalidLevelException;
import cell.Cell;
import cell.ContainerCell;
import cell.MoveReturnValue;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 30;
		
	Board board;
	
	MenuPanel menuPanel = new MenuPanel();
	JButton newGameButton = new JButton("New Game");
	JButton loadGameButton = new JButton("Load Game");
	JButton exitButton = new JButton("Exit");
		
	GameMenuPanel gameMenuPanel = new GameMenuPanel();
	JButton backToMenuButton = new JButton("Back");
	JButton saveGameButton = new JButton("Save");
	
	BoardPanel boardPanel;

	File actualLevelFile = null;
	
	GameFrame() {
		super("SilverSphere");
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				game_keyPressed(e);

			}
		});

		
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actualLevelFile = askForFile("resources/levels");
				newGameButton_actionPerformed(e);
			}
		});
		
		loadGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loadGameButton_actionPerformed(e);
				} catch (IOException e1) {
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
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.add(newGameButton);
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.add(loadGameButton);
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.add(exitButton);
		
//		gameMenuPanel.setLayout(new BoxLayout(gameMenuPanel, BoxLayout.X_AXIS));
//		gameMenuPanel.add(backToMenuButton);
//		gameMenuPanel.add(Box.createRigidArea(new Dimension(5, 0)));
//		gameMenuPanel.add(saveGameButton);
//		gameMenuPanel.add(Box.createHorizontalGlue());
//		gameMenuPanel.setVisible(false);
		
		Container contentPane = getContentPane();
		contentPane.add(menuPanel, BorderLayout.CENTER);
//		contentPane.add(gameMenuPanel, BorderLayout.NORTH);
		
//		setResizable(false);
		setVisible(true);
	}
	
	protected void game_keyPressed(KeyEvent e) {
		{
			if (boardPanel.isVisible()) {
				Direction direction = null;
				switch(e.getKeyCode()) {
				case KeyEvent.VK_UP: direction = Direction.UP; break;
				case KeyEvent.VK_DOWN: direction = Direction.DOWN; break;
				case KeyEvent.VK_RIGHT: direction = Direction.RIGHT; break;
				case KeyEvent.VK_LEFT: direction = Direction.LEFT; break;
				}
				if (direction != null) {
					MoveReturnValue returnValue = board.moveCharacter(direction);
					if (returnValue != MoveReturnValue.UNABLE_TO_MOVE)
					try {
						setCellContents(board, boardPanel);
						repaint();
						if (returnValue == MoveReturnValue.WATER_REACHED) {
							JOptionPane.showMessageDialog(this, "Has perdido, el jugador cayó al agua");
							boardPanel.setVisible(false);
							newGameButton_actionPerformed(null);
						}
						if (returnValue == MoveReturnValue.TARGET_REACHED)
							JOptionPane.showMessageDialog(this, "Has ganado!");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
	}

	void newGameButton_actionPerformed(ActionEvent e) {
		try {
			board = loadLevelFromFile(actualLevelFile);
			boardPanel = new BoardPanel(board.rows, board.columns, CELL_SIZE);
			setCellContents(board, boardPanel);
			boardPanel.setBackground(Color.WHITE);
			add(boardPanel);
			resizeAndCenter(boardPanel.getWidth(), boardPanel.getHeight() + 45);
			boardPanel.repaint();
			menuPanel.setVisible(false);
			gameMenuPanel.setVisible(true);
			boardPanel.setVisible(true);
			setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InvalidLevelException e1) {
			System.err.println(e1.getMessage());
			JOptionPane.showMessageDialog(this,
					e1.getMessage(), "Error al cargar el nivel", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
	private void resizeAndCenter(int width, int height) {
//		setResizable(true);
		setSize(width, height);
		Toolkit t = getToolkit();
		Dimension d = t.getScreenSize();
		setLocation((d.width - getWidth())/2,(d.height-getHeight())/2);		
//		setResizable(false);
	}

	void loadGameButton_actionPerformed(ActionEvent e) throws IOException {
		File f = askForFile("saved");
		ObjectInputStream inStream = null;
		try {
			inStream = new ObjectInputStream(new FileInputStream(f));
			board = (Board) inStream.readObject();
			boardPanel = (BoardPanel) inStream.readObject();
			menuPanel.setVisible(false);
			add(boardPanel, BorderLayout.CENTER);
			resizeAndCenter(boardPanel.getWidth(), boardPanel.getHeight() + 20);
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
		char[] cBuf = new char[500];
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
				Cell c = board.getCell(j, i);
				panel.setImage(i, j, ImageUtils.loadImage("./resources/images/cell.png"));
				panel.appendImage(i, j, GameImages.cellImages.get(c.getClass()));
				if (c instanceof ContainerCell && c.getContent() != null)
					panel.appendImage(i, j, GameImages.cellContentImages.get(c.getContent().getClass()));
			}
		}
	}

}
