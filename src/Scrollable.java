
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Scrollable {
	private ArrayList<MovableImage> listImage;
	private PImage image;			
	private int tileNum = 0;							//keeps track of tile number
	private int speed, posY;	
	
	public Scrollable (int speed, PImage img, int windowW, int posY) {
		this.speed = speed;
		image = img;
		listImage = new ArrayList<MovableImage>();
		int numOfGround = windowW / img.width + 2;
		this.posY = posY;
		
		for(int i = 0; i < numOfGround; i++) {		
			int posX = (int)(i * image.width);
			MovableImage image1 = new MovableImage(image, posX);
			listImage.add(image1);
		}
		
	}
	
	public void update() {
		for(MovableImage img : listImage) {		
			img.setPosX(img.getPosX() - speed); 
		}

		MovableImage firstTile = listImage.get(0);

		if(firstTile.getPosX() + image.width < 0) {	//if out of screen adds the tile at 0 to end and removes tile 0
			firstTile.setPosX(listImage.get(listImage.size() - 1).getPosX() + image.width); 
			listImage.add(listImage.get(0));	
			listImage.remove(0);				
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

