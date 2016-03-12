package Model;

import java.awt.Color;

public class Bricks {
	
	private double x;
	private double y;
	private Color color;
	
	public Bricks(double x, double y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public Color getColor(){
		return color;
	}
	
}
