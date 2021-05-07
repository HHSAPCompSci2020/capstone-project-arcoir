
public class Menu {

	public GameRunner runner;
	public DrawingSurface surface;
	public Dashboard board;
	
	public Menu() {
		 runner = new GameRunner();
		 surface = new DrawingSurface();
		 board = new Dashboard();
	}
	
	/**This method starts the game
	 * 
	 */
	public void runGame() {
		System.out.println("game runs!");
	}
}
