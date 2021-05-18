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
	
	
	public void drawGround(PApplet marker, int topLeft, int topRight, int bottomLeft, int bottomRight) {
		marker.rect(topLeft, topRight, bottomLeft, bottomRight);
		
	}

	
}
