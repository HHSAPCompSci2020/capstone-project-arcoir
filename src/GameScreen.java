import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Toolkit;

import processing.core.PApplet;
import processing.core.PImage;

// modfied from Mr. Shelby's SecondScreen class
public class GameScreen extends Screen {

	// Fields

	private Character c;
	private ArrayList<Ground> g;
	private Dashboard dash;
	private DrawingSurface surface;
	private Animation idleR, idleL, cWR, cWL;
	private Rectangle switchButton;
	private PImage bg;
	private boolean isRight;
	private ArrayList <Character> enemies;


	// Constructor
	public GameScreen(DrawingSurface surface) {
		isRight = true;
		this.surface = surface;
		idleR = new Animation(300);
		idleL = new Animation(300);
		cWR = new Animation(100);
		cWL = new Animation(100);

		switchButton = new Rectangle(100, 100, 100, 100);
		g = new ArrayList<Ground>();
		g.add(new Ground(new Rectangle(0, 450, 1000, 20)));

	}

	// Methods
	public void setup() {
		bg = surface.loadImage("resources/maincharacter/bg2.png");
		loadCAnims();
		
		c = new Character(idleR, 3, 100, 100);
	}

	private void loadCAnims() {
		idleR.addFrame(surface.loadImage("resources/maincharacter/idleR.png"));
		
		idleL.addFrame(surface.loadImage("resources/maincharacter/idleL.png"));
		
		cWR.addFrame(surface.loadImage("resources/maincharacter/walkright/SMWalkRight1.png"));
		cWR.addFrame(surface.loadImage("resources/maincharacter/walkright/SMWalkRight2.png"));
		cWR.addFrame(surface.loadImage("resources/maincharacter/walkright/SMWalkRight3.png"));
		cWR.addFrame(surface.loadImage("resources/maincharacter/walkright/SMWalkRight4.png"));

		cWL.addFrame(surface.loadImage("resources/maincharacter/walkleft/SMWalkLeft1.png"));
		cWL.addFrame(surface.loadImage("resources/maincharacter/walkleft/SMWalkLeft2.png"));
		cWL.addFrame(surface.loadImage("resources/maincharacter/walkleft/SMWalkLeft3.png"));
		cWL.addFrame(surface.loadImage("resources/maincharacter/walkleft/SMWalkLeft4.png"));
	}

	public void draw() {

		surface.pushStyle();

		while (bg.width != this.surface.width || bg.height != this.surface.height)
			bg.resize(this.surface.width, this.surface.height);

		surface.background(bg); // Clear the screen with a white background

		surface.stroke(0); // Set line drawing color to white
		surface.noFill();

//		surface.rect((float) c.getX(), (float) c.getY(), (float) c.getWidth(), (float) c.getHeight());
//		surface.rect(x,y,30,30);
		c.draw(surface);
		for (Ground ground : g) {
	//		ground.drawGround(surface);
		}

		surface.fill(0);
		surface.text("Move: Arrow keys", 10, 30);
		surface.text("Menu: Space", 10, 50);

		surface.popStyle();

		surface.rect(switchButton.x, switchButton.y, switchButton.width, switchButton.height);

		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			// c.setAnimation(cWL);
			c.translate(-1);
		}
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			c.translate(1);
		}
		if (surface.isPressed(KeyEvent.VK_UP)) {
			c.jump();
		}

		if (c.getVelX() > 0.5 && c.getSurfaceState()) {
			c.setAnimation(cWR);
			isRight = true;
		} else if (c.getVelX() < -0.5 && c.getSurfaceState()) {
			c.setAnimation(cWL);
			isRight = false;
		} else {
			if(isRight)
				c.setAnimation(idleR);
			else
				c.setAnimation(idleL);

		}

		c.act(g);
//
//
//		if (surface.isPressed(KeyEvent.VK_SPACE)) {
//			surface.switchScreen(ScreenSwitcher.SCREEN1);
//		}
	}
	
	public void drawEnemies(int i) {
		surface.fill(0, 0, 0);
		surface.rect(i, 100, 40, 90);
		
	}
	
	public void spawnEnenimes() {
	
		for(int i=0; i< enemies.size(); i++) {
			enemies.add(this.c);
			this.drawEnemies(i*10);
			
		}
		
	}

	public void mousePressed(Point p) {
		if (surface.mouseButton == surface.LEFT) {

			if (switchButton.contains(p))
				surface.switchScreen(ScreenSwitcher.DRAWINGSCREEN);

		}
	}
}
