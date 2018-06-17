import java.awt.BorderLayout;

import javax.swing.JApplet;


public class AppletTest extends JApplet {

    private static final long serialVersionUID = -5528671500902698492L;

    public void destroy() {
        System.out.println("Destroy");
    }

    public void init() {

        setSize(750, 750);
        setLayout(new BorderLayout());
        add(new Game(), BorderLayout.CENTER);

        System.out.println("Init");
    }

    public void start() {
        System.out.println("Start");
    }

    public void stop() {
        System.out.println("Stop");
    }
}
