import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


public class Game extends JComponent {

	private static final long serialVersionUID = -8395759457708163217L;
	
	private Ellipse2D.Double ball;
	private RoundRectangle2D.Double pane;
	
	private double velocity = 10;
	
	private int xDirectionBall;
	private int yDirectionBall;
	
	private BufferedImage buffer;

	protected Game(int x, int y) {
		
		ball = new Ellipse2D.Double(0, 0, 30, 30);
		pane = new RoundRectangle2D.Double(100, 100, 300, 20, 50, 50);
		
		xDirectionBall = 1;
		yDirectionBall = 1;
		
	}
	
	protected void paintComponent(Graphics g) {
		
		if(buffer == null) {
			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		
		if(buffer.getHeight() != getHeight() || buffer.getWidth() != getWidth()) {
			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		
		Graphics2D g2 = (Graphics2D)buffer.getGraphics();
		
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
		
		if(ball.x < 0) {
			xDirectionBall = 1;
			ball.x = 0;
		}
		
		if(ball.x > getWidth() - ball.getBounds().width) {
			xDirectionBall = -1;
			ball.x = getWidth() - ball.getBounds().width;
		}
		
		if(ball.y < 0) {
			yDirectionBall = 1;
			ball.y = 0;
		}
		
		if(ball.y > getHeight() - ball.getBounds().height) {
			yDirectionBall = -1;
			ball.y = getHeight() - ball.getBounds().height;
		}
		
		repaint();
	}
}
