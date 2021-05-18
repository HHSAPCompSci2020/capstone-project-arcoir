import processing.core.PApplet;
import java.awt.Rectangle;

/**
 * 
 * @author lindsayqin
 *
 */
public class Dashboard {

	// Fields
	private Map realMap;
	private Rectangle help, map, pause; 
	private Help helpWindow;
	
	// Constructor
	/**
	 * 
	 * @param x coordinate of top left corner of dashboard
	 * @param y coordinate of top left corner of dashboard
	 * @param width of dashboard
	 * @param height of dashboard
	 */
	public Dashboard (int x, int y, int width, int height) {
		help = new Rectangle(x, y, width/20, width/20);
		map = new Rectangle(x + width/20, y, width/20, width/20);
		pause = new Rectangle(x + width/10, y, width/20, width/20);	
		
		helpWindow = new Help();
	}
	
	//Methods
	
	
	public void draw(PApplet marker) {
		//load images
		
		marker.rect(help.x, help.y, help.width, help.height);
//		marker.image(marker.loadImage("help icon.gif"), help.x, help.y);
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
