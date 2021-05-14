import processing.core.PApplet;

public class Menu {

	PApplet marker = new PApplet();
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
		board.draw(marker);
		System.out.println("game runs!");
	}
}
