package View;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

import Model.Model;
import Model.Ball;

/**
 * Creates a simple Ball
 * @author Nima
 *
 */
public class BallView extends GCompound {
	
	//Fields
	private Model model;
	private GOval ball;
	
	/**
	 * Creates a new Ball on given coordinates and color
	 * @param model The Model to get the Data of the Scene
	 * @param ballposition The current Ball position
	 */
	public BallView(Model model, Ball ballposition){
		this.model = model;
		
		//Getting the current position of the Ball
		ballposition = model.getBall();
		double xball = ballposition.getX();
		double yball = ballposition.getY();
		
		//Constructing the new Ball(Instance)
		GOval ball = new GOval(xball,yball,20,20);
		ball.setFilled(true);
		ball.setFillColor(Color.BLUE);
		add(ball);
	}
	
}