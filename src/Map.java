/**
 * @author lindsayqin
 *
 */
public class Map {
	private int progress;
	
	/**
	 * Initializes progress to 0. 
	 */
	public Map () {
		progress = 0;
	}
	
	/**
	 * Increases the progress level on the map. 
	 */
	public void increaseProgress() {
		progress++;
	}
	
	/**
	 * 
	 * @return current progress level
	 */
	public int getProgress() {
		return progress;
	}
}
