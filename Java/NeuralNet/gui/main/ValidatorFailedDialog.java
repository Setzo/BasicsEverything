package gui.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ValidatorFailedDialog extends JDialog {

	private static final long serialVersionUID = -5384321396026883414L;
	
	private JButton okButton;
	private JLabel messageLabel;
	private JLabel emptyLabel;
	
	public ValidatorFailedDialog(JFrame owner, String title, String message) {
		
		super(owner, title, false);
		
		okButton = new JButton("Ok");
		messageLabel = new JLabel(message);
		emptyLabel = new JLabel();
		
		messageLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(2, 2, 2, 2),
				BorderFactory.createTitledBorder("Error Message: ")));

		okButton.addActionListener((e) -> setVisible(false));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 0;
		
		add(messageLabel, gc);
		
		gc.gridwidth = 1;
		gc.weighty = 0.05;
		gc.gridy++;
		
		add(emptyLabel, gc);
		
		gc.weightx = 0.1;
		gc.gridx++;
		
		add(okButton, gc);
		
		setSize(300, 150);
		setLocationRelativeTo(null);
	}
}
