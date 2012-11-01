package provisorio;

public class Game implements Runnable {
	@Override
	public void run() {
		GameFrame frame = new GameFrame();
		frame.setVisible(true);	
	}
}
