import java.awt.Toolkit;

public abstract class Screen {
	
public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	public Screen() {

		this.DRAWING_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.DRAWING_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		
	}
	
	public void mousePressed() {
		
	}
	
	public void mouseMoved() {
		
	}
	
	public void mouseDragged() {
		
	}
	
	public void mouseReleased() {
		
	}
	
	
}
