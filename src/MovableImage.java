import processing.core.PImage;

public class MovableImage {
	private int posX;
	private PImage image;

	public MovableImage(PImage img, int x) {
		image = img;
		posX = x;
	}
	
	public void setPosX(int x) {
		posX = x;
	}

	public int getPosX() {
		return posX;
	}
	
	public PImage getImage() {
		return image;
	}
}
