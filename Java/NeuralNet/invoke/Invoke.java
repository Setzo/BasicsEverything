package invoke;

import javax.swing.SwingUtilities;

import gui.main.MainFrame;

public class Invoke {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			
			MainFrame.getInstance("Neural Network Simulator alpha v0.0.1");
		});
	}
}
