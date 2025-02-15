import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TextPanel extends JPanel {

    private static final long serialVersionUID = 4933762970216328125L;

    private JTextArea textArea;

    TextPanel() {
        textArea = new JTextArea();

        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String c) {
        textArea.append(c);
    }
}
