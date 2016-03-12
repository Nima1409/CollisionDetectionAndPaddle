package Model;

import java.awt.Color;

/**
 * The Brick Model class
 * @author Nima
 *
 */
public class Bricks {
	
	private double x;
	private double y;
	private Color color;
	
	/**
	 * Constructor for the Bricks
	 * @param x determines the x-position
	 * @param y determines the y-position
	 * @param color determines the color of the brick
	 */
	public Bricks(double x, double y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**
	 * returns the current x-position
	 * @return
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * returns the current y-position
	 * @return
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * returns the current color
	 * @return
	 */
	public Color getColor(){
		return color;
	}
	
}
