import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Menu {

	private GameRunner runner;
	private DrawingSurface surface;
	private Dashboard board;
	
	public Menu() {
		 runner = new GameRunner();
		 surface = new DrawingSurface();
		 board = new Dashboard();
	}
	
	/**This method starts the game
	 * 
	 */
	public void runGame() {
		board.draw(surface);
		System.out.println("game runs!");
	}
	
	public JFrame runDrawing() {
		
		PApplet.runSketch(new String[]{""}, surface);
		PSurfaceAWT surf = (PSurfaceAWT) surface.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
		
		window.setSize(480, 300);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		return window;
	}
}
