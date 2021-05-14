import processing.core.PApplet;

public class DrawingSurface extends PApplet{

	public ColorPalette palette;
	public Character character;
	public Dashboard board;
	
	public DrawingSurface() {
		palette = new ColorPalette();
//		character = new Character();
		board = new Dashboard();
	}
	
	public void clickToFill() {
		
	}
	
	public void saveImage() {
		
	}
	
	public void draw () {
		board.draw(this);
	}
	
}
