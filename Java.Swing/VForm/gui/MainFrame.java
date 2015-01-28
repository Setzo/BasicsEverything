package gui;

import gui.listeners.FormListener;
import gui.listeners.PersonTableListener;
import gui.listeners.PreferencesListener;
import gui.listeners.ToolbarListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import controller.Controller;
import data.listeners.CntChangeListener;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 3383150008662809288L;
	
	private Controller controller;
	//private TextPanel textPanel;
	private FormPanel formPanel;
	private TablePanel tablePanel;
	private Toolbar toolbar;
	private JFileChooser fileChooser;
	private Preferences prefs;
	private JTabbedPane tabbedPane;
	private MessagePanel messagePanel;
	
	private PreferencesDialog preferencesDialog;
	
	public MainFrame(String title) {
		
		super(title);
		
		/*try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}*/
		
		try {
			UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		controller = new Controller();
		
		setLayout(new BorderLayout());
		
		toolbar = new Toolbar();
		//textPanel = new TextPanel();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		preferencesDialog = new PreferencesDialog(this);
		tabbedPane = new JTabbedPane();
		messagePanel = new MessagePanel();
		
		tabbedPane.addTab("Person Database", tablePanel);
		tabbedPane.addTab("Messages", messagePanel);
		
		prefs = Preferences.userRoot().node("db");
		
		tablePanel.setData(controller.getPeople());
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				
				controller.removePerson(row);
			}
		});
		
		preferencesDialog.setPreferencesListener(new PreferencesListener() {
			public void preferencesSet(String username, String password, int port) {
				
				prefs.put("username", username);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});
		
		preferencesDialog.setDefault(prefs.get("username", null),
				prefs.get("password", null),
				prefs.getInt("port", 3306));
		
		setJMenuBar(createMenuBar());
		
		toolbar.setToolbarListener(new ToolbarListener() {
			public void saveEventOccured() {
				
				connect();
				
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database",
							"Database Saving Failure", JOptionPane.ERROR_MESSAGE);

				}
			}

			public void refreshEventOccured() {
				
				try {
					connect();
					try {
						controller.load();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database",
								"Database Loading Failure", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					tablePanel.refresh();
				}
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				
				/*String name = e.getName();
				String occupation = e.getOccupation();
				int age = e.getAge();
				String employment = e.getEmployment();
				boolean ispl = e.isPlCitizen();
				String docID = e.getNrDow();
				String gender = e.getGender();
				
				textPanel.appendText("Name: "
				+ name + "\nOccupation: "
				+ occupation + "\nAge Category: "
				+ age + "\nEmployment: "
				+ employment + "\nIs polish citizen: "
				+ ispl + "\nDocument ID: "
				+ docID + "\nGender: "
				+ gender +"\n\n");*/
				
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				controller.disconnect();
				dispose();
				System.gc();
			}
		});
		
		controller.setCntChangeListener(new CntChangeListener() {
			public void firePersonChangeEvent() {
				
				tablePanel.refresh();
			}
		});
		
		add(tabbedPane, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.PAGE_START);
		
		setLocationByPlatform(true);
		setVisible(true);
		setSize(1000, 500);
		setMinimumSize(new Dimension(600, 450));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		
		JMenuItem exportDataItem = new JMenuItem("Export Data..");
		JMenuItem importDataItem = new JMenuItem("Import Data..");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenuItem chooseSkin = new JMenu("Skins");
		JMenu showMenu = new JMenu("Show");
		JMenuItem showFormItem = new JCheckBoxMenuItem("Form Panel");
		JMenuItem showToolbarItem = new JCheckBoxMenuItem("Toolbar");
		JMenuItem preferences = new JMenuItem("Preferences");

		showFormItem.setSelected(true);
		showToolbarItem.setSelected(true);
		
		showMenu.add(showToolbarItem);
		showMenu.add(showFormItem);
		
		windowMenu.add(showMenu);
		windowMenu.add(chooseSkin);
		windowMenu.addSeparator();
		windowMenu.add(preferences);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		preferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				preferencesDialog.setVisible(true);
			}
		});
		
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				if((JCheckBoxMenuItem)ev.getSource() != null) {
					JCheckBoxMenuItem menuItem =(JCheckBoxMenuItem)ev.getSource();
				
					formPanel.setVisible(menuItem.isSelected());
				}
			}
		});
		
		showToolbarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				if((JCheckBoxMenuItem)ev.getSource() != null) {
					JCheckBoxMenuItem menuItem =(JCheckBoxMenuItem)ev.getSource();
				
					toolbar.setVisible(menuItem.isSelected());
				}
			}
		});
		
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
						
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
						
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "File is corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Couldn't open file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

					try {
						controller.saveToFile(fileChooser.getSelectedFile());
						
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Couldn't open file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*String text = JOptionPane.showInputDialog(MainFrame.this,
						"Enter nickname",
						"Enter Nickname",
						JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println(text);*/
				
				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you wish to exit?",
						"Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(getClass().getResource("/images/warn46.png"), "Warning"));
				
				if(action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					for(WindowListener listener : listeners) {
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}
				//else if(action == JOptionPane.CANCEL_OPTION) {
				//	return;
				//}
			}
		});
		
		preferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		
		preferences.setMnemonic(KeyEvent.VK_P);
		windowMenu.setMnemonic(KeyEvent.VK_W);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		return menuBar;
	}
	
	private void connect() {
		
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Couldn't connect to database",
					"Database Connection Failure", JOptionPane.ERROR_MESSAGE);
		}
	}
}
