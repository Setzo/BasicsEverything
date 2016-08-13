package gui.main;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NetInputArea extends JPanel {

	private static final long serialVersionUID = -7078462918829040304L;
	
	private JTextArea inputTextArea;
	
	public NetInputArea() {
		
		inputTextArea = new JTextArea();
		
		inputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		setLayout(new BorderLayout());
		
		add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
		inputTextArea.setEditable(false);
	}
	
	public void appendText(String c) {
		inputTextArea.append(c);
	}
	
	public void setText(String c) {
		inputTextArea.setText(c);
	}
}
