import processing.core.PApplet;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


public class DrawingSurface extends PApplet implements ScreenSwitcher{
public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;

	
	public DrawingSurface() {
		
		
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		DrawingScreen screen1 = new DrawingScreen(this);
		screens.add(screen1);
		
		GameScreen screen2 = new GameScreen(this);
		screens.add(screen2);
		
		activeScreen = screens.get(0);
		
	}
	
	public void settings() {
		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mousePressed(p);
	}
	
	public void mouseMoved() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mouseMoved(p);
	}
	
	public void mouseDragged() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mouseDragged(p);
	}
	
	public void mouseReleased() {
		Point p = new Point ((int)(mouseX/ratioX), (int)(mouseY/ratioY));
		activeScreen.mouseReleased(p);
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}


}