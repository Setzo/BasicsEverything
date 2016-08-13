package gui.main;

import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.uti.Utilitiess;

public class NetInputArea extends JPanel {

	private static final long serialVersionUID = -7078462918829040304L;
	
	private JTextArea inputTextArea;
	
	public NetInputArea() {
		
		inputTextArea = new JTextArea();
		
		try {
			inputTextArea.setFont(Utilitiess.createFont("/fonts/GravityBook.ttf").deriveFont(15f));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	
	public StringBuilder getText() {
		
		try {
			return new StringBuilder(inputTextArea.getText());
		} catch(NullPointerException e) {
			return null;
		} 
	}
}