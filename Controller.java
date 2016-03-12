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

/**
 * The Main Class.
 * To apply the MVC pattern to the Breakout Application we firstly wrote our Model.
 * The Model is the Database that contains all of the data like for e.g. the coordinates of Objects,
 * but without knowing that these Objects even exist. The Model could be applied to every
 * other Project without going under heavy rework.
 * 
 * To do so, we wrote for every Object that will be needed in the Breakout Application a Model class,
 * with getters and setters of the needed Data, like getX() and setX(), two main methods for the object-oriented
 * coding.
 * We combined all 3 Models in 1 Main Model class, so that we need only 1 Database for the whole Application.
 * In Model we just get and set the coordinates of the Paddle and the Ball, and return these Data in their own Methods,
 * thats why we declared the Fields as private, since the end result will be returned public.
 * For the Bricks we thought it would be better to add them into one ArrayList.
 * This is memory efficient when passing these Objects around, even if it isn't really needed in our Application
 * since it is a small Project. These Bricks will are also provided with a specific color, since both of the
 * iterative loops are displaying our Brick layers. The Model does not get any information about the Object or whatsoever.
 * 
 * For the View we decided to create 3 different classes. For later purposes it is much easier
 * to distinguish between the different templates (Views), for e.g. if we want to handle actions.
 * Each View extends GCompound, so that GObject as well as GCanvas resources are usable 
 * (As well as it is needed for the Bricks, since GCompound is a collection of Graphical Objects).
 * In PaddleView and BallView we created class specific (private) references of their Models(Data),
 * so that their models are usable.
 * We decided to create the Instance in the Constructor(with the model and their specific
 * model as their parameters).
 * For the BricksView in the Constructor we go into an iterative-statement to get every Data in the 
 * ArrayList (at the Index).
 * The View classes only have access to the Model classes, so that the created Instances have coordinates
 * and colors.
 * 
 * Usually there is a Main class other than the Controller class, but for our
 * simple project there was no need to ( also there is not "the" mvc pattern, but rather than
 * a convention which has no strict ruling).
 * The Controller imports every other class, since it "rules" about everything, and we created
 * for each a reference to work with. 
 * The controller extends GraphicsProgram to use the incredible and powerful resources 
 * of the acm library given by the stanford university.
 * A pressToPlay Label to the Overlay for the beginning, a random generator for the
 * ball velocity on the x axis, since it makes the animation a little bit "rounder",
 * and a velocity on the y axis initialized with 4. All of these are private,
 * since we only use them in the Controller and are not needed outside of the class.
 * Then we declared the WIDTH and HEIGHT of the Application, which needed to be static, a class
 * variable, because it needs to be created before the application starts.
 * 
 * In the run method we set up the size of the Application and invalidate the container.
 * Then we created an instance of our GLabel , which is the Overlay.
 * The waitForClick() method is used to prevent the game for crashing etc. if it is 
 * opened in the background, this ensures that the user has really started the game.
 * Then we called the Setup() method, which creates instances of the models, sets each
 * coordinates and adds the template(object).
 * The conditional-statement in the run() method is an endless loop to ensure a flawless
 * animation of the ball movement.
 * The BallMovement() method works with positions, we didnt want to make an edge frame around 
 * the screen and detect a collision, but work with coordinates and do some math.
 * Whenever a maximum/minimum is reached, the velocity is multiplied with -1, to get a bounce off
 * effect.
 * 
 * @author Nima
 *
 */
public class Controller extends GraphicsProgram{
	
	//References of the Models/Views
	private Ball ball;
	private Paddle paddle;
	private Bricks bricks;
	private Model model;
	private BrickView brickView;
	private PaddleView paddleView;
	private BallView ballView;
	
	//The Label for the pressToPlay Button
	private GLabel pressToPlay;
	private GLabel GameOver;
	
	private boolean running;
	
	//Random generator for the velocity on the x axis of the ball
	private RandomGenerator random = new RandomGenerator();
	
	//The velocity of the ball
	private double vx = random.nextDouble(1.0 , 4.0); 
	private double vy = 4;
	
	private double lastX;
	
	//The Width and Height of the Application
	private static final int WIDTH = 405;
	private static final int HEIGHT = 600;
	
	/**
	 * The Main method, running the application
	 */
	public void run(){
		//Setting the Size of the Application on Width = 405, Height = 600
		setSize(WIDTH,HEIGHT);
		invalidate();
		running = true;
		
		//Constructing the Label for the beginning of the Scene
		pressToPlay = new GLabel("Click to Play", getWidth()/2, getHeight()/2);
		Font mediumFont = new Font("Sansserif",Font.PLAIN, 36);
		pressToPlay.setFont(mediumFont);
		pressToPlay.setColor(Color.BLACK);
		add(pressToPlay);
		
		//ACM specific method for pausing the Scene
		waitForClick();
		
		//Calling the objects after Click was made
		Setup();
		
		if (random.nextBoolean(0.5)) {
			vx = -vx; 
		}
		
		//Endless loop to create a ball movement
		while(true){
			BallMovement();
			if(!running){
				break;
			}
		}
	}
	
	/**
	 * Setup for the Scene
	 */
	public void Setup(){
		
		//removes the pressToPlay Label after Click was made
		remove(pressToPlay);
		
		//Instance of Ball
		//Setting the coordinates for the Ball (Data)
		ball = new Ball();
		ball.setX( (400 / 2));
		ball.setY( 300 );
		
		//Instance of Paddle
		//Setting the coordinates for the Paddle (Data)
		paddle = new Paddle();
		paddle.setX( (400 / 2) - 35 );
		paddle.setY( getHeight() - 80 );
		
		//Creating the Model with ball and paddles data
		model = new Model(ball,paddle);
		
		//Creating the Object of Bricks
		brickView = new BrickView(model);
		add(brickView);
		
		//Creating the Object of Paddle
		paddleView = new PaddleView(model,paddle);
		add(paddleView);
		
		//Creating the Object of Ball
		ballView = new BallView(model,ball);
		add(ballView);
		
		//Adding MouseListener Events to the Scene
		addMouseListeners();
	
	}
	
	public void GameOver(){
		running = false;
		
		remove(paddleView);
		remove(brickView);
		remove(ballView);
		
		GameOver = new GLabel("Game Over", getWidth()/2 - 100, getHeight()/2);
		Font mediumFont = new Font("Sansserif",Font.PLAIN, 36);
		GameOver.setFont(mediumFont);
		GameOver.setColor(Color.BLACK);
		add(GameOver);
		
	}
	
	/**
	 * Set ups the physics of the Ball
	 */
	public void BallMovement(){
		
		//The Ball moves with vx on the x axis and vy on the y axis
		ballView.move(vx, vy);
		
		//If the Ball is on the left Side or on the right Side of the Screen,
		// the velocity should change *-1
		if((ballView.getX() + 200 <= 0) || (ballView.getX() >= getWidth() - 220)){
			vx = -vx;
		}
		
		//If the Ball is on the upper Side of the Screen,
		// the velocity should change *-1
		if(ballView.getY() <= - 300){
			vy = -vy;
		}
		
		//If the Ball is on the bottom Side of the Screen,
		// past the Paddle, its GameOver
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
		
		//A small delay to get a flawless animation of the Ball
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
