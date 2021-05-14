import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Menu {

	private GameRunner runner;
	private DrawingScreen surface;
	private Dashboard board;
	
	public Menu() {
		 runner = new GameRunner();
		 surface = new DrawingScreen();
		 board = new Dashboard();
	}
	
	/**This method starts the game
	 * 
	 */
	public void runGame() {
		System.out.println("game runs!");
	}
	
	public JFrame runDrawing() {
		
				return window;
	}
}
