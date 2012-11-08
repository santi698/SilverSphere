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
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

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
import board.GameFileManager;
import board.InvalidLevelException;
import board.MoveRes;
import board.Position;
import cell.Cell;
import cell.ContainerCell;
import cell.Target;

/**
 * 
 *
 */
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
	
	private JPanel menuPanel = new JPanel();
	private JButton newGameButton = new JButton("New Game");
	private JButton loadGameButton = new JButton("Load Game");
	private JButton exitButton = new JButton("Exit");
		
	private JPanel gameMenuPanel = new JPanel();
	private JButton backToMenuButton = new JButton("Back");
	private JButton saveGameButton = new JButton("Save");
	private JButton restartGameButton = new JButton("Restart Level");
	
	private BoardPanel boardPanel;
	private Board board;

	private File actualLevelFile = null;
	private GameFileManager fm = new GameFileManager();
	
	GameFrame() {
		super("SilverSphere");
		
		setUI();
		
		connectEvents();
		
		setSize(INITIAL_SIZE);
		center();
		setVisible(true);
		setFocusable(true);
	}

	/**
	 * Inicializa y configura los elementos de la interfaz grafica.
	 */
	private void setUI() {
		
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
		gameMenuPanel.add(restartGameButton);
		gameMenuPanel.setVisible(false);
		
		Container contentPane = getContentPane();
		contentPane.add(menuPanel, BorderLayout.CENTER);
		contentPane.add(gameMenuPanel, BorderLayout.NORTH);
	}
	/**
	 * Metodo que se ocupa de dar respuesa a las ineracciones que realice el usuario a travez de
	 * las opciones habilitadas en los paneles y el ingreso de informaci�n por teclado.
	 */
	private void connectEvents() {
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
					requestFocus();
			}
		});
		
		loadGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = askForFile("saved");
				if (f != null)
				loadGame(f);
				requestFocus();
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
				requestFocus();
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
				requestFocus();
			}	
		});
		
		restartGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (actualLevelFile != null)
					startGame();
				else
					JOptionPane.showMessageDialog(gameMenuPanel,
							"No se conoce la ubicacion del archivo de nivel");
				requestFocus();
			}
		});		
	}

	/**
	 * metodo que se encarga de guardar el juego. 
	 * @param f
	 * @throws IOException
	 */
	protected void saveGame(File f) throws IOException {
		try {
			fm.save(f);
		}catch (StreamCorruptedException e1){
			JOptionPane.showMessageDialog (this, "El archivo esta mal formado", 
			"Error al cargar el juego", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Carga un juego guardado a partir del archivo f
	 * @param f
	 * @throws IOException
	 */
	private void loadGame(File f) {
		try{
			board = fm.loadGame(f);
			boardPanel = new BoardPanel(board.rows, board.columns, CELL_SIZE);
			setCellImages(board, boardPanel);
			menuPanel.setVisible(false);
			gameMenuPanel.setVisible(true);
			add(boardPanel, BorderLayout.CENTER);
			setSize(boardPanel.getWidth(), boardPanel.getHeight() + 20);
			center();
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "El archivo esta mal formado", 
					"Error al cargar el juego", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * metodo que retorna nuevamente al menu inicial.
	 */
	protected void returnToMenu() {
		gameMenuPanel.setVisible(false);
		if (boardPanel != null)
			boardPanel.setVisible(false);
		menuPanel.setVisible(true);
		setSize(INITIAL_SIZE);
		requestFocus();
		}

	/**
	 * Metodo que determina que accion realizar ante la presion de algun tecla.
	 * S�lo se responde ante la presi�n de alguna de las teclas de direccion, intentando mover al jugador hacia
	 * donde corresponda.
	 * @param e es un {@code KeyListener} que viene del {@code KeyPress}
	 */
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
					MoveRes ret;
					ArrayList<Position> changed = board.moveCharacter(direction);
					ret = board.checkMove(changed);
					
					try {
						updateCellImages(changed, boardPanel);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (ret.equals(MoveRes.WATER_REACHED)) {
						JOptionPane.showMessageDialog(this, "Has perdido, el jugador cayó al agua");
						startGame();
					}
					if (ret.equals(MoveRes.PLAYER_WON)) {
						JOptionPane.showMessageDialog(this, "Has ganado!");
						returnToMenu();
					}
					
				}
			}
		}
	}

	private void updateCellImages(ArrayList<Position> changed,
			BoardPanel boardPanel) throws IOException {
		GameImageFactory factory = new GameImageFactory();
		for (Position position : changed) {
				Cell c = board.getCell(position);
				boardPanel.setImage(position.y, position.x, ImageUtils.loadImage("./resources/images/cell.png"));
				if (!(c instanceof Target && !((Target) c).isVisible()))
					boardPanel.appendImage(position.y, position.x, factory.getImageFor(c));
				if (c instanceof ContainerCell && c.getContent() != null)
					boardPanel.appendImage(position.y, position.x, factory.getImageFor(c.getContent()));
			}
		boardPanel.repaint();
	}

	/**
	 * Pide un archivo, arma el tablero en base a el y lo muestra.
	 */
	private void startGame() {
		try {
			if (boardPanel != null)
				boardPanel.setVisible(false);
			board = fm.loadLevelFromFile(actualLevelFile);
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
			JOptionPane.showMessageDialog(this,
					e1.getMessage(), "Error al cargar el nivel", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}

	
	/**
	 * Metodo que da a elegir un archivo determinado a partir del parametro {@code path}
	 * @param path es un strin con el directorio de donde se quiere elegir un archivo
	 * @return un {@code File} con el archivo elegido o {@code null} si no se eligio ninguno
	 */
	private File askForFile(String path) {
		JFileChooser chooser = new JFileChooser(path);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();
		return null;
	}
	


	/**
	 * carga las imagenes del tablero logico (board) en el tablero grafico (boardPanel). 
	 * @param board 
	 * @param boardPanel
	 * @throws IOException en caso que haya habido algun problema con el manejo de los archivos.
	 * @see {@link IOException}
	 */
	private static void setCellImages (Board board, BoardPanel boardPanel) throws IOException{
		GameImageFactory factory = new GameImageFactory();
		for (int i = 0; i < board.rows; i++) {
			for (int j = 0; j < board.columns; j++) {
				Cell c = board.getCell(j, i);
				boardPanel.setImage(i, j, ImageUtils.loadImage("./resources/images/cell.png"));
				if (!(c instanceof Target && !((Target) c).isVisible()))
					boardPanel.appendImage(i, j, factory.getImageFor(c));
				if (c instanceof ContainerCell && c.getContent() != null)
					boardPanel.appendImage(i, j, factory.getImageFor(c.getContent()));
			}
		}
		boardPanel.repaint();
	}
	
	/**
	 * Metodo que se encarga de centrar el panel.
	 */
	private void center() {
		Toolkit t = getToolkit();
		Dimension d = t.getScreenSize();
		setLocation((d.width - getWidth())/2,(d.height-getHeight())/2);
	}

}
