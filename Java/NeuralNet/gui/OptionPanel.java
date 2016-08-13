package gui.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

	private static final long serialVersionUID = 3389021541615862354L;
	
	private JButton okButton;
	private JButton notOkButton;
	
	public OptionPanel() {

		okButton = new JButton("ok");
		notOkButton = new JButton("nope");
		
		setBorder(BorderFactory.createTitledBorder("OptionPanel"));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		add(okButton, gc);
		
		gc.gridy++;
		add(notOkButton, gc);
		
		setPreferredSize(new Dimension(200, 500));
		setVisible(true);
	}
}
