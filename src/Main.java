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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w.setBounds(0, 0, screenSize.width, screenSize.height);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu.runGame();
		
		w.setResizable(true);
		w.setVisible(true);
	}
	
}
