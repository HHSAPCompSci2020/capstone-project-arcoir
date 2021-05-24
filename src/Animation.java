
import java.util.List;

import processing.core.PImage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**A class that keeps an ArrayList of PImages to display as an animation
 * 
 * @author Lead: Nicole Spaulding
 *
 */
public class Animation {
	//FIELDS
	
	
	private List<PImage> frames;	
	private int frameIndex = 0;			
	private int deltaTime = 0;			
	private long previousTime;			
	
	
	//CONSTRUCTOR
	
	/**Creates a new instance of Animation by taking in the specified time in milliseconds 
	 * 
	 * @param deltaTime
	 */
	public Animation(int deltaTime) {		
		this.deltaTime = deltaTime;
		frames = new ArrayList<PImage>();
	}
	
	/**Updates the animation's frames
	 * 
	 */
	public void update() {	
		if(System.currentTimeMillis() - previousTime > deltaTime) {									
			frameIndex++;						
			if(frameIndex >= frames.size()) {	
				frameIndex = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}

	/**adds a specified frame to the animation by appending it to the end
	 * 
	 * @param newFrame
	 */
	public void addFrame(PImage newFrame) {
		frames.add(newFrame);				
	}

	/**returns the current frame, if the animation is empty, returns null
	 * 
	 * @return PImage of the current frame, null if empty
	 */
	public PImage getFrame() {		//
		if (frames.size() > 0) {
			return frames.get(frameIndex);
		}
		return null;
	}


}
