package invoke;

import javax.swing.SwingUtilities;

import view.MainFrame;


public class Invoke {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new MainFrame("Algorithms"));

    }
}
