package provisorio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.EventListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private BoardPanel boardPanel;
	private MenuPanel menuPanel = new MenuPanel();
	GameFrame() {
		super("SilverSphere");
		add(menuPanel);
		setVisible(true);
		setSize(300, 500);
	}

}
