import processing.core.PImage;
import java.awt.Point;
import java.awt.Rectangle;

public class MenuScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage initial, credits;
	private boolean clicked;
	private Rectangle start, customize, play;
	private Dashboard dash;
	
	public MenuScreen (DrawingSurface surface) {
		this.surface = surface;
		clicked = false;
		start = new Rectangle(320, 260, 160, 40);
		customize = new Rectangle (320, 275, 160, 40);
		play = new Rectangle (320, 330, 160, 40);
	}
	
	public void setup() {
		initial = surface.loadImage("resources/arcoir/arcoir.gif");
		credits = surface.loadImage("resources/arcoir/arcoirCredits.gif");
		
		dash = new Dashboard(DRAWING_WIDTH * 2/3, DRAWING_HEIGHT - DRAWING_WIDTH/20 - 20, DRAWING_WIDTH, DRAWING_HEIGHT, 
				surface.loadImage("resources/dash/help/helpIcon.gif"), surface.loadImage("resources/dash/back.gif"));

	}
	
	public void draw () {
		if (!clicked) {
			surface.image(initial, 0, 0);
			
			surface.pushStyle();
			surface.noFill();
			surface.stroke(255);
			surface.strokeWeight(4);
			surface.rect(start.x, start.y, start.width, start.height);
			
			surface.fill(255);
			surface.textSize(20);
			surface.text("START", start.x + 46, start.y + 27);
			surface.popStyle();
		} else {
			surface.image(credits, 0, 0);
			
			surface.pushStyle();
			surface.noFill();
			surface.stroke(255);
			surface.strokeWeight(4);
			surface.rect(customize.x, customize.y, customize.width, customize.height);
			surface.rect(play.x, play.y, play.width, play.height);
			
			surface.fill(255);
			surface.textSize(20);
			surface.text("CUSTOMIZE", customize.x + 23, customize.y + 27);
			surface.text("PLAY", play.x + 55, play.y + 27);
			surface.popStyle();
			
			surface.pushStyle();
			surface.fill(255);
			surface.rect(760, 460, 30, 30);
			surface.rect(10, 460, 30, 30);
			dash.draw(surface);
			surface.popStyle();
		}
	}
	
	public void mousePressed(Point click) {
		if (surface.mouseButton == surface.LEFT) {
			if (clicked != true && start.contains(click.x, click.y)) {
				clicked = true;
			} else {
				if (customize.contains(click.x, click.y)) {
					surface.switchScreen(ScreenSwitcher.DRAWINGSCREEN);
				} else if (play.contains(click.x, click.y)) {
					surface.switchScreen(ScreenSwitcher.GAMESCREEN);
				}
			}
			dash.mousePressed(click.x, click.y, surface, 0, false, false);
			if (new Rectangle(10, 460, 30, 30).contains(click.x, click.y))
				clicked = false;
		}
	}
}
