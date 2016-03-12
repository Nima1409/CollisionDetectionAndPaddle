package Controller;

import Model.Model;
import Model.Ball;
import Model.Bricks;
import Model.Paddle;
import View.BrickView;
import View.PaddleView;
import View.BallView;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.*;


public class Controller extends GraphicsProgram{
	
	private Ball ball;
	private Paddle paddle;
	private Bricks bricks;
	private Model model;
	private BrickView brickView;
	private PaddleView paddleView;
	private BallView ballView;
	
	private RandomGenerator random = new RandomGenerator();
	
	private double vx = random.nextDouble(1.0 , 4.0); 
	private double vy = 4;
	
	private double lastX;
	
	private static final int WIDTH = 405;
	private static final int HEIGHT = 600;
	
	public void run(){
		
		setSize(WIDTH,HEIGHT);
		invalidate();
		
		waitForClick();
		
		Setup();
		
		if (random.nextBoolean(0.5)) {
			vx = -vx; 
		}
		
		while(true){
			BallMovement();
		}
	}
	
	public void Setup(){
		
		ball = new Ball();
		ball.setX( (400 / 2));
		ball.setY( 300 );
		
		paddle = new Paddle();
		paddle.setX( (400 / 2) - 35 );
		paddle.setY( getHeight() - 80 );
		
		model = new Model(ball,paddle);
		
		brickView = new BrickView(model);
		add(brickView);
		
		paddleView = new PaddleView(model,paddle);
		add(paddleView);
		
		ballView = new BallView(model,ball);
		add(ballView);
		
		addMouseListeners();
	
	}
	
	public void BallMovement(){
		
		ballView.move(vx, vy);
		
		if((ballView.getX() + 200 <= 0) || (ballView.getX() >= getWidth() - 220)){
			vx = -vx;
		}
		
		if(ballView.getY() <= - 300){
			vy = -vy;
		}
		
		if(ballView.getY() + 320 >= getHeight()){
			GameOver();
		}
		
		GObject collider = getCollidingObject();
		
		if(collider == paddleView){
			
				vy = -vy;
			
		}
		
		else if (collider != null) {
			remove(collider); 
			vy = -vy;
		}
		
		pause(10);
		
	}
	
	private GObject getCollidingObject() {
		
		//Did the ballView origin(upper left) hit anything?
		if((getElementAt(ballView.getX(), ballView.getY())) != null) {
	         return getElementAt(ballView.getX(), ballView.getY());
	      }
		//Did the ballView upper right hit anything?
		else if (getElementAt( (ballView.getX() + 20), ballView.getY()) != null ){
	         return getElementAt(ballView.getX() + 20, ballView.getY());
	      }
		//Did the ballView bottom left hit anything?
		else if(getElementAt(ballView.getX(), (ballView.getY() + 20)) != null ){
	         return getElementAt(ballView.getX(), ballView.getY() + 20);
	      }
		//Did the ballView bottom right hit anything?
		else if(getElementAt((ballView.getX() + 20), (ballView.getY() + 20)) != null ){
	         return getElementAt(ballView.getX() + 20, ballView.getY() + 20);
	      }
		//return null because there was no collision detection
		else{
	         return null;
	      }
	}
	
	/**
	 * Tracks the Mouse events
	 */
	public void mouseMoved(MouseEvent e) {
		/* The mouse tracks the middle point of the paddle. 
		 * If the middle point of the paddle is between half paddle width of the screen
		 * and half a paddle width before the end of the screen, 
		 * the x location of the paddle is set at where the mouse is minus half a paddle's width, 
		 * and the height remains the same
		 */
		
		lastX = e.getX();
		
		if ( (e.getX() > 30) && (e.getX() < getWidth() - 30) ) {
			
			paddleView.move(lastX - 170, paddleView.getY());
			
		}
		
	}
}
