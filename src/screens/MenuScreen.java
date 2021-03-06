
package screens;
import processing.core.PImage;

import dashboard.Dashboard;
import arcoir.DrawingSurface;
import arcoir.ScreenSwitcher;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * The MenuScreen class displays the initial screen when the user first runs the program. 
 * It prompts the player to start the game and either begin the customization of their character or the game. 
 * 
 * @author Lindsay Qin
 */
public class MenuScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage initial, credits;
	private boolean clicked1, clicked2;
	private Rectangle start, customize, play;
	private PImage one, two, three, four, five, six, seven;
	private int clickCount;
	private Dashboard dash;
	
	/**
	 * Constructs a DrawingScreen object.
	 * 
	 * @param surface PApplet surface that uses Processing methods.
	 */
	public MenuScreen (DrawingSurface surface) {
		this.surface = surface;
		clicked1 = false;
		clicked2 = false;
		clickCount = 0;
		start = new Rectangle(320, 260, 160, 40);
		customize = new Rectangle (320, 275, 160, 40);
		play = new Rectangle (320, 330, 160, 40);
	}
	
	/**
	 * A method that runs as soon as the program starts
	 */
	public void setup() {
		initial = surface.loadImage("resources/arcoir/arcoir.gif");
		credits = surface.loadImage("resources/arcoir/arcoirCredits.gif");
		one = surface.loadImage("resources/intro/1.png");
		two = surface.loadImage("resources/intro/2.png");
		three = surface.loadImage("resources/intro/3.png");
		four = surface.loadImage("resources/intro/4.png");
		five = surface.loadImage("resources/intro/5.png");
		six = surface.loadImage("resources/intro/6.png");
		seven = surface.loadImage("resources/intro/7.png");
		
		dash = new Dashboard(false, false, surface.loadImage("resources/dash/help/helpIcon.gif"), 
				surface.loadImage("resources/dash/back.gif"));

	}
	
	/**
	 * Draws components of the menu to the screen.
	 */
	public void draw () {
		if (!clicked1) {
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
		} else if (clicked1 && !clicked2){
			if (clickCount == 0) {
				surface.image(one, 0, 0);
				surface.text("Left click anywhere to continue.", 260, 450);
			} else if (clickCount == 1) {
				surface.image(two, 0, 0);
			} else if (clickCount == 2) {
				surface.image(three, 0, 0);
			} else if (clickCount == 3) {
				surface.image(four, 0, 0);
			} else if (clickCount == 4) {
				surface.image(five, 0, 0);
			} else if (clickCount == 5) {
				surface.image(six, 0, 0);
			} else if (clickCount == 6) {
				surface.image(seven, 0, 0);
			}
			dash.draw(surface);
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
			surface.popStyle();
			
			dash.draw(surface);
		}
	}
	
	/**
	 * if the left mouse button if pressed, the dashboard's mousePressed button is called
	 * @param click The point with coordinates x and y on the screen that is clicked by the mouse 
	 */
	public void mousePressed(Point click) {
		if (surface.mouseButton == surface.LEFT) {
			dash.mousePressed(click.x, click.y, surface, 0, false, false);
			if (new Rectangle(760, 460, 30, 30).contains(click.x, click.y)) {
				clickCount--;
			} else if (new Rectangle(10, 460, 30, 30).contains(click.x, click.y)) {
				clickCount = -1;
			}
			if (clicked1 != true && start.contains(click.x, click.y)) {
				clicked1 = true;
			} else if (clicked1 && !clicked2){
				clickCount++;
				if (clickCount == 7) {
					clicked2 = true;
				}
			} else {
				if (customize.contains(click.x, click.y)) {
					surface.switchScreen(ScreenSwitcher.DRAWINGSCREEN);
				} else if (play.contains(click.x, click.y)) {
					surface.switchScreen(ScreenSwitcher.GAMESCREEN);
					surface.reset();
				}
			}
			if (new Rectangle(10, 460, 30, 30).contains(click.x, click.y))
				clicked1 = false;
		}
	}
}
