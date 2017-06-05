/**
 * 
 */
package connect4;

/**
 * Jun 5, 2017
 * 
 * @author Andrew Niklas
 *
 */
public class Position {

	private char x;
	private int y;

	
	/**
	 * 
	 */
	public Position(char x, int y) {
		this.x = x;
		this.y = y;
	}


	
	public char getX() {
		return x;
	}


	
	public int getY() {
		return y;
	}
	
	
}
