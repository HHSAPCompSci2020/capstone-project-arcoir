import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Toolkit;

import processing.core.PApplet;
// modfied from Mr. Shelby's SecondScreen class
public class GameScreen extends Screen{

	//Fields
	
	private MainCharacter c;
	private Enemy e;
	private Ground g;
	private Dashboard dash;
	private DrawingSurface surface;
	Animation characterAnim;
	private Rectangle switchButton;
	
	// Constructor
	public GameScreen(DrawingSurface surface) {
		super(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		this.surface = surface;
		characterAnim =  new Animation(300);
		switchButton = new Rectangle (100, 100, 100, 100);
		
		
	}
	// Methods
	public void setup() {
		characterAnim.addFrame(surface.loadImage("resources/stickfigure.png"));
		c = new MainCharacter(characterAnim, 3, 100, 100);
	}
	
	public void draw() {
		surface.pushStyle();
		
		surface.background(255);   // Clear the screen with a white background
		surface.stroke(0);     // Set line drawing color to white
		surface.noFill();

		surface.rect((float)c.getX(), (float)c.getY(), (float)c.getWidth(), (float)c.getHeight());
//		surface.rect(x,y,30,30);
		
		surface.fill(0);
		surface.text("Move: Arrow keys",10,30);
		surface.text("Menu: Space",10,50);

		surface.popStyle();
		
		surface.rect(switchButton.x, switchButton.y, switchButton.width, switchButton.height);
		
//		if (surface.isPressed(KeyEvent.VK_LEFT))
//			
//		if (surface.isPressed(KeyEvent.VK_RIGHT))
//			x += 5;
//		if (surface.isPressed(KeyEvent.VK_UP))
//			y -= 5;
//		if (surface.isPressed(KeyEvent.VK_DOWN))
//			y += 5;
//
//
//		if (surface.isPressed(KeyEvent.VK_SPACE)) {
//			surface.switchScreen(ScreenSwitcher.SCREEN1);
//		}
	}
	
	public void mousePressed() {
		if (surface.mouseButton == surface.LEFT) {
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (switchButton.contains(p))
			surface.switchScreen(ScreenSwitcher.DRAWINGSCREEN);


	}	
	}	
}
