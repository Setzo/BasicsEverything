package gui.main;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NetOutputArea extends JPanel {

	private static final long serialVersionUID = -7078462918829040304L;
	
	private JTextArea outputTextArea;
	
	public NetOutputArea() {
		
		outputTextArea = new JTextArea();
		
		outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		setLayout(new BorderLayout());
		
		add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
		outputTextArea.setEditable(false);
	}
	
	public void appendText(String c) {
		outputTextArea.append(c);
	}
	
	public void setText(String c) {
		outputTextArea.setText(c);
	}
	
	public StringBuilder getText() {
		
		try {
			return new StringBuilder(outputTextArea.getText());
		} catch(NullPointerException e) {
			return null;
		} 
	}
}
