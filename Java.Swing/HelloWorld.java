import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Application {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
 
			@Override
			public void run() {
				JFrame helloWorld = new JFrame("Hello World! :D");
				helloWorld.setVisible(true);
				helloWorld.setSize(500, 500);
				helloWorld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//JFrame.EXIT_ON_CLOSE = 3
			}
		});
	}
}
