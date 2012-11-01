package provisorio;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class MenuPanel extends Box {
	private JButton newGameButton = new JButton("New Game");
	private JButton loadGameButton = new JButton("Load Game");
	private JButton exitButton = new JButton("Exit");
	MenuPanel() {
		super(BoxLayout.Y_AXIS);
		
		newGameButton.addMouseListener(new MouseAdapter() {
			private File f;

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setVisible(true);
				if (fileChooser.showOpenDialog(e.getComponent()) == JFileChooser.APPROVE_OPTION)
					f = fileChooser.getSelectedFile();
			}
		});
		add(newGameButton, BorderLayout.NORTH);
		
		loadGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
			}
		});
		add(loadGameButton, BorderLayout.CENTER);
		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton, BorderLayout.SOUTH);
		setSize(300, 500);
	}
}
