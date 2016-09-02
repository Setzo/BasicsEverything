import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;


public class Game extends JComponent {

    private static final long serialVersionUID = -8395759457708163217L;

    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.ORANGE);
        //g2.drawRect(0, 0, 20, 20);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLACK);

        g2.fill(new Ellipse2D.Double(0, 0, 30, 30));

        g2.fill(new RoundRectangle2D.Double(100, 100, 300, 20, 50, 50));
    }
}
