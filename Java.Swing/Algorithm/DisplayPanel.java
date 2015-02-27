package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	
	private static final long serialVersionUID = 1446427779646464021L;

	public  DisplayPanel() {
		
		setPreferredSize(new Dimension(400, 400));
		setLayoutStuff();
		setParams();
	}
	
	private void setParams() {

		setVisible(true);
	}
	
	private void setLayoutStuff() {
		
		setLayout(new FlowLayout());
	}
}
