package Model;

/**
 * The Paddle Model class
 * @author Nima
 *
 */
public class Paddle {
	
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
	 * sets the x-position of the paddle
	 * @param x
	 */
	public void setX(double x){
		this.x = x;
	}

	/**
	 * gets the current y-position
	 * @return
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * sets the y-position of the paddle
	 * @param y
	 */
	public void setY(double y){
		this.y = y;
	}
	
}
