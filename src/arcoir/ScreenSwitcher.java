package arcoir;


/**
 * The ScreenSwitcher interface initialized each of the four screens to a number between 0 and 3, 
 * so that other classes can easily call the switchScreen() method in order to change the display.
 * 
 * @author Mr. Shelby (adapted for use by Lindsay Qin, Nicole Spaulding, and Emily Zhang)
 */
public interface ScreenSwitcher {
	
	/**
	 * Index of the MenuScreen.
	 */
	public static final int MENUSCREEN = 0;
	/**
	 * Index of the DrawingScreen.
	 */
	public static final int DRAWINGSCREEN = 1;
	/**
	 * Index of the GameScreen.
	 */
	public static final int GAMESCREEN = 2;
	/**
	 * Index of the GameOverScreen.
	 */
	public static final int GAMEOVERSCREEN = 3;
	
	/**
	 * Changes the display to one of the fours specific screens passed into the parameter. 
	 * 
	 * @param i The number that corresponds to a specific screen: MenuScreen, DrawingScreen, GameScreen, or GameOverScreen.
	 */
	public void switchScreen(int i);
}
