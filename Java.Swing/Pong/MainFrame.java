package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainFrame extends JFrame implements ActionListener{
	
		private static final long serialVersionUID = -5528671500902698492L;

		private static long cnt = 0;
		
		private Game game;
		private Timer timer;
		
		public MainFrame() {
			
			setSize(500, 800);
			setVisible(true);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					
					System.gc();
					dispose();
					System.exit(0);
				}
			});
			
			timer = new Timer(17, this);
			game = new Game();
			
			setLayout(new BorderLayout());
			add(game, BorderLayout.CENTER);
			
			timer.start();
		}

		public void actionPerformed(ActionEvent e) {
			
			cnt++;
			
			if(cnt % 2800 == 0) {
				game.setVelocity(game.getVelocity() + 1);
			}
			game.update();
		}
}
