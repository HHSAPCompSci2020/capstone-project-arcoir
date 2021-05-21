import java.awt.Point;
import java.awt.event.KeyEvent;
import java.security.spec.ECField;
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
	private Animation idleR, idleL, cWR, cWL, eR, eL;
	private Rectangle switchButton;
	private PImage bg;
	private boolean isRight;
	private ArrayList <Character> enemies;
	private Character enemy;
	private int cLives;


	// Constructor
	public GameScreen(DrawingSurface surface) {
		isRight = true;
		this.surface = surface;
		idleR = new Animation(300);
		idleL = new Animation(300);
		cWR = new Animation(100);
		cWL = new Animation(100);
		eR = new Animation(300);
		eL = new Animation(300);

		switchButton = new Rectangle(100, 100, 100, 100);
		g = new ArrayList<Ground>();
		g.add(new Ground(new Rectangle(0, 450, 1000, 20)));

		cLives = 0;
	}

	// Methods
	public void setup() {
		bg = surface.loadImage("resources/maincharacter/bg2.png");
		loadCAnims();
		
		c = new Character(idleR, 3, 100, 100);
		c.adjustImgShift(-45, -20);
		
		enemies = new ArrayList<>();
		enemy = new Character(eR, 1, 100, 100);
		enemy.adjustImgShift(-8, -10);
		enemy.setHeight(40);
		enemies.add(enemy);
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
		
		eR.addFrame(surface.loadImage("resources/enemy/EnemyRunRight1.png"));
		eR.addFrame(surface.loadImage("resources/enemy/EnemyRunRight2.png"));
		
		eL.addFrame(surface.loadImage("resources/enemy/EnemyRunLeft1.png"));
		eL.addFrame(surface.loadImage("resources/enemy/EnemyRunLeft2.png"));

	}

	public void draw() {
		
		windowBoundary(1440, 847);
		
		System.out.println("Width: " + surface.width + ", Height: " + surface.height);


		surface.pushStyle();

		while (bg.width != this.surface.width || bg.height != this.surface.height)
			bg.resize(this.surface.width, this.surface.height);

		surface.background(bg); // Clear the screen with a white background

		surface.stroke(0); // Set line drawing color to white
		surface.noFill();
		
		
		
	//	surface.rect((float) c.getX(), (float) c.getY(), (float) c.getWidth(), (float) c.getHeight());
	//	surface.rect((float) enemy.getX(), (float) enemy.getY(), (float) enemy.getWidth(), (float) enemy.getHeight());

		//		surface.rect(x,y,30,30);
		c.draw(surface);
		enemy.draw(surface);

		for (Ground ground : g) {
	//		ground.drawGround(surface);
		}

		surface.fill(0);
		surface.text("Move: Arrow keys", 10, 30);
		surface.text("Menu: Space", 10, 50);

		surface.popStyle();

		surface.rect(switchButton.x, switchButton.y, switchButton.width, switchButton.height);

		if (surface.isPressed(KeyEvent.VK_LEFT)) {
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
		for(Character enm : enemies) {
			if(c.getX()  > enm.getX() - 10 ) {
				enm.translate(0.3);
				enemy.setAnimation(eR);
			} else if (c.getX()  < enm.getX() + 10) {
				enm.translate(-0.3);
				enemy.setAnimation(eL);
			}
			
			
			enm.act(g);
			
		}
		
		
//
//
//		if (surface.isPressed(KeyEvent.VK_SPACE)) {
//			surface.switchScreen(ScreenSwitcher.SCREEN1);
//		}
	}
	
	public boolean characterIntersect() {
		
		boolean hitboxIntersection = false;
		
		if((enemy.getHitBox().intersects(c.getHitBox()) || c.getHitBox().intersects(enemy.getHitBox())) && cLives >=0){
			cLives--;
			hitboxIntersection = true;
		} else {
			hitboxIntersection = false;
		}
		return hitboxIntersection;
		
	}
	
	public void windowBoundary(double width, double height) {
		
		if(c.getX()>width) {
			c.setX(width);
			c.setY(c.getY());
		}
		
		if(c.getX()<0) {
			c.setX(0);
			c.setY(c.getY());
		}
		
		if(c.getY()> height) {
			c.setX(c.getX());
			c.setY(height);
		}
		
		if(c.getY()<0) {
			c.setX(c.getX());
			c.setY(0);
		}
	}

	public void mousePressed(Point p) {
		if (surface.mouseButton == surface.LEFT) {

			if (switchButton.contains(p))
				surface.switchScreen(ScreenSwitcher.DRAWINGSCREEN);

		}
	}
}
