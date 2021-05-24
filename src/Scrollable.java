
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Scrollable {
	private ArrayList<Integer> imagePos;
	private PImage image;			
	private int speed, posY;	
	private PApplet surface;
	
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
	

	public void draw(PApplet p) {
		//for(int i = 0; i < listImage.size(); i++) {
		image.resize(800, 500);
		for(int xPos: imagePos) {
			p.image(image, xPos, posY);
		}

	}


	public void setSpeed(int speed) {		//makes it so speed can be changes and still private
		this.speed = speed;
	}
	
	public void setImage(PImage image) {		//makes it so speed can be changes and still private
		this.image = image;
	}
	
}

