
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * The Scrollable class shifts the background image either left or right, 
 * depending on which direction the character moves in the game. 

 * @author Author: Nicole Spaulding
 *
 */
public class Scrollable {
	private ArrayList<Integer> imagePos;
	private PImage image;			
	private int speed, posY;	
	private PApplet surface;
	
	
	/**
	 * Creates a new movable background for the game.
	 * 
	 * @param speed How fast the background shifts left or right 
	 * @param img The background image in png form 
	 * @param posY The position of the background 
	 * @param surface PApplet to draw
	 */
	public Scrollable (int speed, PImage img, int posY, PApplet surface) {
		this.speed = speed;
		this.surface = surface;
		image = img;
		imagePos = new ArrayList<Integer>();
		int numOfGround = surface.width/img.width + 3;
		this.posY = posY;
		for(int i = 0; i < numOfGround; i++) {		
			int posX = (int)(i * image.width - img.width);
			imagePos.add(posX);
		}
		
	}
	
	/**
	 * Changes the position of the background each time the character moves. 
	 */
	public void update() {
		for(int i = 0; i < imagePos.size(); i++) {
			imagePos.set(i, imagePos.get(i) - speed);
		}
		
		if(speed > 0) {
			int firstTile = imagePos.get(0);
			if(firstTile + image.width < 0) {	//if out of screen adds the tile at 0 to end and removes tile 0
				imagePos.set(0, imagePos.get(imagePos.size() - 1) + image.width); 
				imagePos.add(imagePos.get(0));	
				imagePos.remove(0);				
			}
		} else {
			int lastTile = imagePos.get(imagePos.size()-1);
		
			if(lastTile + image.width > surface.width) {	//if out of screen adds the tile at end to beginning and removes tile 3
				imagePos.set(imagePos.size()-1, imagePos.get(0)- image.width); 
				imagePos.add(0, imagePos.get(imagePos.size() - 1));	
				imagePos.remove(imagePos.size() - 1);
			}
		}
	}
	
	/**
	 * Draws the PApplet.
	 * 
	 * @param p PApplet to draw
	 */
	public void draw(PApplet p) {
		//for(int i = 0; i < listImage.size(); i++) {
		image.resize(800, 500);
		for(int xPos: imagePos) {
			p.image(image, xPos, posY);
		}

	}

	/**
	 * Initializes the speed of the background to amount passed in the parameter. 
	 * 
	 * @param speed the amount to shift the background
	 */
	public void setSpeed(int speed) {		//makes it so speed can be changes and still private
		this.speed = speed;
	}
	
	
	/**
	 * Changes the background image  
	 * 
	 * @param image the new background display in the game.
	 */
	public void setImage(PImage image) {		//makes it so speed can be changes and still private
		this.image = image;
	}
	
}

