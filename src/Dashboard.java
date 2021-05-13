import processing.core.PApplet;
import java.awt.Rectangle;

public class Dashboard extends PApplet{

	// Fields
	private Map realMap;
	private Rectangle help, map, pause; 
	
	// Constructor
	public Dashboard () {
		help = new Rectangle();
		map = new Rectangle();
		pause = new Rectangle();
	}
	
	//Methods
	public void draw() {
		//load images
	}
	
	public void mousePressed() {
		if (help.contains(mouseX, mouseY)) {
			
		}
		if (map.contains(mouseX, mouseY)) {
			
		}
		if (pause.contains(mouseX, mouseY)) {
			
		}
	}
	
	private void createHelp() {
		
	}
	
	
}
