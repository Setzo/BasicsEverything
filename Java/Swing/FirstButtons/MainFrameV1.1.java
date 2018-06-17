import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainFrame extends JFrame {

    private static final long serialVersionUID = 4390384097346081415L;

    private TextPanel textPanel1;
    private JButton button1;
    private JButton button2;

    MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        textPanel1 = new TextPanel();
        button1 = new JButton("Hello there");
        button2 = new JButton("GTFO");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                textPanel1.appendText("Hello there!\n");
                button1.setEnabled(false);
                button2.setEnabled(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                textPanel1.appendText("Why you click meh?\n");
                button1.setEnabled(true);
                button2.setEnabled(false);
            }
        });

        add(textPanel1, BorderLayout.CENTER);
        add(button1, BorderLayout.SOUTH);
        add(button2, BorderLayout.NORTH);

        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //JFrame.EXIT_ON_CLOSE = 3
    }
}
