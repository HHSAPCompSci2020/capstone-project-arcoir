import java.awt.Image;
import java.awt.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Emily Zhang
 *
 */
public class Ground {
	
	// Fields
	private PImage image;
	private Rectangle ground;
	
	// Constructor
	
	public Ground(Rectangle ground) {
		this.ground = ground;
		image = null;
	}
	
	// Methods 
	
	
	public Rectangle getRect() {
		return ground;
	}
	
	public void drawGround(PApplet marker) {
		//marker.rect(ground.x, ground.y, ground.width, ground.height);
		if(image != null) {
			marker.image(image, ground.x, ground.y);
		}
	}
	
	public void setImage(PImage i) {
		image = i;
	}
	
	public void translate(int amnt) {
		ground.setLocation(ground.x + amnt, ground.y);
	}
	
}
