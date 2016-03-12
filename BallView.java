package View;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

import Model.Model;
import Model.Ball;

public class BallView extends GCompound {
	
	private Model model;
	private GOval ball;
	
	public BallView(Model model, Ball ballposition){
		this.model = model;
		
		ballposition = model.getBall();
		double xball = ballposition.getX();
		double yball = ballposition.getY();
		
		GOval ball = new GOval(xball,yball,20,20);
		ball.setFilled(true);
		ball.setFillColor(Color.BLUE);
		add(ball);
	}
	
}
