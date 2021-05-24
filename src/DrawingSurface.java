import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Point;
import java.util.ArrayList;

/**
 * The ScreenSwitcher interface initializes each of four screens and adds them to a screens 
 * array. Allows for switching between screens.
 * 
 * @author Mr. Shelby
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher{
public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;

	/**
	 * Constructs a DrawingSurface with four screens: Menu, Drawing, Game, and GameOver.
	 */
	public DrawingSurface() {
		
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		MenuScreen screen1 = new MenuScreen(this);
		screens.add(screen1);
		
		DrawingScreen screen2 = new DrawingScreen(this);
		screens.add(screen2);
		
		GameScreen screen3 = new GameScreen(this);
		screens.add(screen3);
		
		GameOverScreen screen4 = new GameOverScreen(this);
		screens.add(screen4);
		
		activeScreen = screens.get(0);
		
	}
	
	/**
	 * Sets the size of the DrawingSurface to the width and height of the active screen.
	 */
	public void settings() {
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	/**
	 * Sets up the DrawingSurface.
	 */
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
	}
	
	/**
	 * Draws the active screen. Scales the active screen (800 x 500) to the width and height of the user's screen. 
	 */
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
	}
	
	/**
	 * Resets the GameScreen so that the game's progress starts at 0.
	 */
	public void reset() {
		((GameScreen)(screens.get(2))).reset();
	}
	
	/**
	 * 
	 * @return the hero's animation frames.
	 */
	public PImage[][] getFrames() {
		PImage[][] frames = ((DrawingScreen)(screens.get(1))).getFrames();
		return frames;
	}
	
	/**
	 * 
	 * @return true if the hero's animation frames are done.
	 */
	public boolean framesDone() {
		DrawingScreen screen = (DrawingScreen)screens.get(DRAWINGSCREEN);
		return screen.framesDone();
	}
	
	/**
	 * 
	 * @return the enemy's animation frames.
	 */
	public PImage[][] getEnemyFrames() {
		PImage[][] frames = ((DrawingScreen)(screens.get(1))).getEnemyFrames();
		return frames;
	}
	
	/**
	 * 
	 * @return true if the enemy's frames are done.
	 */
	public boolean eFramesDone() {
		DrawingScreen screen = (DrawingScreen)screens.get(DRAWINGSCREEN);
		return screen.eframesDone();
	}
	
	/**
	 * Adds a keycode to the key pressed array.
	 */
	public void keyPressed() {
		keys.add(keyCode);
	}

	/**
	 * Removes a keycode from the key pressed array.
	 */
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	/**
	 * Determines whether a specific key is pressed.
	 * 
	 * @param code the key that is being pressed.
	 * @return true if the key is being pressed.
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	/**
	 * Executes code on the active screen when the mouse is pressed.
	 */
	public void mousePressed() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mousePressed(p);
	}
	
	/**
	 * Executes code on the active screen when the mouse is moved.
	 */
	public void mouseMoved() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mouseMoved(p);
	}
	
	/**
	 * Executes code on the active screen when the mouse is dragged.
	 */
	public void mouseDragged() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mouseDragged(p);
	}
	
	/**
	 * Executes code on the active screen when the mouse is released.
	 */
	public void mouseReleased() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mouseReleased(p);
	}
	
	/**
	 * Returns the unscaled coordinates.
	 * 
	 * @param assumed the scaled coordinates
	 * @return the actual coordinates on the screen
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	/**
	 * Returns the scaled coordinates.
	 * 
	 * @param actual the actual coordinates on the screen
	 * @return the scaled coordinates
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	/**
	 * Changes the display to one of the fours specific screens passed into the parameter. 
	 * 
	 * @param i The number that corresponds to a specific screen: MenuScreen, DrawingScreen, GameScreen, or GameOverScreen.
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}

	

}