import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {

	public static Menu menu = new Menu();

	
	/**Runs the menu to start the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame w = new JFrame("Arcoir");
		
		PApplet.runSketch(new String[]{""}, surface);
		PSurfaceAWT surf = (PSurfaceAWT) surface.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
		
		window.setSize(480, 300);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w.setBounds(0, 0, screenSize.width, screenSize.height);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu.runGame();
		
		w.setResizable(true);
		w.setVisible(true);
	}
	
}
