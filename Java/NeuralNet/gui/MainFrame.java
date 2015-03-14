package gui.main;

import gui.file.filter.NeuralNetFileFilter;
import gui.file.filter.TextFileFilter;
import invoke.NeuralNetInvoker;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import uti.Utilitiess;
import controller.Controller;



public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3682888377848497638L;
	
	private static MainFrame instance;
	
	public static MainFrame getInstance(String title) {
		
		if(instance == null) {
			instance = new MainFrame(title);
		}
		
		return instance;
	}
	
	private NeuralNetInvoker neuralNetInvoker;

	private Controller controller;
	private Toolbar toolbar;
	private OptionPanel optionPanel;
	private NetInputArea networkInputArea;
	private NetOutputArea networkOutputArea;

	private JFileChooser fileChooser;
	private JTabbedPane networkAreasDivider;
	
	private MainFrame(String title) {
		
		neuralNetInvoker = new NeuralNetInvoker();
		
		controller = new Controller();
		toolbar = new Toolbar();
		optionPanel = new OptionPanel();
		networkInputArea = new NetInputArea();
		networkOutputArea = new NetOutputArea();
		
		fileChooser = new JFileChooser();
		networkAreasDivider = new JTabbedPane();
		
		fileChooser.addChoosableFileFilter(new NeuralNetFileFilter());
		fileChooser.addChoosableFileFilter(new TextFileFilter());
		
		networkAreasDivider.add("Input", networkInputArea);
		networkAreasDivider.add("Output", networkOutputArea);
		
		addListeners();
		basicSetUp(title);
	}
	
	private void basicSetUp(String title) {
		
		/*for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			
			if(info.getName().equals("Nimbus")) {
				
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					
					System.err.println("Nimbus failed");
				}
				break;
			}
		}*/
		
		setJMenuBar(createJMenuBar());
		setFocusable(true);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle(title);
		setSize(500,500);
		
		setLayoutStuff();
		
		setVisible(true);
	}
	
	private void setLayoutStuff() {
		
		setLayout(new BorderLayout());
		
		add(toolbar, BorderLayout.NORTH);
		add(optionPanel, BorderLayout.WEST);
		add(networkAreasDivider, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.gc();
				dispose();
			}
		});
		
		toolbar.setButtonPressedEventListener((buttonID) -> {
			
			switch(buttonID) {
			case Toolbar.RUN_BUTTON : {
				neuralNetInvoker.invokeNet();
				break;
			}
			case Toolbar.REFRESH_BUTTON : {
				neuralNetInvoker.invokeNet();
				break;
			}
			}
		});
		
		neuralNetInvoker.setOutputLineListener((lineToApp) -> {
			networkOutputArea.appendText(lineToApp);
		});
	}
	
	private JMenuBar createJMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		
		JMenuItem importItem = new JMenuItem("Import");
		JMenuItem exportItem = new JMenuItem("Export");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		JMenuItem showItem = new JMenuItem("Show");
		JMenuItem preferencesItem = new JMenuItem("Preferences");
		
		fileMenu.add(importItem);
		fileMenu.add(exportItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		windowMenu.add(showItem);
		windowMenu.add(preferencesItem);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		importItem.addActionListener((e) -> {
			System.out.println("clicked");
			//TODO
		});
		
		exportItem.addActionListener((e) -> {

			if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

				if (Utilitiess.getExtension(fileChooser.getSelectedFile().getName()) == null) {
					controller.saveToFile(new File(fileChooser.getSelectedFile().toString() + ".txt"), networkOutputArea.getText());
					
				} else if (Utilitiess.getExtension(fileChooser.getSelectedFile().getName()).equals("txt")) {
					controller.saveToFile(fileChooser.getSelectedFile(), networkOutputArea.getText());
					
				} else if (!Utilitiess.getExtension(fileChooser.getSelectedFile().getName()).equals("txt")) {
					controller.saveToFile(new File(fileChooser.getSelectedFile().toString() + ".txt"), networkOutputArea.getText());
				}
			}
		});
		
		exitItem.addActionListener((e) -> {
			
			int action = JOptionPane.showConfirmDialog(MainFrame.this,
					"Do you wish to exit?",
					"Confirm Exit",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			
			if(action == JOptionPane.OK_OPTION) {
				
				WindowListener[] listeners = getWindowListeners();
				for(WindowListener listener : listeners) {
					listener.windowClosing(new WindowEvent(MainFrame.this, 0));
				}
			}
		});
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		windowMenu.setMnemonic(KeyEvent.VK_W);
		
		importItem.setMnemonic(KeyEvent.VK_I);
		importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		exportItem.setMnemonic(KeyEvent.VK_E);
		exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		showItem.setMnemonic(KeyEvent.VK_H);
		showItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		preferencesItem.setMnemonic(KeyEvent.VK_P);
		preferencesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		
		return menuBar;
	}
}
