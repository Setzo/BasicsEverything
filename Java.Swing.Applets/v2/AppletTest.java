import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.Timer;


public class AppletTest extends JApplet implements ActionListener {

	private static final long serialVersionUID = -5528671500902698492L;

	private Game game;
	private Timer timer;

	public void init() {
		
		setSize(750, 300);
		
		timer = new Timer(17, this);
		game = new Game();
		
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
		
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}
	
	public void destroy() {
	}


	public void actionPerformed(ActionEvent e) {
		
		game.update();
	}
}
