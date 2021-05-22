
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Scrollable {
	private ArrayList<MovableImage> listImage;
	private PImage image;			
	private int tileNum = 0;							//keeps track of tile number
	private int speed, posY;	
	private PApplet surface;
	
	public Scrollable (int speed, PImage img, int posY, PApplet surface) {
		this.speed = speed;
		this.surface = surface;
		image = img;
		listImage = new ArrayList<MovableImage>();
		int numOfGround = 3;
		this.posY = posY;
		for(int i = 0; i < numOfGround; i++) {		
			int posX = (int)(i * image.width - img.width);
			MovableImage image1 = new MovableImage(image, posX);
			listImage.add(image1);
		}
		
	}
	
	public void update() {
		for(MovableImage img : listImage) {		
			img.setPosX(img.getPosX() - speed); 
		}

		
		if(speed > 0) {
			MovableImage firstTile = listImage.get(0);
			if(firstTile.getPosX() + image.width < 0) {	//if out of screen adds the tile at 0 to end and removes tile 0
				firstTile.setPosX(listImage.get(listImage.size() - 1).getPosX() + image.width); 
				listImage.add(listImage.get(0));	
				listImage.remove(0);				
			}
		} else {
			MovableImage lastTile = listImage.get(listImage.size()-1);
		
			if(lastTile.getPosX() > surface.width) {	//if out of screen adds the tile at 3 to beginning and removes tile 3
				lastTile.setPosX(listImage.get(0).getPosX() - image.width); 
				listImage.add(0, listImage.get(listImage.size() - 1));	
				listImage.remove(listImage.size() - 1);
			}
		}
	}
	

	public void draw(PApplet p) {
		//for(int i = 0; i < listImage.size(); i++) {
		for(MovableImage img: listImage) {
			p.image(img.getImage(), img.getPosX(), posY);
		}

	}


	public void setSpeed(int speed) {		//makes it so speed can be changes and still private
		this.speed = speed;
	}
	
}

