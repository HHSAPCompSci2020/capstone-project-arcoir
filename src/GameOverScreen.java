import processing.core.PImage;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * The GameOverScreen class presents the display that appears when the character loses all three of its lives. 
 * It prompts the user to exit back to the main menu in order to customize or play again.   
 * 
 * @author Emily Zhang
 */
public class GameOverScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage gameOver;
	private Rectangle menu;
	private Dashboard dash;
	
	/**
	 * Constructs a GameOverScreen.
	 * 
	 * @param surface The PApplet to draw the GameOverScreen with.
	 */
	public GameOverScreen (DrawingSurface surface) {
		this.surface = surface;
		menu = new Rectangle(300, 270, 200, 40);
	}
	
	/**
	 * Sets up the GameOverScreen.
	 */
	public void setup() {
		gameOver = surface.loadImage("resources/arcoir/gameOver.gif");		
		dash = new Dashboard(true, true, surface.loadImage("resources/dash/help/helpIcon.gif"), surface.loadImage("resources/dash/back.gif"));
	}
	
	/**
	 * Draws the GameOverScreen.
	 */
	public void draw () {
		surface.image(gameOver, 0, 0);

		surface.pushStyle();
		surface.noFill();
		surface.stroke(255);
		surface.strokeWeight(4);
		surface.rect(menu.x, menu.y, menu.width, menu.height);

		surface.fill(255);
		surface.textSize(20);
		surface.text("RETURN TO MENU", menu.x + 12, menu.y + 27);
		surface.popStyle();
		
		dash.draw(surface);
	}
	
	/**
	 * Executes methods when the mouse is left clicked.
	 */
	public void mousePressed(Point click) {
		if (surface.mouseButton == surface.LEFT) {
			if (menu.contains(click.x, click.y))
				surface.switchScreen(0);
			dash.mousePressed(click.x, click.y, surface, 0, false, false);
		}
	}
}
