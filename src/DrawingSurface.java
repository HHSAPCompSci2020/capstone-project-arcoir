import processing.core.PApplet;
import java.awt.Color;
import java.awt.Point;


public class DrawingSurface extends PApplet{

	public ColorPalette palette;
//	public Character character;
	public Dashboard board;
	private Color[][] character;
	private Point prevToggle;
	
	public DrawingSurface() {
		palette = new ColorPalette();
//		character = new Character();
		character = new Color [32][32];
		board = new Dashboard();
	}
	
	public void clickToFill() {
		
	}
	
	public void saveImage() {
		
	}
	
	public void setup() {
		
	}
	
	public void draw () {
		background(255);
		fill(0);
		
		drawGrid(width/5, 0, (int)(width * 0.8), height);
		board.draw(this);
		
	}
	
	/**
	 * (Graphical UI)
	 * Draws the grid on a PApplet.
	 * The specific way the grid is depicted is up to the coder.
	 * 
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void drawGrid(float x, float y, float width, float height) {
		float rectWidth = width/character[0].length;
		float rectHeight = height/character.length;
		
		for (int i = 0; i < character.length; i++) {
			for (int j = 0; j < character[0].length; j++) {
				float rectX = x + j * rectWidth;
				float rectY = y + i * rectHeight;
				
				Color current = character[i][j];
				
				//draw grid here (not transparent background, but what is in the character array
				fill(current.getRGB());
				rect(rectX, rectY, rectWidth, rectHeight);
			}
		}
	}
	
	/**
	 * (Graphical UI)
	 * Draws a gray and white background on a PApplet.
	 * 
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void drawBackGround(float x, float y, float width, float height) {
		float rectWidth = width/character[0].length;
		float rectHeight = height/character.length;
		
		boolean isWhite = true;
		
		for (int i = 0; i < character.length; i++) {
			for (int j = 0; j < character[0].length; j++) {
				float rectX = x + j * rectWidth;
				float rectY = y + i * rectHeight;
				
				Color current;
				
				if (isWhite) {
					current = Color.WHITE;
				} else {
					current = Color.LIGHT_GRAY;
				}
				
				//draw grid here (not transparent background, but what is in the character array
				fill(current.getRGB());
				rect(rectX, rectY, rectWidth, rectHeight);
				isWhite = !isWhite;
			}
		}
	}
	
	/**
	 * (Graphical UI)
	 * Determines which element of the grid matches with a particular pixel coordinate.
	 * This supports interaction with the grid using mouse clicks in the window.
	 * 
	 * @param p A Point object containing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the game of life grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		float rectWidth = width/character[0].length;
		float rectHeight = height/character.length;
		int a = -1; //def not on the grid
		int b = -1; //def not on the grid
		float x1 = p.x - x;
		float y1 = p.y - y;
		
		if ((x <= p.x && p.x < x + width) && (y <= p.y && p.y < y + height)) {
			a = (int)(y1/rectHeight);
			b = (int)(x1/rectWidth);
		}
		
		Point coordinate = new Point (a, b);
		if (a == -1 && b == -1) {
			coordinate = null; //if point not on the grid
		}
		return coordinate;
	}
	
	/**
	 * (Graphical UI)
	 * changes color of selected pixel.
	 * 
	 * @param i The x coordinate of the cell in the grid.
	 * @param j The y coordinate of the cell in the grid.
	 */
	public void toggleCell(int i, int j) {
		character[i][j] = palette.getSelectedColor();
	}
	
	public void mousePressed() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX,mouseY);
			float dimension = height;
			Point coord = clickToIndex(click,0,0,dimension,dimension);
			if (coord != null) {
				toggleCell(coord.x, coord.y);
				prevToggle = coord;
			}
		} 

		board.mousePressed(mouseX, mouseY);
	}
	
	public void mouseDragged() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX,mouseY);
			float dimension = height;
			Point coord = clickToIndex(click,0,0,dimension,dimension);
			if (coord != null && !coord.equals(prevToggle)) {
				toggleCell(coord.x, coord.y);
				prevToggle = coord;
			}
		} 
	}

	
}
