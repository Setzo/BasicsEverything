package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {
	
	private static final long serialVersionUID = -4481091577239946236L;
	
	private JButton bSTButton;
	
	public Toolbar() {
		
		instantiate();
		setLayoutStuff();
		setParams();
	}
	
	private void instantiate() {
		
		bSTButton = new JButton("BST");
	}
	
	private void setParams() {

		setFloatable(false);
		setVisible(true);
		setPreferredSize(new Dimension(200, 500));
	}
	
	private void setLayoutStuff() {
		
		setLayout(new FlowLayout());
		
		add(bSTButton);
	}
}
