package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class DisplayPanel extends JComponent {
	
	private static final long serialVersionUID = 1446427779646464021L;

	public  DisplayPanel() {
		
		setLayoutStuff();
		setParams();
	}

	private void setParams() {

		setVisible(true);
	}
	
	private void setLayoutStuff() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 460, 0, 0);
		add(new JLabel("Hello"), gc);
	}
}
