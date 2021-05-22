import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Rectangle;

/**
 * 
 * @author lindsayqin
 *
 */
public class Dashboard {

	// Fields
	private Map realMap;
	private Rectangle help, back; 
	private Help helpWindow;
	private PImage helpIcon, backIcon;
	
	// Constructor
	/**
	 * 
	 * @param x coordinate of top left corner of dashboard
	 * @param y coordinate of top left corner of dashboard
	 * @param width of dashboard
	 * @param height of dashboard
	 */
	public Dashboard (int x, int y, int width, int height, PImage image1, PImage image2) {
		help = new Rectangle(760, 460, 30, 30);
		back = new Rectangle (10, 460, 30, 30);
		helpIcon = image1;
		helpWindow = new Help();
		backIcon = image2;
	}	
	
	public void draw(PApplet marker) {
		//load images
		marker.image(helpIcon, help.x, help.y, help.width, help.height);
		marker.image(backIcon, back.x, back.y, back.width, back.height);
	}
	
	public void mousePressed(double x, double y, DrawingSurface surface) {
		if (help.contains(x, y)) {
			helpWindow.show();
		}
		if (back.contains(x, y)) {
			surface.switchScreen(ScreenSwitcher.MENUSCREEN);
		}
		
	}
			
	}
