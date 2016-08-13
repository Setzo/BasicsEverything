import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 4390384097346081415L;
	
	private TextPanel textPanel1;
	private JButton button1;
	private Toolbar toolbar1;
	
	MainFrame() {
		super("Hello World");
		
		setLayout(new BorderLayout());
		
		toolbar1 = new Toolbar();
		textPanel1 = new TextPanel();
		button1 = new JButton("Hello there");
		
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textPanel1.appendText("Hello there!\n");
			}
		});
		
		add(textPanel1, BorderLayout.CENTER);
		add(button1, BorderLayout.SOUTH);
		add(toolbar1, BorderLayout.NORTH);
		
		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//JFrame.EXIT_ON_CLOSE = 3
	}
}
