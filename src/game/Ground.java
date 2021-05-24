package game;
import java.awt.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Ground class enables the characters to remain along the same x-axis by a rectangle shape.
 * 
 * @author Emily Zhang
 */
public class Ground {
	
	// Fields
	private PImage image;
	private Rectangle ground;
	
	/**
	 * Creates a ground object.
	 * 
	 * @param ground The Rectangle to be used as the ground.
	 */
	public Ground(Rectangle ground) {
		this.ground = ground;
		image = null;
	}
		
	/**
	 * 
	 * @return the Ground object as a Rectangle.
	 */
	public Rectangle getRect() {
		return ground;
	}
	
	/**
	 * Draws the Ground.
	 * 
	 * @param marker the PApplet to draw the Ground with.
	 */
	public void drawGround(PApplet marker) {
		//marker.rect(ground.x, ground.y, ground.width, ground.height);
		if(image != null) {
			marker.image(image, ground.x, ground.y);
		}
	}
	
	/**
	 * Sets the ground's image.
	 * @param i The PImage to be added.
	 */
	public void setImage(PImage i) {
		image = i;
	}
	
	/**
	 * Translates the ground horizontally.
	 * 
	 * @param amnt The amount to translate the ground by.
	 */
	public void translate(int amnt) {
		ground.setLocation(ground.x + amnt, ground.y);
	}
	
}
