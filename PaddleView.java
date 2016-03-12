package View;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

import Model.Model;
import Model.Paddle;

/**
 * Creates a simple Paddle
 * @author Nima
 *
 */
public class PaddleView extends GCompound {

	//Fields
	private Model model;
	private GRect paddle;
	
	/**
	 * Creates a new Paddle on the given coordinates and color
	 * @param model The Model to get the Data of the Scene
	 * @param paddleposition The current Paddle position
	 */
	public PaddleView(Model model,Paddle paddleposition){
		this.model = model;
		
		//Getting the current position of the Paddle
		paddleposition = model.getPaddle();
		double xpaddle = paddleposition.getX();
		double ypaddle = paddleposition.getY();
		
		//Constructing the new Paddle(Instance)
		GRect paddle = new GRect(xpaddle,ypaddle,60,15);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLUE);
		add(paddle);
		
	}
	
}
