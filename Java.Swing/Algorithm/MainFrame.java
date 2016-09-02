package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;


public class MainFrame extends JFrame {

    private static final long serialVersionUID = -979691148534081065L;

    private DisplayPanel displayPanel;
    private Toolbar toolbar;

    public MainFrame(String title) {

        //setLookAndFeel();
        setLayoutStuff();
        setParams(title);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                dispose();
                System.gc();
            }
        });
    }

    private void instantiate() {

        displayPanel = new DisplayPanel();
        toolbar = new Toolbar();
    }

    private void setLookAndFeel() {

        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

            if (info.getName().equals("Nimbus")) {
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
        setSize(new Dimension(1000, 500));
        setFocusable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(createMenuBar());
        setVisible(true);
    }

    private void setLayoutStuff() {

        instantiate();

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        int col = 0, row = 0;
        ;

        ////////////////ROW 0
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.BOTH;

        gc.gridx = col;
        gc.gridy = row;

        add(toolbar, gc);

        ////////////////ROW++
        gc.weightx = 4;
        gc.weighty = 4;

        gc.gridx = col;
        gc.gridy = ++row;
        add(displayPanel, gc);
    }

    private JMenuBar createMenuBar() {//TODO Make it work

        //INSTANTIATING
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem exitItem = new JMenuItem("Exit");

        //ADDING LISTENERS
        exitItem.addActionListener((e) -> {


            int action = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Do you wish to exit?",
                    "Confirm Exit",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (action == JOptionPane.OK_OPTION) {
                for (WindowListener listener : getWindowListeners()) {
                    listener.windowClosing(new WindowEvent(MainFrame.this, 0));
                }
            }
        });

        //ADDING MNEMONICS & ACCELERATORS
        fileMenu.setMnemonic(KeyEvent.VK_F);
        windowMenu.setMnemonic(KeyEvent.VK_W);

        exitItem.setMnemonic(KeyEvent.VK_W);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));

        //ADDING TO LAYOUT
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        return menuBar;
    }
}
