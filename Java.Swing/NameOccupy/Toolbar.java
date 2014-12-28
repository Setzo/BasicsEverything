package nameOccupy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -3423439769286911843L;
	
	private JButton sayHi;
	private JButton sayBye;
	
	private StringListener strListener;
	
	public Toolbar() {
		
		setBorder(BorderFactory.createTitledBorder("Toolbar"));
		
		sayHi = new JButton("Hi");
		sayBye = new JButton("Bye");
		
		sayHi.addActionListener(this);
		sayBye.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(sayHi);
		add(sayBye);
	}
	
	public void setStringListener(StringListener listener) {
		this.strListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (strListener != null) {
			JButton clicked = (JButton)e.getSource();
			if (clicked == sayHi) {
				strListener.textToTypeOut("Hi");
			} else if (clicked == sayBye) {
				strListener.textToTypeOut("Bye");
			}
		}
	}
}
