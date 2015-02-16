package snakepack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Snake extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6541285683612229156L;
	
	private static final int UP_DIRECTION = 38;
	private static final int DOWN_DIRECTION = 40;
	private static final int RIGHT_DIRECTION = 39;
	private static final int LEFT_DIRECTION = 37;

	private final int FIELD_SIZEX;
	private final int FIELD_SIZEY;

	private final int DOT_SIZEXY;
	private final int VELOCITY;
	
	private final int GRID_SIZE;

	private final int DOT_LIMIT;

	private Image snakeTorsoUpDown;
	private Image snakeTorsoLeftRight;
	
	private Image snakeHeadUp;
	private Image snakeHeadDown;
	private Image snakeHeadLeft;
	private Image snakeHeadRight;
	
	private Image snakeTailUp;
	private Image snakeTailDown;
	private Image snakeTailRight;
	private Image snakeTailLeft;
	
	private Image snakeDownToRight;
	private Image snakeDownToLeft;
	private Image snakeUpToRight;
	private Image snakeUpToLeft;
	
	private Image foodIcon;
	
	private int rightDirection;	//2
	private int leftDirection;	//3
	private int upDirection;	//0
	private int downDirection;	//1

	private LinkedList<Integer> lastDirection;
	
	private boolean isAlive;
	
	private int foodX;
	private int foodY;
	
	private int snakeSize;
	
	private int[] snakeXs;
	private int[] snakeYs;
	
	private Timer timer;
	private Timer velocityUpTimer;

	private BufferedImage buffer;
	
	protected Snake(int width, int height, int dotSize, int velocity) {

		FIELD_SIZEX = width;
		FIELD_SIZEY = height;
		DOT_SIZEXY = dotSize;
		VELOCITY = velocity;

		DOT_LIMIT = (FIELD_SIZEX * FIELD_SIZEY) / (DOT_SIZEXY * DOT_SIZEXY);
		
		GRID_SIZE = FIELD_SIZEX / DOT_SIZEXY;

		snakeHeadUp = createIcon("/images/head.png");
		snakeHeadDown = createIcon("/images/headD.png");
		snakeHeadLeft = createIcon("/images/headL.png");
		snakeHeadRight = createIcon("/images/headR.png");

		snakeTorsoUpDown = createIcon("/images/torso.png");
		snakeTorsoLeftRight = createIcon("/images/torso2.png");
		
		snakeTailUp = createIcon("/images/tail.png");
		snakeTailDown = createIcon("/images/tailD.png");
		snakeTailLeft = createIcon("/images/tailR.png");
		snakeTailRight = createIcon("/images/tailL.png");
		
		snakeDownToRight = createIcon("/images/downToRight.png");
		snakeDownToLeft = createIcon("/images/downToLeft.png");
		snakeUpToRight = createIcon("/images/upToRight.png");
		snakeUpToLeft = createIcon("/images/upToLeft.png");
		
		foodIcon = createIcon("/images/snakeFood.png");
		
		timer = new Timer(VELOCITY, this);
		
		velocityUpTimer = new Timer(5000, (e) ->		
			timer.setDelay(timer.getDelay() <= 40 ? 40 : timer.getDelay() - 5)
		);
		
		snakeXs = new int[DOT_LIMIT];
		snakeYs = new int[DOT_LIMIT];
		
		isAlive = true;
		
		rightDirection = 0;
		leftDirection = 0;
		upDirection = 1;
		downDirection = 0;
		
		lastDirection = new LinkedList<Integer>();
		
		lastDirection.add(UP_DIRECTION);
		
		setFocusable(true);
		setBackground(new Color(77, 77, 77));
		
		setPreferredSize(new Dimension(FIELD_SIZEX, FIELD_SIZEY));
		setVisible(true);
		
		snakeSize = 2;
		
		KeyboardFocusManager
		.getCurrentKeyboardFocusManager()
		.addKeyEventDispatcher((e) -> {

			setDirection(e.getKeyCode());
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE && !isAlive) {
				restartGame();
			}
			
			return false;
		});
		
		startGame();
	}

	private void setDirection(int e) {
		
		if(e < LEFT_DIRECTION || e > DOWN_DIRECTION) {
			return;
		}
		
		if(!checkLastDirection(e)) {
			return;
		}
		
		zeroDirection();
		
		switch (e) {
		case UP_DIRECTION : {
			upDirection = 1;
			if(lastDirection.getLast() != UP_DIRECTION) {
				lastDirection.add(UP_DIRECTION);
			}
			return;
			
		} case DOWN_DIRECTION : {
			downDirection = 1;
			if(lastDirection.getLast() != DOWN_DIRECTION) {
				lastDirection.add(DOWN_DIRECTION);
			}
			return;
			
		} case RIGHT_DIRECTION : {
			rightDirection = 1;
			if(lastDirection.getLast() != RIGHT_DIRECTION) {
				lastDirection.add(RIGHT_DIRECTION);
			}
			return;
			
		} case LEFT_DIRECTION : {
			leftDirection = 1;
			if(lastDirection.getLast() != LEFT_DIRECTION) {
				lastDirection.add(LEFT_DIRECTION);
			}
			return;
			
		} default :
			return;
		}
	}
	
	private boolean checkLastDirection(int iter) {
		
		for(int i = lastDirection.size() - 1; i >= 0; i--) {
			
			if(lastDirection.get(i) == iter) {
				continue;
				
			} else if(lastDirection.get(i) == DOWN_DIRECTION && iter == UP_DIRECTION) {
				return false;
			} else if(lastDirection.get(i) == UP_DIRECTION && iter == DOWN_DIRECTION) {
				return false;
			} else if(lastDirection.get(i) == LEFT_DIRECTION && iter == RIGHT_DIRECTION) {
				return false;
			} else if(lastDirection.get(i) == RIGHT_DIRECTION && iter == LEFT_DIRECTION) {
				return false;
			} else {
				return true;
			}
		}
		
		return true;
	}
	
	private void zeroDirection() {
		
		upDirection = 0;
		downDirection = 0;
		leftDirection = 0;
		rightDirection = 0;
	}
	
	private void makeFood() {
		
		Random rng = new Random();
		
		foodX = rng.nextInt(GRID_SIZE) * DOT_SIZEXY;
		foodY = rng.nextInt(GRID_SIZE) * DOT_SIZEXY;
	}
	
	private static Image createIcon(String path) {

		if (System.class.getResource(path) == null) {
			System.out.println("Wrong path");
		}

		return new ImageIcon(System.class.getResource(path)).getImage();
	}
	
	private void startGame() {

		for (int i = 0; i < snakeSize; i++) {

			snakeXs[i] = (FIELD_SIZEX / 2 % DOT_SIZEXY) * DOT_SIZEXY - i * DOT_SIZEXY;
			snakeYs[i] = (FIELD_SIZEY / 2 % DOT_SIZEXY) * DOT_SIZEXY;
		}

		makeFood();

		timer.start();
		velocityUpTimer.start();
	}
	
	public void paintComponent(Graphics g) {

		if(buffer == null) {
			buffer = new BufferedImage(FIELD_SIZEX, FIELD_SIZEY, BufferedImage.TYPE_INT_RGB);
		}
		
		Graphics2D g2 = (Graphics2D)buffer.getGraphics();
		
		if(isAlive) {
			g2.setColor(new Color(77, 77, 77));
			g2.fillRect(0, 0, FIELD_SIZEX, FIELD_SIZEY);
			
			g2.drawImage(foodIcon, foodX, foodY, this);
			
			if (isAlive) {
				for (int i = 0; i < snakeSize; i++) {
	
					paintSnake(g2, i);
				}
			}
			
			g.drawImage(buffer, 0, 0, null);
			
		} else {
			endGame(g2);
			g.drawImage(buffer, 0, 0, null);
		}
		
		Toolkit.getDefaultToolkit().sync();
    }
	
	private void endGame(Graphics2D g2) {
		
	        g2.setColor(Color.BLACK);
	        g2.fillRect(0, 0, FIELD_SIZEX, FIELD_SIZEY);
	        
	        g2.setColor(Color.ORANGE);
	        
	        g2.drawString("Game Over", FIELD_SIZEX / 2 - 30, FIELD_SIZEY / 2 - 20);
	        g2.drawString("Press SPACE to try again", FIELD_SIZEX / 2 - 67, FIELD_SIZEY / 2);
	}
	
	private void restartGame() {
		
		timer = new Timer(VELOCITY, this);
		
		velocityUpTimer = new Timer(5000, (e) ->		
			timer.setDelay(timer.getDelay() < 40 ? 40 : timer.getDelay() - 5)
		);
		
		snakeXs = new int[DOT_LIMIT];
		snakeYs = new int[DOT_LIMIT];
		
		isAlive = true;
		
		rightDirection = 0;
		leftDirection = 0;
		upDirection = 1;
		downDirection = 0;
		
		lastDirection = new LinkedList<Integer>();
		
		lastDirection.add(UP_DIRECTION);
		
		snakeSize = 2;
		
		startGame();
	}
	
	private void paintSnake(Graphics2D g2, int i) {
		
		if (i == 0) {
			
			switch(lastDirection.getLast()) {
			case LEFT_DIRECTION : {
				g2.drawImage(snakeHeadLeft, snakeXs[i], snakeYs[i], this);
				break;
			} case UP_DIRECTION : {
				g2.drawImage(snakeHeadUp, snakeXs[i], snakeYs[i], this);
				break;
			} case RIGHT_DIRECTION : {
				g2.drawImage(snakeHeadRight, snakeXs[i], snakeYs[i], this);
				break;
			} case DOWN_DIRECTION : {
				g2.drawImage(snakeHeadDown, snakeXs[i], snakeYs[i], this);
				break;
			}
			}

		} else if (i == snakeSize - 1) {
			
			if(snakeXs[snakeSize - 1] > snakeXs[snakeSize - 2]
					&& snakeYs[snakeSize -1] == snakeYs[snakeSize - 2]) {
				
				g2.drawImage(snakeTailLeft, snakeXs[i], snakeYs[i], this);
				
			} else if(snakeXs[snakeSize - 1] < snakeXs[snakeSize - 2]
					&& snakeYs[snakeSize - 1] == snakeYs[snakeSize - 2]) {
				
				g2.drawImage(snakeTailRight, snakeXs[i], snakeYs[i], this);
				
			} else if(snakeXs[snakeSize - 1] == snakeXs[snakeSize - 2]
					&& snakeYs[snakeSize - 1] > snakeYs[snakeSize - 2]) {
				
				g2.drawImage(snakeTailUp, snakeXs[i], snakeYs[i], this);
				
			} else if(snakeXs[snakeSize - 1] == snakeXs[snakeSize - 2]
					&& snakeYs[snakeSize - 1] < snakeYs[snakeSize - 2]) {
				
				g2.drawImage(snakeTailDown, snakeXs[i], snakeYs[i], this);
			} 

		} else {
			
			if(snakeXs[i] == snakeXs [i + 1] && snakeXs[i] == snakeXs[i - 1]) {
				
				g2.drawImage(snakeTorsoUpDown, snakeXs[i], snakeYs[i], this);
				
			} else if (snakeYs[i] == snakeYs [i + 1] && snakeYs[i] == snakeYs[i - 1]){
				
				g2.drawImage(snakeTorsoLeftRight, snakeXs[i], snakeYs[i], this);
				
			} else if((snakeXs[i] == snakeXs[i + 1] && snakeXs[i] > snakeXs[i-1])
					|| (snakeXs[i] > snakeXs[i + 1] && snakeXs[i] == snakeXs[i-1])) {
				
				if((snakeYs[i] == snakeYs[i + 1] && snakeYs[i] > snakeYs[i-1])
						|| (snakeYs[i] > snakeYs[i + 1] && snakeYs[i] == snakeYs[i-1])) {
					
					g2.drawImage(snakeUpToLeft, snakeXs[i], snakeYs[i], this);
					
				} else {
					g2.drawImage(snakeDownToLeft, snakeXs[i], snakeYs[i], this);
				}
				
			} else if((snakeXs[i] == snakeXs[i + 1] && snakeXs[i] < snakeXs[i-1])
					|| (snakeXs[i] < snakeXs[i + 1] && snakeXs[i] == snakeXs[i-1])) {
				
				if((snakeYs[i] == snakeYs[i + 1] && snakeYs[i] < snakeYs[i-1])
						|| (snakeYs[i] < snakeYs[i + 1] && snakeYs[i] == snakeYs[i-1])) {
					
					g2.drawImage(snakeDownToRight, snakeXs[i], snakeYs[i], this);
					
				} else {
					g2.drawImage(snakeUpToRight, snakeXs[i], snakeYs[i], this);
				}
				
			}
		}

	}
	
	private void move() {

		for (int i = snakeSize; i > 0; i--) {
			snakeXs[i] = snakeXs[(i - 1)];
			snakeYs[i] = snakeYs[(i - 1)];
		}

		if (leftDirection == 1) {
			snakeXs[0] -= DOT_SIZEXY;
		}

		if (rightDirection == 1) {
			snakeXs[0] += DOT_SIZEXY;
		}

		if (upDirection == 1) {
			snakeYs[0] -= DOT_SIZEXY;
		}

		if (downDirection == 1) {
			snakeYs[0] += DOT_SIZEXY;
		}
	}

	private void snakeIntersection() {

		for (int i = snakeSize; i > 0; i--) {

			if ((i > 4) && (snakeXs[0] == snakeXs[i]) && (snakeYs[0] == snakeYs[i])) {
				isAlive = false;
			}
		}

		if (snakeXs[0] >= FIELD_SIZEX) {
			isAlive = false;
		}

		if (snakeXs[0] < 0) {
			isAlive = false;
		}

		if (snakeYs[0] >= FIELD_SIZEY) {
			isAlive = false;
		}

		if (snakeYs[0] < 0) {
			isAlive = false;
		}

		if (!isAlive) {
			timer.stop();
			velocityUpTimer.stop();
		}
	}

	private void foodIntersection() {

		if ((snakeXs[0] == foodX) && (snakeYs[0] == foodY)) {

			snakeSize++;
			makeFood();
		}
	}
	
	public void actionPerformed(ActionEvent e) {

		foodIntersection();
		snakeIntersection();
		move();

		repaint();
	}
}
