import processing.core.PApplet;
import java.awt.Rectangle;

public class Dashboard {

	// Fields
	private Map realMap;
	private Rectangle help, map, pause; 
	private Help helpWindow;
	
	// Constructor
	public Dashboard () {
		help = new Rectangle(10, 10, 50, 50);
		map = new Rectangle();
		pause = new Rectangle();	
		
		helpWindow = new Help();
	}
	
	//Methods
	
	
	public void draw(PApplet marker) {
		//load images
		
		marker.rect(help.x, help.y, help.width, help.height);
	}
	
	public void mousePressed(double x, double y) {
		if (help.contains(x, y)) {
			helpWindow.show();
		}
		if (map.contains(x, y)) {
			
		}
		if (pause.contains(x, y)) {
			
		}
	}
			
	}
