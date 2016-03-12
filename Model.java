package Model;

import java.awt.Color;
import java.util.ArrayList;
import Model.Bricks;
import Model.Ball;
import Model.Paddle;

public class Model {
	
	private Ball ball;
	private Paddle paddle;
	
	private Color color;
	
	private ArrayList<Bricks> bricks = new ArrayList<Bricks>();
	
	public Model(Ball ball,Paddle paddle){
		this.ball = ball;
		this.paddle = paddle;
		
		ball.getX();
		ball.getY();
		paddle.getX();
		paddle.getY();
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 6; j++){
				
				double x = 10 + (65 * j);
				double y = 10 + (30 * i);
				
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
	
	public ArrayList<Bricks> getBricks(){
		return bricks;
	}

	public Ball getBall(){
		return ball;
	}
	
	public Paddle getPaddle(){
		return paddle;
	}
}
