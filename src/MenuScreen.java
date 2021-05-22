import processing.core.PImage;
import java.awt.Point;
import java.awt.Rectangle;

public class MenuScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage initial, credits;
	private boolean clicked;
	private Rectangle start, customize, play;
	
	public MenuScreen (DrawingSurface surface) {
		this.surface = surface;
		clicked = false;
		start = new Rectangle(320, 260, 160, 40);
		customize = new Rectangle (320, 275, 160, 40);
		play = new Rectangle (320, 330, 160, 40);
	}
	
	public void setup() {
		initial = surface.loadImage("resources/menu/arcoir.gif");
		credits = surface.loadImage("resources/menu/arcoirCredits.gif");
	}
	
	public void settings() {
		
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
		}
	}
}
