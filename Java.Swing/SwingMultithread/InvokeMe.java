package swingUt;

import javax.swing.SwingUtilities;

public class SwingUtilitiess {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame ("Count");
			}
		});
	}
}
