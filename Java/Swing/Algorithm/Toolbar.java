package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

    private static final long serialVersionUID = -4481091577239946236L;

    private JButton bSTButton;
    private JButton aVLButton;
    private JButton aStarButton;

    public Toolbar() {

        instantiate();
        setLayoutStuff();
        setParams();
    }

    private void instantiate() {

        bSTButton = new JButton("BST");
        aVLButton = new JButton("AVL");
        aStarButton = new JButton("A*");
    }

    private void setParams() {

        setFloatable(true);
        setVisible(true);
    }

    private void setLayoutStuff() {

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(bSTButton);
        add(aVLButton);
        add(aStarButton);
    }
}
