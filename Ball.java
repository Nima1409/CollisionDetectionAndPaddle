package Model;

/**The Ball Model class
 * 
 * @author Nima
 * 
 */
public class Ball {
	
	private double x;
	private double y;
	
	/**
	 * returns the current x-position
	 * @return
	 */
	public double getX(){
		return x;
	}

	/**
	 * sets the x-position of the ball
	 * @param x
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * returns the current y-position
	 * @return
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * sets the y-position of the ball
	 * @param y
	 */
	public void setY(double y){
		this.y = y;
	}
	
}
