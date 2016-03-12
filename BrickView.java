package View;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

import Model.Model;
import Model.Bricks;

public class BrickView extends GCompound {
	
	private Model model;
	private GRect bricks;
	
	public BrickView(Model model){
		this.model = model;
		
		for(Bricks bricks2 : model.getBricks()){
		double xbricks = bricks2.getX();
		double ybricks = bricks2.getY();
		Color color = bricks2.getColor();
		
		GRect bricks = new GRect(xbricks,ybricks,60,15);
		bricks.setFilled(true);
		bricks.setFillColor(color);
		add(bricks);
		}
	}
	
}
