package invoke;

import gui.main.MainFrame;

import javax.swing.SwingUtilities;

public class Invoke {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			
			MainFrame.getInstance("Neural Network Simulator alpha v0.1.1");
		});
	}
}