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
	private Scrollable background;
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
		bg.resize(this.surface.width, this.surface.height);
		background = new Scrollable(0, bg, 0, surface);
		
		c = new Character(idleR, 3, surface.width/2, 100);
		c.adjustImgShift(-45, -20);
		c.setMovesXAxis(false);
		
		enemies = new ArrayList<>();
		enemy = new Character(eL, 1, 600, 100);
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
		assignFrames();
		c.windowBoundary(this.DRAWING_WIDTH, this.DRAWING_HEIGHT);
		
//		System.out.println("Width: " + surface.width + ", Height: " + surface.height);

		background.update();
		background.draw(surface);
		surface.pushStyle();

	//	while (bg.width != this.surface.width || bg.height != this.surface.height)
	//	bg.resize(this.surface.width, this.surface.height);
		//surface.background(bg); // Clear the screen with a white background

		surface.stroke(0); // Set line drawing color to white
		surface.noFill();
		
	
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
		background.setSpeed(0);
		double currentX = c.getX();
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			for(Character enm : enemies) {
				//enm.translate(0.2);
			}
			c.translate(-1);
			background.setSpeed(-3);
		}
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			for(Character enm : enemies) {
				//enm.translate(-0.2);
			}
			c.translate(1);
			background.setSpeed(3);
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
			if(c.getX()  > enm.getX() - 10  && Math.abs(c.getX() - enm.getX()) < 350) {
				enm.translate(0.3);
				enemy.setAnimation(eR);
			} else if (c.getX()  < enm.getX()  && Math.abs(c.getX() - enm.getX()) < 350) {
				enm.translate(-0.3);
				enemy.setAnimation(eL);
			}
			
			if(c.intersects(enm)) {
				enm.attack(c);
				System.out.println(c.getLiveState());
			}
			
			enm.act(g);
			
		}
		
		
//
//
//		if (surface.isPressed(KeyEvent.VK_SPACE)) {
//			surface.switchScreen(ScreenSwitcher.SCREEN1);
//		}
	}
	
	
	
	private void assignFrames() {
		PImage[][] frames = surface.getFrames();
		if(surface.framesDone()) {
			for(int i = 0; i < frames.length; i++) {
				for(int j = 0; j < frames[i].length; j++) {
					if(i == 0) {
						cWR = new Animation(100);
						cWR.addFrame(frames[i][j]);
					} else if(i == 1) {
						idleR = new Animation(300);
						idleR.addFrame(frames[i][j]);
					} else if(i == 2) {
						cWL = new Animation(100);
						cWL.addFrame(frames[i][j]);
					} else if(i == 3) {
						idleL = new Animation(300);
						idleL.addFrame(frames[i][j]);
					}
				}
			}
		}
	} 

	public void mousePressed(Point p) {
		if (surface.mouseButton == surface.LEFT) {

			if (switchButton.contains(p))
				surface.switchScreen(ScreenSwitcher.MENUSCREEN);

		}
	}
}
