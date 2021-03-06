package game;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Rectangle;

import processing.core.PImage;

import dashboard.Dashboard;
import arcoir.DrawingSurface;
import screens.Screen;

// modfied from Mr. Shelby's SecondScreen class
/**A screen that displays all the components used to run the game. 
 * This class was adapted from Mr. Shelby's SecondScreen class from the ProcessingScreenSwitching Demo
 * 
 * @author Lead: Nicole Spaulding, everyone worked on this class
 *
 */
public class GameScreen extends Screen {

	// Fields
	private Entity c;
	private ArrayList<Ground> g;
	private Dashboard dash;
	private DrawingSurface surface;
	private Animation idleR, idleL, cWR, cWL, eR, eL;
	private PImage bg, obstacle;
	private boolean isRight;
	private ArrayList <Entity> enemies;
	private Entity enemy;
	private Scrollable background;
	private int level;
	private int score;
	private boolean doneLoading, edoneLoading;
	private boolean leveledUp;
	private int displayCount;
	private ArrayList<PImage> artifacts, backgrounds;
	
	/** Creates a new instance of a GameScreen with a PApplet object
	 * 
	 * @param surface PApplet to draw
	 */
	public GameScreen(DrawingSurface surface) {
		isRight = true;
		this.surface = surface;
		idleR = new Animation(300);
		idleL = new Animation(300);
		cWR = new Animation(100);
		cWL = new Animation(100);
		eR = new Animation(300);
		eL = new Animation(300);
		
		artifacts =  new ArrayList<PImage>();
		backgrounds = new ArrayList<PImage>();
		leveledUp = false;
		displayCount = 0;
		
		g = new ArrayList<Ground>();
		g.add(new Ground(new Rectangle(-1000, 450, 15000, 20)));
		for(int i = 0; i < 26; i++) {
			g.add(new Ground(new Rectangle(i*500-1000, 410, 40, 40)));
		}
		score = 0;
		level = 1;
				
		doneLoading = false;
		edoneLoading = false;
		
		
	}

	// Methods
	
	/**A method that runs as soon as the program starts, 
	 * 
	 */
	public void setup() {
		
		loadCAnims();
		background = new Scrollable(0, bg, 0, surface);
		
		c = new Entity(idleR, "HERO", 3, 336, 100);
		c.adjustImgShift(-45, -20);
		c.setMovesXAxis(false);
		
		enemies = new ArrayList<Entity>();
		enemy = new Entity(eL, "ENEMY", 1, 600, 100);
		enemy.adjustImgShift(-8, -10);
		enemy.setHeight(40);
		enemies.add(enemy);
		
		spawnEnemy();
		
		dash = new Dashboard(false, true, surface.loadImage("resources/dash/help/helpIcon.gif"), surface.loadImage("resources/dash/back.gif"));
	}

	/**A method called in setup to load images and animations
	 * 
	 */
	private void loadCAnims() {
		bg = surface.loadImage("resources/bgs/bgGrey.jpeg");
		obstacle = surface.loadImage("resources/obstacle/Mushroom.png");
		obstacle.resize(40, 40);
		for(int i = 0; i < g.size(); i++) {
			if(i != 0) {
				g.get(i).setImage(obstacle);
			}
		}
		
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
		
		
	//	backgrounds.add(bg);
		backgrounds.add(surface.loadImage("resources/bgs/bgRed.gif"));
		backgrounds.add(surface.loadImage("resources/bgs/bgOrange.gif"));
		backgrounds.add(surface.loadImage("resources/bgs/bgYellow.gif"));
		backgrounds.add(surface.loadImage("resources/bgs/bgGreen.gif"));
		backgrounds.add(surface.loadImage("resources/bgs/bgBlue.gif"));
		backgrounds.add(surface.loadImage("resources/bgs/bgPurple.gif"));
		
		artifacts.add(surface.loadImage("resources/artifacts/artifactRed.gif"));
		artifacts.add(surface.loadImage("resources/artifacts/artifactOrange.gif"));
		artifacts.add(surface.loadImage("resources/artifacts/artifactYellow.gif"));
		artifacts.add(surface.loadImage("resources/artifacts/artifactGreen.gif"));
		artifacts.add(surface.loadImage("resources/artifacts/artifactBlue.gif"));
		artifacts.add(surface.loadImage("resources/artifacts/artifactPurple.gif"));
		artifacts.add(surface.loadImage("resources/artifacts/artifactRainbow.gif"));

	}
	/**Draws components of the game to the screen
	 * 
	 */
	public void draw() {
		if (!doneLoading && surface.framesDone()) {
			assignFrames();
		} 
		
		if (!edoneLoading && surface.eFramesDone()) {
			assignEnemyFrames();
		} 
		
		
		surface.pushStyle();
		
		background.draw(surface);
		surface.stroke(0); // Set line drawing color to white
		surface.noFill();
		
	
		for(Ground ground: g) {
			ground.drawGround(surface);
		}
		
		c.draw(surface);
		for (Entity e : enemies) {
			e.draw(surface);
		}

		surface.fill(0);
		surface.text("Move: Arrow keys", 10, 30);
		surface.text("Attack: Space", 10, 50);
		
		surface.text("Score: " + score, 700, 30);
		surface.text("Level: " + level, 700, 50);
		surface.text("Lives: " + (int)c.getLives(), 700, 70);
		
		
		if(leveledUp && level < 9 && level > 1) {
			
			if(displayCount == 10 && level < 8 && level != 0) {
				background.setImage(backgrounds.get(level - 2));
				//System.out.println(level - 2);
			}
			
			if(displayCount > 0) {
				surface.image(artifacts.get(level-2), 400 - artifacts.get(level-2).width/2, 250 - artifacts.get(level-2).height/2);
				
				if(level == 8)
					surface.text("You Won! UNLIMITED MODE:", 280, 180);
				displayCount--;
			}
		}
		
		surface.popStyle();
		
		background.setSpeed(0);
		
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			for(Entity enm : enemies) {
				enm.translate(0.8);
			}
			for(Ground ground: g) {
				ground.translate(3);
			}
			c.translate(-1);
			background.setSpeed(-3);
		}
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			for(Entity enm : enemies) {
				enm.translate(-0.8);
			}
			for(Ground ground: g) {
				ground.translate(-3);
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
		//		System.out.println(c.getLiveState());
			}
			
