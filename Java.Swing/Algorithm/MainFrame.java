package view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;


public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -979691148534081065L;
	
	private DisplayPanel displayPanel;
	private Toolbar toolbar;
	
	public MainFrame(String title) {
		
		setLookAndFeel();
		instantiate();
		setLayoutStuff();
		setParams(title);
	}
	
	private void instantiate() {
		
		displayPanel = new DisplayPanel();
		toolbar = new Toolbar();
	}
	
	private void setLookAndFeel() {
		
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			
			if(info.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
					
				} catch (ClassNotFoundException e) {
					System.err.println("Couldn't find the nimbus look and feel.");
				} catch (InstantiationException e) {
					System.err.println("Couldn't instantiate the nimbus look and feel");
				} catch (IllegalAccessException e) {
					System.err.println("No rights to access the nimbus look and feel");
				} catch (UnsupportedLookAndFeelException e) {
					System.err.println("This look and feel type is not supported");
				}
				break;
			}
		}
	}
	
	private void setParams(String title) {
		
		setTitle(title);
		setSize(new Dimension(500, 500));
		setFocusable(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setJMenuBar(createMenuBar());
		setVisible(true);
	}

	private void setLayoutStuff() {
		
		setLayout(new BorderLayout());
		
		add(displayPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
	}
	
	private JMenuBar createMenuBar() {//TODO Make it work
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(exitItem);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		return menuBar;
	}
}
