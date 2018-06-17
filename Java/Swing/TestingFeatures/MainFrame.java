package nameOccupy;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class MainFrame extends JFrame {

    private static final long serialVersionUID = 3383150008662809288L;

    private TextPanel textPanel;
    private FormPanel formPanel;
    private Toolbar toolbar;

    MainFrame() {
        super("Hello World");

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus L&F not found");
        }

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();

        toolbar.setStringListener(new StringListener() {

            public void textToTypeOut(String text) {
                textPanel.appendText(text + "\n");
            }
        });

        formPanel.setFormListener(new FormListener() {
            public void formEventOccured(FormEvent e) {

                String name = e.getName();
                String occupation = e.getOccupation();
                int age = e.getAge();
                String employment = e.getEmployment();
                boolean ispl = e.isPlCitizen();
                String nrd = e.getNrDow();
                textPanel.appendText("Name: "
                        + name + "\nOccupation: "
                        + occupation + "\nAge Category: "
                        + age + "\nEmployment: "
                        + employment + "\nIs Polish citizen: "
                        + ispl + "\nDocument ID: "
                        + nrd + "\n\n");
            }
        });

        add(textPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);

        setVisible(true);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
