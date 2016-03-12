package View;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

import Model.Model;
import Model.Paddle;

public class PaddleView extends GCompound {

	
	private Model model;
	private GRect paddle;
	
	public PaddleView(Model model,Paddle paddleposition){
		this.model = model;
		
		paddleposition = model.getPaddle();
		double xpaddle = paddleposition.getX();
		double ypaddle = paddleposition.getY();
		
		GRect paddle = new GRect(xpaddle,ypaddle,60,15);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLUE);
		add(paddle);
		
	}
	
}
