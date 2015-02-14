package snakepack;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Invoke extends JFrame{
	
	private static final long serialVersionUID = -2628521818362631107L;
	
	private static final int FIELD_SIZEX = 750;
	private static final int FIELD_SIZEY = 750;
	
	private static final int DOT_SIZEXY = 30;
	private static final int VELOCITY = 100;
	
	public Invoke() {
		
        add(new Snake(FIELD_SIZEX,
				FIELD_SIZEY,
				DOT_SIZEXY,
				VELOCITY));
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			JFrame invoke = new Invoke();
			invoke.setVisible(true);
		});
	}
}
