import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

/**
 * The Dashboard class displays the help button and back button on the screen.
 * 
 * @author lindsayqin
 */
public class Dashboard {

	// Fields
	private Rectangle help, back; 
	private Help helpWindow;
	private PImage helpIcon, backIcon;
	private boolean whiteSquares, over;
	
	// Constructor
	/**
	 * Constructs a Dashboard object.
	 * 
	 * @param gameOver Whether the game is over or not.
	 * @param squares Whether white squares should be displayed behind the back and help icons.
	 * @param help1 The help icon.
	 * @param back1 The back icon.
	 */
	public Dashboard (boolean gameOver, boolean squares, PImage help1, PImage back1) {
		help = new Rectangle(760, 460, 30, 30);
		back = new Rectangle (10, 460, 30, 30);
		helpIcon = help1;
		helpWindow = new Help();
		backIcon = back1;
		over = gameOver;
		whiteSquares = squares;
	}	
	
	/**
	 * Draws the dashboard to the screen.
	 * 
	 * @param marker The PApplet to draw the dashboard with.
	 */
	public void draw(PApplet marker) {
		if (whiteSquares) {
			marker.pushStyle();
			marker.fill(255);
			marker.rect(760, 460, 30, 30);
			if (!over)
				marker.rect(10, 460, 30, 30);
			marker.popStyle();
		}
		
		//show images
		marker.image(helpIcon, help.x, help.y, help.width, help.height);
		if (!over)
			marker.image(backIcon, back.x, back.y, back.width, back.height);
	}
	
	/**
	 * Carries out mouse methods when the mouse is left clicked.
	 * 
	 * @param x coordinate of the mouse click
	 * @param y coordinate of the mouse click
	 * @param surface PApplet to draw with.
	 * @param i index of the current screen.
	 * @param framesDone whether the hero frames were done or not
	 * @param eframesDone whether the enemy frames were done or not
	 */
	public void mousePressed(double x, double y, DrawingSurface surface, int i, boolean framesDone, boolean eframesDone) {
		if (help.contains(x, y)) {
			helpWindow.show();
		}
		if (back.contains(x, y) && !over) {
			if (i == 1 && (!framesDone || !eframesDone)) {
				int select = -1;
				if (!framesDone) {
					select = JOptionPane.showConfirmDialog(null, 
							"Are you sure? You haven't saved all of your hero frames. The default hero will be used.", "NOTICE", 
							JOptionPane.YES_NO_OPTION);
				} 
				if (!eframesDone) {
					select = JOptionPane.showConfirmDialog(null, 
							"Are you sure? You haven't saved all of your enemy frames. The default enemy will be used.", "NOTICE", 
							JOptionPane.YES_NO_OPTION);
				}
				if (select == JOptionPane.YES_OPTION) {
					surface.switchScreen(ScreenSwitcher.MENUSCREEN);
				}
			}
			else {
				surface.switchScreen(ScreenSwitcher.MENUSCREEN);
			}
		}
		
	}
			
	}
