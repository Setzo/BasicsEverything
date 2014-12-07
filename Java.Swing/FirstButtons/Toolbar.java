import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel {

	private static final long serialVersionUID = -5261480899581379323L;
	
	private JButton sayHi;
	private JButton sayBye;
	
	public Toolbar() {
		
		sayHi = new JButton("Hi");
		sayBye = new JButton("Bye");
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(sayHi);
		add(sayBye);
	}

}
