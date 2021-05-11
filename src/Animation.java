import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 
 * @author Nicole Spaulding
 *
 */
public class Animation {
	//FIELDS
	
	//list of all frames
	private List<BufferedImage> frames;	
	//what frame is currently displayed
	private int frameIndex = 0;			
	//specified time between frames
	private int deltaTime = 0;			
	//system time after the last frame update
	private long previousTime;			
	//CONSTRUCTOR, takes in specified time between frames
	public Animation(int deltaTime) {		
		this.deltaTime = deltaTime;
		frames = new ArrayList<BufferedImage>();
	}

	//***********PUBLIC METHODS***************

	public void update() {	
		if(System.currentTimeMillis() - previousTime > deltaTime) {									
			frameIndex++;						//updates animation to next frame 
			if(frameIndex >= frames.size()) {	//restarts animation at end
				frameIndex = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}

	public void addFrame(BufferedImage newFrame) {
		frames.add(newFrame);				//adds specified frames to the animation
	}

	public BufferedImage getFrame() {		//returns current frame's index in list
		if (frames.size() > 0) {
			return frames.get(frameIndex);
		}
		return null;
	}


}
