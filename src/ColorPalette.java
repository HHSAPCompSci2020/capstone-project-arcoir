import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PApplet;

/**
 * @author lindsayqin
 *
 */
public class ColorPalette {
	private Color [] palette;
	private Color selectedColor;
	private Rectangle [] colors;
	
	/**
	 * Initializes a new palette with 24 default colors. Selects the first color.
	 */
	public ColorPalette () {
		palette = new Color [24];
		colors = new Rectangle [24];
		reset();
		initializeRects();
		selectedColor = palette[0];
	}
	
	/**
	 * Gets a color on the palette.
	 * 
	 * @param index of chosen color in the palette
	 * @return the chosen color
	 */
	public Color getColor(int index) {
		return palette[index];
	}
	
	/**
	 * Selects a color on the palette.
	 * 
	 * @param index of selected color in the palette
	 */
	public void selectColor(int index) {
		selectedColor = palette[index];
	}
	
	public Color getSelectedColor() {
		return selectedColor;
	}
	
	/**
	 * Switches a currently selected color with a new color.
	 * 
	 * @param index of color in the palette to be switched with. 
	 * @param r red value of new color
	 * @param g green value of new color
	 * @param b blue value of new color
	 */
	public void changeColor(int index, int r, int g, int b) {
		palette[index] = new Color(r, g, b);
	}
	
	/**
	 * Resets the palette to the default colors.
	 */
	public void reset () {
		palette[0] = new Color (205,74,74); //mahogany
		palette[1] = Color.RED; //red
		palette[2] = new Color (255,83,73); //red orange
		palette[3] = new Color (255,117,56); //orange
		palette[4] = new Color (255,182,83); //yellow orange
		palette[5] = new Color (255,223,0); //golden yellow
		palette[6] = Color.YELLOW; //yellow
		palette[7] = new Color (197,227,132); //yellow green
		palette[8] = new Color (10, 107, 13); //jade green
		palette[9] = new Color (28,172,120); //green
		palette[10] = new Color (25,158,189); //turquoise
		palette[11] = new Color (128,218,235); //sky blue
		palette[12] = Color.BLUE; //blue
		palette[13] = new Color (25,116,210); //navy blue
		palette[14] = new Color (146,110,174); //violet
		palette[15] = Color.MAGENTA; //magenta
		palette[16] = Color.PINK; //pink
		palette[17] = new Color (180,103,77); //brown
		palette[18] = Color.BLACK; //black
		palette[19] = Color.WHITE; //white
		palette[20] = Color.GRAY; //gray
		palette[21] = new Color (255,207,171); //peach
		palette[22] = new Color (250,167,108); //tan
		palette[23] = new Color (181, 101, 29); //light brown
	}
	
	/**
	 * Draws the PApplet.
	 * 
	 * @param marker PApplet to draw
	 */
	public void draw (PApplet marker) {
		for (int i = 0; i < palette.length/2; i++) {
			marker.fill(palette[i].getRGB());
			marker.rect(colors[i].x, colors[i].y, colors[i].width, colors[i].height);
		}
		for (int k = 12; k < palette.length; k++) {
			marker.fill(palette[k].getRGB());
			marker.rect(colors[k].x, colors[k].y, colors[k].width, colors[k].height);
		}

	}
	
	private void initializeRects() {
		float rectX = 660;
		float sideLength = 20;
		for (int i = 0; i < palette.length/2; i++) {
			float rectY = i * sideLength;
			colors[i] = new Rectangle((int)rectX, (int)rectY, (int)sideLength, (int)sideLength);
		}
		rectX = 680;
		for (int k = 12; k < palette.length; k++) {
			float rectY = (k-12) * sideLength;
			colors[k] = new Rectangle((int)rectX, (int)rectY, (int)sideLength, (int)sideLength);
		}

	}
	
	/**
	 * (Graphical UI)
	 * Determines which element of the palette matches with a particular pixel coordinate.
	 * This supports interaction with the grid using mouse clicks in the window.
	 * 
	 * @param p A Point object containing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the game of life grid.
	 */
	public int clickToIndex(Point p, float x, float y) {
		int a = -1; //def not on the grid
		int b = -1; //def not on the grid
		float x1 = p.x - x;
		float y1 = p.y - y;
					
		if ((x <= p.x && p.x < x + 40) && (y <= p.y && p.y < y + 240)) {
			a = (int)(y1/20);
			b = (int)(x1/20);
		}
		
		int index = -1; //def not right
		if (b == 0) {
			index = a;
		} else {
			index = 12 + a;
		}
		
		return index;
	}
	
	private void toggleCell(int i) {
		selectedColor = palette[i];
	}
	
	/**
	 * (Graphical UI)
	 * changes color of selected pixel.
	 * 
	 * @param p The point that the mouse was clicked on.
	 */
	public void mousePressed (Point p) {
		int index = clickToIndex(p, 660, 0);
		toggleCell(index);
	}
}
