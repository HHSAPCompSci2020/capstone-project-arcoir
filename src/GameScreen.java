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

	private Entity c;
	private ArrayList<Ground> g;
	private Dashboard dash;
	private DrawingSurface surface;
	private Animation idleR, idleL, cWR, cWL, eR, eL;
	private PImage bg;
	private boolean isRight;
	private ArrayList <Entity> enemies;
	private int numEnemies;
	private Entity enemy;
	private Scrollable background;
	private int level;
	private int score;
	private int t;
	private boolean doneLoading;
	
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
		

		g = new ArrayList<Ground>();
		g.add(new Ground(new Rectangle(0, 450, 1000, 20)));

		score = 0;
		level = 1;
		
		t = 0;
		
		doneLoading = false;
		
		
	}

	// Methods
	public void setup() {
		
		loadCAnims();
		background = new Scrollable(0, bg, 0, surface);
		
		c = new Entity(idleR, 3, 64, 100);
		c.adjustImgShift(-45, -20);
		c.setMovesXAxis(false);
		
		enemies = new ArrayList<>();
		enemy = new Entity(eL, 1, 600, 100);
		enemy.adjustImgShift(-8, -10);
		enemy.setHeight(40);
		enemies.add(enemy);
		
		spawnEnemy();
		
		dash = new Dashboard(DRAWING_WIDTH * 2/3, DRAWING_HEIGHT - DRAWING_WIDTH/20 - 20, DRAWING_WIDTH, DRAWING_HEIGHT, 
				surface.loadImage("resources/dash/help/helpIcon.gif"), surface.loadImage("resources/dash/back.gif"));
	}

	private void loadCAnims() {
		bg = surface.loadImage("resources/bgs/bg2.jpeg");
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
		if (!doneLoading && surface.framesDone()) {
			assignFrames();
		} 
		c.windowBoundary(this.DRAWING_WIDTH, this.DRAWING_HEIGHT);
		
//		System.out.println("Width: " + surface.width + ", Height: " + surface.height);

		
		surface.pushStyle();

//		while (bg.width != this.surface.width || bg.height != this.surface.height)
//			bg.resize(this.surface.width, this.surface.height);
		//surface.background(bg); // Clear the screen with a white background
	//	background.setImage(bg);
		background.draw(surface);
		surface.stroke(0); // Set line drawing color to white
		surface.noFill();
		
	
		c.draw(surface);
		for (Entity e : enemies) {
			e.draw(surface);
			//System.out.println("drawing an enemy");
		}

		for (Ground ground : g) {
	//		ground.drawGround(surface);
		}

		surface.fill(0);
		surface.text("Move: Arrow keys", 10, 30);
		surface.text("Menu: Space", 10, 50);
		
		surface.text("Score: " + score, 700, 30);
		surface.text("Level: " + level, 700, 50);
		surface.text("Lives: " + c.getLives(), 700, 70);
		surface.popStyle();
		
		background.setSpeed(0);
		
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			for(Entity enm : enemies) {
				enm.translate(0.8);
			}
			c.translate(-1);
			background.setSpeed(-3);
		}
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			for(Entity enm : enemies) {
				enm.translate(-0.8);
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
		for(Entity enm : enemies) {
			if(c.getX()  > enm.getX() - 10  && Math.abs(c.getX() - enm.getX()) < 350) {
				enm.translate(0.4);
				enm.setAnimation(eR);
			} else if (c.getX()  < enm.getX()  && Math.abs(c.getX() - enm.getX()) < 350) {
				enm.translate(-0.4);
				enm.setAnimation(eL);
			}
			
			if(c.intersects(enm)) {
				enm.attack(c);
				System.out.println(c.getLiveState());
			}
			
			enm.act(g);
			
		}
		
		while (numEnemies<level) {
			spawnEnemy();
		}
		
//		System.out.println("num: " + numEnemies);
//		System.out.println("score: " + score);
//		System.out.println("level: " + level);
		
		updateLevel();
		
		countLives();

		if (surface.isPressed(KeyEvent.VK_SPACE)) {
			for (int i = 0; i<enemies.size(); i++) {
				if (c.intersects(enemies.get(i))) {
					enemies.remove(i);
					numEnemies--;
					score++;
				}
			}
		}
		
		
		background.update();
		

		dash.draw(surface);
		
		
		
//
//
//		if (surface.isPressed(KeyEvent.VK_SPACE)) {
//			surface.switchScreen(ScreenSwitcher.SCREEN1);
//		}
	}
	
	
	
	private void assignFrames() {
		PImage[][] frames = surface.getFrames();
		if(surface.framesDone()) {
			idleR = new Animation(300);
			idleL = new Animation(300);
			cWR = new Animation(100);
			cWL = new Animation(100);
			for(int i = 0; i < frames.length; i++) {
				for(int j = 0; j < frames[i].length; j++) {
					if(i == 0) {
						cWR.addFrame(frames[i][j]);
					} else if(i == 1) {
						idleR.addFrame(frames[i][j]);
					} else if(i == 2) {
						cWL.addFrame(frames[i][j]);
					} else if(i == 3) {
						idleL.addFrame(frames[i][j]);
					}
				}
			}
			c.setAnimation(idleR);
			doneLoading = true;
		}
	} 

	public void mousePressed(Point p) {
		if (surface.mouseButton == surface.LEFT) {
			dash.mousePressed(p.x, p.y, surface, 2, false);
		}
	}
	
	public void updateLevel() {
		if (score >= level) {
			level++;
			score = 0;
		}
	}
	
	public void spawnEnemy() {
		Entity spawnedEnemy = new Entity(eL, 1, (int)(Math.random()*500+100), 100);
		spawnedEnemy.adjustImgShift(-8, -10);
		spawnedEnemy.setHeight(40);
		enemies.add(spawnedEnemy);
		numEnemies++;
	}
	
	public void countLives() {
		for(int i=0; i<enemies.size(); i++) {
//			if(enemies.get(i).getX() > this.DRAWING_WIDTH || enemies.get(i).getX() <0) {
//				cLives--;
//				enemies.remove(i);
//				i++;
//			}
			if(enemies.get(i).getX() > this.DRAWING_WIDTH || enemies.get(i).getX() <0) {
				enemies.remove(i);
				i--;
			}
			
			if(enemies.get(i).intersects(c)) {
//				enemies.remove(i);
//				t++;
//				System.out.println(t);
//				if (t > 60) {
				enemies.get(i).attack(c);
//				cLives--;
//				i++;
				//}
			}
			
			t = 0;
			
			if(enemies.size() == 0) {
				spawnEnemy();
			}
		}
		
		if(!c.getLiveState()) {
			surface.switchScreen(3);
			c.setLives(3);
			isRight = true;
			level = 0;
			score = 0;
		}
	}
}
