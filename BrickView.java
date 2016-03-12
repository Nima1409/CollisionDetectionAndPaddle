package View;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

import Model.Model;
import Model.Bricks;

/**
 * Creates a simple Brick
 * @author Nima
 *
 */
public class BrickView extends GCompound {
	
	//Fields
	private Model model;
	private GRect bricks;
	
	/**
	 * Creates a new Brick on given coordinates and colors
	 * @param model the Model to get the Data of the Scene
	 */
	public BrickView(Model model){
		this.model = model;
		
		//Going through every Index in the ArrayList and getting 
		//the Bricks position and color
		for(Bricks bricks2 : model.getBricks()){
		double xbricks = bricks2.getX();
		double ybricks = bricks2.getY();
		Color color = bricks2.getColor();
		
		//Creating a new Brick(Instance)
		GRect bricks = new GRect(xbricks,ybricks,60,15);
		bricks.setFilled(true);
		bricks.setFillColor(color);
		add(bricks);
		}
	}
	
}