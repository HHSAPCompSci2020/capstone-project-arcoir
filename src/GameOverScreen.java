import processing.core.PImage;
import java.awt.Point;
import java.awt.Rectangle;

public class GameOverScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage gameOver;
	private Rectangle menu;
	private Dashboard dash;
	
	public GameOverScreen (DrawingSurface surface) {
		this.surface = surface;
		menu = new Rectangle(300, 270, 200, 40);
	
	}
	
	public void setup() {
		gameOver = surface.loadImage("resources/arcoir/gameOver.gif");		
		dash = new Dashboard(DRAWING_WIDTH * 2/3, DRAWING_HEIGHT - DRAWING_WIDTH/20 - 20, DRAWING_WIDTH, DRAWING_HEIGHT, 
				surface.loadImage("resources/dash/help/helpIcon.gif"), surface.loadImage("resources/dash/back.gif"));

	}
	
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
	}
	
	public void mousePressed(Point click) {
		if (surface.mouseButton == surface.LEFT) {
			if (menu.contains(click.x, click.y))
				surface.switchScreen(0);
			dash.mousePressed(click.x, click.y, surface, 0, false);
		}
	}
}
