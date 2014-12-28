package nameOccupy;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TextPanel extends JPanel {

	private static final long serialVersionUID = 3921459457900607517L;
	
	private JTextArea textArea ;
	TextPanel() {
		textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void appendText(String c) {
		textArea.append(c);
	}
}
