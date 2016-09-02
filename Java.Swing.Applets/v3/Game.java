import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


public class Game extends JComponent {

    private static final long serialVersionUID = -8395759457708163217L;

    private Ellipse2D.Double ball;
    private RoundRectangle2D.Double pane;

    private double velocity = 6;

    private int xDirectionBall;
    private int yDirectionBall;

    private BufferedImage buffer;

    private boolean check = true;

    protected Game() {

        ball = new Ellipse2D.Double(0, 0, 30, 30);
        pane = new RoundRectangle2D.Double(100, 100, 200, 20, 50, 50);

        xDirectionBall = 1;
        yDirectionBall = 1;

        addMouseMotionListener(new MouseMotionListener() {

            public void mouseMoved(MouseEvent e) {
                pane.x = e.getX() - pane.width / 2;
                //pane.y = e.getY() - pane.height / 2;
            }

            public void mouseDragged(MouseEvent e) {

            }
        });

        addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                ball.x = 0;
                ball.y = 0;
            }
        });

        setCursor(getToolkit().createCustomCursor(
                new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
                new Point(0, 0),
                ""));

		/*KeyboardFocusManager
		.getCurrentKeyboardFocusManager()
		.addKeyEventDispatcher((e) -> {

			System.out.println("e.getKeyCode()");
			return false;
		});*/
    }

    protected void paintComponent(Graphics g) {

        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            pane.y = getHeight() - pane.height - 2;
        }

        if (buffer.getHeight() != getHeight() || buffer.getWidth() != getWidth()) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        Graphics2D g2 = (Graphics2D) buffer.getGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.ORANGE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLACK);
        g2.fill(ball);
        g2.fill(pane);

        g.drawImage(buffer, 0, 0, null);
    }

    public void update() {

        ball.x += xDirectionBall * velocity;
        ball.y += yDirectionBall * velocity;

        if (ball.x < 0) {
            xDirectionBall = 1;
            ball.x = 0;
        }

        if (ball.x > getWidth() - ball.getBounds().width) {
            xDirectionBall = -1;
            ball.x = getWidth() - ball.getBounds().width;
        }

        if (ball.y < 0) {
            yDirectionBall = 1;
            ball.y = 0;
        }

        if (ball.y > getHeight() - ball.getBounds().height) {
            yDirectionBall = -1;
            ball.y = getHeight() - ball.getBounds().height;
        }

        if (check) {
            if (ball.intersects(pane.getBounds2D())) {
                yDirectionBall = -yDirectionBall;
                xDirectionBall = -xDirectionBall;
                check = false;
            }
        } else {
            check = true;
        }

        repaint();
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

}
