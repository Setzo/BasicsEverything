package gui.main;

import gui.file.filter.BinaryFileFilter;
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

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import controller.uti.Utilitiess;


public class MainFrame extends JFrame {

    private static final long serialVersionUID = -3682888377848497638L;

    private static MainFrame instance;

    public static MainFrame getInstance() {
        return instance;
    }

    public static MainFrame getInstance(String title) {

        if (instance == null) {
            instance = new MainFrame(title);
        }

        return instance;
    }

    private NeuralNetInvoker neuralNetInvoker;

    //private Controller controller;
    private Toolbar toolbar;
    private OptionPanel optionPanel;
    private NetInputArea networkInputArea;
    private NetOutputArea networkOutputArea;

    private JFileChooser fileChooser;
    private JTabbedPane networkAreasDivider;
    private JSplitPane leftRightSplitPane;
    private JSplitPane upDownSplitPane;

    private MainFrame(String title) {

        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

            if (info.getName().equals("Nimbus")) {

                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {

                    System.err.println("Nimbus failed");
                }
                break;
            }
        }

        neuralNetInvoker = new NeuralNetInvoker();

        //controller = new Controller();
        toolbar = new Toolbar();
        optionPanel = new OptionPanel();
        networkInputArea = new NetInputArea();
        networkOutputArea = new NetOutputArea();

        fileChooser = new JFileChooser();
        networkAreasDivider = new JTabbedPane();

        fileChooser.addChoosableFileFilter(new NeuralNetFileFilter());
        fileChooser.addChoosableFileFilter(new BinaryFileFilter());
        fileChooser.addChoosableFileFilter(new TextFileFilter());
        fileChooser.setFileHidingEnabled(false);

        networkAreasDivider.add("Input", networkInputArea);
        networkAreasDivider.add("Output", networkOutputArea);

        leftRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionPanel, networkAreasDivider);
        upDownSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolbar, leftRightSplitPane);

        addListeners();
        basicSetUp(title);
    }

    private void basicSetUp(String title) {

        setJMenuBar(createJMenuBar());
        setFocusable(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle(title);
        setSize(500, 500);

        setLayoutStuff();

        setVisible(true);
    }

    private void setLayoutStuff() {

        setLayout(new BorderLayout());

        //add(toolbar, BorderLayout.NORTH);
        //add(optionPanel, BorderLayout.WEST);
        add(upDownSplitPane, BorderLayout.CENTER);
    }

    private void addListeners() {

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                System.gc();
                dispose();
            }
        });

        toolbar.setButtonPressedEventListener((buttonID) -> {

            switch (buttonID) {
                case Toolbar.RUN_BUTTON: {
                    neuralNetInvoker.invokeNet();
                    break;
                }
                case Toolbar.REFRESH_BUTTON: {
                    neuralNetInvoker.invokeNet();
                    break;
                }
            }
        });

        neuralNetInvoker.setOutputLineListener((lineToApp) -> {

            networkOutputArea.appendText(lineToApp);
        });

        Controller.setInputToGetListener((lineToApp) -> {

            networkInputArea.appendText(lineToApp);
        });

        optionPanel.setValidationFailedListener((title, message) -> {

            ValidatorFailedDialog validationDialog = new ValidatorFailedDialog(this, title, message);
            validationDialog.setVisible(true);
        });

        optionPanel.setSubmitButtonListener((e) -> {

            //TODO
        });
    }

    private JMenuBar createJMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem importItem = new JMenuItem("Import");
        JMenuItem exportItem = new JMenuItem("Export");
        JMenuItem exitItem = new JMenuItem("Exit");

        JMenu showMenu = new JMenu("Show");
        JMenuItem preferencesItem = new JMenuItem("Preferences");

        JCheckBoxMenuItem showToolbarItem = new JCheckBoxMenuItem("Toolbar");
        JCheckBoxMenuItem showOptionPanelItem = new JCheckBoxMenuItem("Option Panel");
        JCheckBoxMenuItem showIOItem = new JCheckBoxMenuItem("Input / Output");

        showToolbarItem.setSelected(true);
        showOptionPanelItem.setSelected(true);
        showIOItem.setSelected(true);

        fileMenu.add(importItem);
        fileMenu.add(exportItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        showMenu.add(showToolbarItem);
        showMenu.add(showOptionPanelItem);
        showMenu.add(showIOItem);

        windowMenu.add(showMenu);
        windowMenu.add(preferencesItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        importItem.addActionListener((e) -> {
            System.out.println("clicked");
            //TODO
        });

        showToolbarItem.addActionListener((e) -> {

            toolbar.setVisible(showToolbarItem.isSelected());
            if (showToolbarItem.isSelected()) {
                upDownSplitPane.setDividerLocation(toolbar.getHeight());
            }
        });

        showOptionPanelItem.addActionListener((e) -> {

            optionPanel.setVisible(showOptionPanelItem.isSelected());
            if (showOptionPanelItem.isSelected()) {
                leftRightSplitPane.setDividerLocation(optionPanel.getWidth());
            }
        });

        showIOItem.addActionListener((e) -> {

            networkAreasDivider.setVisible(showIOItem.isSelected());
            if (showIOItem.isSelected()) {
                leftRightSplitPane.setDividerLocation(networkAreasDivider.getWidth());
            }
        });

        exportItem.addActionListener((e) -> {

            if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

                if (Utilitiess.getExtension(fileChooser.getSelectedFile().getName()) == null) {
                    Controller.saveToFile(new File(fileChooser.getSelectedFile().toString() + ".txt"), networkOutputArea.getText());

                } else if (Utilitiess.getExtension(fileChooser.getSelectedFile().getName()).equals("txt")) {
                    Controller.saveToFile(fileChooser.getSelectedFile(), networkOutputArea.getText());

                } else if (Utilitiess.getExtension(fileChooser.getSelectedFile().getName()).equals("bin")) {

                    try {
                        Controller.saveConnectionWeightsToFile(fileChooser.getSelectedFile(),
                                neuralNetInvoker.getNeuralNetwork().getConnectionWeights());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        exitItem.addActionListener((e) -> {

            int action = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Do you wish to exit?",
                    "Confirm Exit",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (action == JOptionPane.OK_OPTION) {

                WindowListener[] listeners = getWindowListeners();
                for (WindowListener listener : listeners) {
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

        showMenu.setMnemonic(KeyEvent.VK_H);
        preferencesItem.setMnemonic(KeyEvent.VK_P);
        preferencesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        return menuBar;
    }
}