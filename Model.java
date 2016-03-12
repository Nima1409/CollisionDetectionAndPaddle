package Model;

import java.awt.Color;
import java.util.ArrayList;
import Model.Bricks;
import Model.Ball;
import Model.Paddle;

public class Model {
	
	//Reference of each Model
	private Ball ball;
	private Paddle paddle;
	
	//The Color in use
	private Color color;
	
	//The ArrayList containing the Bricks
	private ArrayList<Bricks> bricks = new ArrayList<Bricks>();
	
	/**
	 * The Constructor for the Data
	 * @param ball The Data of the Ball
	 * @param paddle The Data of the Paddle
	 */
	public Model(Ball ball,Paddle paddle){
		this.ball = ball;
		this.paddle = paddle;
		
		//Getting the coordinates of Ball and Paddle
		ball.getX();
		ball.getY();
		paddle.getX();
		paddle.getY();
		
		//Loop for Brick Data
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 6; j++){
				
				//The given coordinates of each brick (x * j) && (y * i)
				double x = 10 + (65 * j);
				double y = 10 + (30 * i);
				
				//switching the layer for change in Color
				switch(i){
					case 0: color = new Color(255, 0, 0);
						break;
					case 1: color = new Color(255,200,0);
						break;
					case 2: color = new Color(255,255,0);
						break;
					case 3: color = new Color(0,255,0);
						break;
					case 4: color = new Color(0,255,255);
						break;
					default:
						break;
				}
				
				Object brick = new Bricks(x,y, color);
				bricks.add( (Bricks) brick);
			}
		}
	}
	/**
	 * Returns the brick Data in the ArrayList
	 * @return
	 */
	public ArrayList<Bricks> getBricks(){
		return bricks;
	}

	/**
	 * Returns the Ball Data 
	 * @return
	 */
	public Ball getBall(){
		return ball;
	}
	
	/**
	 * Returns the Paddle Data
	 * @return
	 */
	public Paddle getPaddle(){
		return paddle;
	}
}
