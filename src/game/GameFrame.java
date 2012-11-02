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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import board.Board;
import board.Direction;
import board.InvalidLevelException;
import cell.Cell;
import cell.ContainerCell;
import cell.MoveReturnValue;
import cell.Target;

public class GameFrame extends JFrame {
	static {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}
	private static final long serialVersionUID = 1L;
	private static final Dimension INITIAL_SIZE = new Dimension(150, 120);
	private static final int CELL_SIZE = 30;
	
	Board board;
	
	JPanel menuPanel = new JPanel();
	JButton newGameButton = new JButton("New Game");
	JButton loadGameButton = new JButton("Load Game");
	JButton exitButton = new JButton("Exit");
		
	JPanel gameMenuPanel = new JPanel();
	JButton backToMenuButton = new JButton("Back");
	JButton saveGameButton = new JButton("Save");
	
	BoardPanel boardPanel;

	File actualLevelFile = null;
	
	GameFrame() {
		super("SilverSphere");
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				respondToKeyEvent(e);

			}
		});

		
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actualLevelFile = askForFile("resources/levels");
				if (actualLevelFile != null)
					startGame();
				else
					returnToMenu();
			}
		});
		
		loadGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = askForFile("saved");
				if (f != null)
				try {
					loadGame(f);
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
		backToMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		saveGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = askForFile("saved");
				if (f != null)
				try {
					saveGame(f);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		});
		
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		newGameButton.setAlignmentX(CENTER_ALIGNMENT);
		menuPanel.add(newGameButton);
		
		menuPanel.add(Box.createVerticalGlue());
		loadGameButton.setAlignmentX(CENTER_ALIGNMENT);
		menuPanel.add(loadGameButton);
		menuPanel.add(Box.createVerticalGlue());
		exitButton.setAlignmentX(CENTER_ALIGNMENT);
		menuPanel.add(exitButton);
		
		gameMenuPanel.setLayout(new BoxLayout(gameMenuPanel, BoxLayout.X_AXIS));
		gameMenuPanel.add(backToMenuButton);
		gameMenuPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		gameMenuPanel.add(saveGameButton);
		gameMenuPanel.add(Box.createHorizontalGlue());
		gameMenuPanel.setVisible(false);
		
		Container contentPane = getContentPane();
		contentPane.add(menuPanel, BorderLayout.CENTER);
		contentPane.add(gameMenuPanel, BorderLayout.NORTH);
		
//		setResizable(false);
		setSize(INITIAL_SIZE);
		center();
		setVisible(true);
		setFocusable(true);
	}
	
	protected void saveGame(File f) throws IOException {
		ObjectOutputStream outStream = null;
		if (f != null) {
			try {
				outStream = new ObjectOutputStream(new FileOutputStream(f));
				outStream.writeObject(board);
				
			} catch (StreamCorruptedException e1) {
				JOptionPane.showMessageDialog(this, "El archivo está mal formado", 
						"Error al cargar el juego", JOptionPane.ERROR_MESSAGE);
			}
			finally {
				if (outStream != null)
					outStream.close();
			}
		}

	}
	

	protected void returnToMenu() {
		gameMenuPanel.setVisible(false);
		boardPanel.setVisible(false);
		menuPanel.setVisible(true);
		setSize(INITIAL_SIZE);
		}

	protected void respondToKeyEvent(KeyEvent e) {
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
						setCellImages(board, boardPanel);
						if (returnValue == MoveReturnValue.WATER_REACHED) {
							JOptionPane.showMessageDialog(this, "Has perdido, el jugador cayó al agua");
							boardPanel.setVisible(false);
							startGame();
						}
						if (returnValue == MoveReturnValue.TARGET_REACHED) {
							JOptionPane.showMessageDialog(this, "Has ganado!");
							boardPanel.setVisible(false);
							startGame();
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
	}

	void startGame() {
		try {
			board = loadLevelFromFile(actualLevelFile);
			boardPanel = new BoardPanel(board.rows, board.columns, CELL_SIZE);
			boardPanel.setBackground(Color.WHITE);
			setCellImages(board, boardPanel);
			add(boardPanel);
			setSize(boardPanel.getWidth(), boardPanel.getHeight() + 45);
			center();
			menuPanel.setVisible(false);
			boardPanel.setVisible(true);
			gameMenuPanel.setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InvalidLevelException e1) {
			System.err.println(e1.getMessage());
			JOptionPane.showMessageDialog(this,
					e1.getMessage(), "Error al cargar el nivel", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}

	void loadGame(File f) throws IOException {
		ObjectInputStream inStream = null;
		try {
			inStream = new ObjectInputStream(new FileInputStream(f));
			board = (Board) inStream.readObject();
			boardPanel = new BoardPanel(board.rows, board.columns, CELL_SIZE);
			setCellImages(board, boardPanel);
			menuPanel.setVisible(false);
			gameMenuPanel.setVisible(true);
			add(boardPanel, BorderLayout.CENTER);
			setSize(boardPanel.getWidth(), boardPanel.getHeight() + 20);
			center();
			
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

	public static void setCellImages (Board board, BoardPanel boardPanel) throws IOException{
		for (int i = 0; i < board.rows; i++) {
			for (int j = 0; j < board.columns; j++) {
				Cell c = board.getCell(j, i);
				boardPanel.setImage(i, j, ImageUtils.loadImage("./resources/images/cell.png"));
				if (!(c instanceof Target && !((Target) c).isVisible()))
					boardPanel.appendImage(i, j, GameImages.cellImages.get(c.getClass()));
				if (c instanceof ContainerCell && c.getContent() != null)
					boardPanel.appendImage(i, j, GameImages.cellContentImages.get(c.getContent().getClass()));
			}
		}
		boardPanel.repaint();
	}
	public void center() {
		Toolkit t = getToolkit();
		Dimension d = t.getScreenSize();
		setLocation((d.width - getWidth())/2,(d.height-getHeight())/2);
	}

}
