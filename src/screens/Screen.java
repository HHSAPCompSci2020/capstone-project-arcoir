package screens;
import java.awt.Point;

/**
 * The Screen class initializes the window size to 800 by 500, and any changes to the window after will follow the same scale. 
 * 
 * @author Mr. Shelby
 */
public abstract class Screen {

	/**
	 * Width of the initial window size. Will be scaled to the user's screen.
	 */
	public final int DRAWING_WIDTH;
	
	/**
	 * Height of the initial window size. Will be scaled to the user's screen.
	 */
	public final int DRAWING_HEIGHT;
	
	/**
	 * Constructs a screen object.
	 */
	public Screen() {

		this.DRAWING_WIDTH = 800;
		this.DRAWING_HEIGHT = 500;
	
	}
	
	/**
	 * Sets up the screen.
	 */
	public void setup() {
		
	}
	
	/**
	 * Draws the screen.
	 */
	public void draw() {
		
	}
	
	/**
	 * Executes code when the mouse is pressed.
	 * 
	 * @param p The coordinates of the mouse press.
	 */
	public void mousePressed(Point p) {
		
	}
	
	/**
	 * Executes code when the mouse is moved.
	 * 
	 * @param p The coordinates of the mouse move.
	 */
	public void mouseMoved(Point p) {
		
	}
	
	/**
	 * Executes code when the mouse is dragged.
	 * 
	 * @param p The coordinates of the mouse drag.
	 */
	public void mouseDragged(Point p) {
		
	}
	
	/**
	 * Executes code when the mouse is released.
	 * 
	 * @param p The coordinates of the mouse release.
	 */
	public void mouseReleased(Point p) {
		
	}
	
	
}
