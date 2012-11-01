package provisorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	BoardPanel boardPanel;
	MenuPanel menuPanel = new MenuPanel();
	JButton newGameButton = new JButton("New Game");
	JButton loadGameButton = new JButton("Load Game");
	JButton exitButton = new JButton("Exit");
	GameFrame() {
		super("SilverSphere");
		
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	TODO
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
		setVisible(true);
		setSize(300, 500);
	}
	
	void newGameButton_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadGameButton_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	File askForFile() {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();
		return null;
	}

}
