package gui.main;

import gui.listeners.ButtonPressedEventListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import uti.Utilitiess;

public class Toolbar extends JToolBar {

	private static final long serialVersionUID = -2073774014437376685L;
	
	public static final int RUN_BUTTON = 0;
	public static final int REFRESH_BUTTON = 1;
	
	private JButton runButton;
	private JButton refreshButton;
	
	private ButtonPressedEventListener listener;
	
	public Toolbar() {

		runButton = new JButton("", Utilitiess.createIcon("/images/run.png", "Run"));
		refreshButton = new JButton("", Utilitiess.createIcon("/images/refresh.png", "Refresh"));
		
		add(runButton);
		add(refreshButton);
		
		runButton.addActionListener((e) -> {
			
			if(this.listener != null) {
				this.listener.buttonPressedEventOccured(RUN_BUTTON);
			}
		});
		
		refreshButton.addActionListener((e) -> {
			
			if(this.listener != null) {
				this.listener.buttonPressedEventOccured(REFRESH_BUTTON);
			}
		});
		
		setFloatable(false);
		
		setVisible(true);
	}
	
	public void setButtonPressedEventListener(ButtonPressedEventListener listener) {
		this.listener = listener;
	}
}