			enm.act(g);
			
		}
		
		while (enemies.size()<level) {
			spawnEnemy();
		}
		
		updateLevel();
		
		updateEnemyState();
		enemyAttack();
		

		if (surface.isPressed(KeyEvent.VK_SPACE)) {
			for (int i = 0; i<enemies.size(); i++) {
				if (c.intersects(enemies.get(i))) {
					enemies.remove(i);
					score++;
				}
			}
		}
		
		if(!c.getLiveState()) {
			surface.switchScreen(3);
			reset();
		}
		
		background.update();
		

		dash.draw(surface);
		
	}
	
	/**resets the game if all the character's lives are lost
	 * 
	 */
	public void reset () {
		c.setLives(3);
		isRight = true;
		level = 1;
		score = 0;
		leveledUp = false;
		displayCount = 0;
		
		background.setImage(bg);
		
		enemies = new ArrayList<Entity>();
		enemy = new Entity(eL, "ENEMY", 1, 600, 100);
		enemy.adjustImgShift(-8, -10);
		enemy.setHeight(40);
		enemies.add(enemy);
		
		spawnEnemy();
	}
	
	/**Assigns the customized images to the frames of the main character
	 * 
	 */
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
	/**Assigns the customized images to the frames of the enemies
	 * 
	 */
	private void assignEnemyFrames() {
		PImage[][] frames = surface.getEnemyFrames();
		if (surface.eFramesDone()) {
			eR = new Animation (300);
			eL = new Animation (300);
			for(int i = 0; i < frames.length; i++) {
				for(int j = 0; j < frames[i].length; j++) {
					if(i == 0) {
						eR.addFrame(frames[i][j]);
					} else if(i == 1) {
						eL.addFrame(frames[i][j]);
					}
				}
			}
			c.setAnimation(eL);
			edoneLoading = true;
		}
	}
	/**if the left mouse button if pressed, the dashboard's mousePressed button is called
	 * @param p The point with coordinates x and y on the screen that is clicked by the mouse 
	 */
	public void mousePressed(Point p) {
		if (surface.mouseButton == surface.LEFT) {
			dash.mousePressed(p.x, p.y, surface, 2, false, false);
		}
	}
	
	/**Increments the level by one if the score has exceeded the current level
	 * 
	 */
	public void updateLevel() {
		if (score >= level) {
			level++;
			score = 0;
			leveledUp = true;
			displayCount = 10;
		}
	}
	
	/**Spawns an enemy at a random place to the right of the character
	 * 
	 */
	public void spawnEnemy() {
		Entity spawnedEnemy = new Entity(eL, "ENEMY", 1, (int)(Math.random()*400+400), 100);
		spawnedEnemy.adjustImgShift(-8, -10);
		spawnedEnemy.setHeight(40);
		enemies.add(spawnedEnemy);
	}
	
	/**removes enemies as they  go off the screen
	 * 
	 */
	public void updateEnemyState() {
		for(int i=0; i<enemies.size(); i++) {
			if(enemies.get(i).getX() > this.DRAWING_WIDTH || enemies.get(i).getX() <0) {
				enemies.remove(i);
				i--;
			}
		}
	}
	/** checks if enemies are intersecting the character, if so, they attack the character
	 * 
	 */
	public void enemyAttack() {
		for(int i=0; i<enemies.size(); i++) {
			if(enemies.get(i).intersects(c)) {
				enemies.get(i).attack(c);
			}
		}
	}
}
