import java.awt.Image;
import java.awt.Rectangle;
import processing.core.PApplet;

public class Ground {
	
	// Fields
	private Image image;
	private Rectangle ground;
	
	// Constructor
	
	public Ground(Rectangle ground) {
		this.ground = ground;
	}
	
	// Methods 
	
	
	public Rectangle getRect() {
		return ground;
	}
	
	public void drawGround(PApplet marker) {
		marker.rect(ground.x, ground.y, ground.width, ground.height);
		
	}

	
}
