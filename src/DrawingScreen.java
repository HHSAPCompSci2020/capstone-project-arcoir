import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;

/**
 * 
 * @author lindsayqin
 *
 */
public class DrawingScreen extends Screen {

	private ColorPalette palette;
	private DrawingSurface surface;
	private Rectangle switchButton, paletteRect, paintCanRect, saveRect;
	private Dashboard board;
	private Color [][][] characters;
	private Color[][] character1, character2, character3;
	private PImage [] frames;
	private PImage frame1, frame2, frame3;
	private int index;
	private Point prevToggle;
	private PImage paintCanIcon, saveIcon;
	private String selectedTool;

	public DrawingScreen(DrawingSurface surface) {
		this.surface = surface;
		palette = new ColorPalette();
		paletteRect = new Rectangle (660, 0, 40, 240);
		character1 = new Color [128][128];
		character2 = new Color [128][128];
		character3 = new Color [128][128];
		index = 0;
		switchButton = new Rectangle (50, 50, 50, 50);
		paintCanRect = new Rectangle (710, 0, 45, 45);
		saveRect = new Rectangle  (755, 0, 45, 45);
		selectedTool = "";
		
		characters = new Color [][][] {character1, character2, character3};
		frames = new PImage[] {frame1, frame2, frame3};
	}

	public void saveImage() {
		if (index == 0) {
			frame1.loadPixels();
			int i = 0;
			for (int r = 0; r < character1.length; r++) {
				for (int c = 0; c < character1.length; c++) {
					frame1.pixels[i] = surface.color(character1[r][c].getRed(), character1[r][c].getGreen(), character1[r][c].getBlue());
					i++;
				}
			}
		} else if (index == 1) {
			frame2.loadPixels();
			int i = 0;
			for (int r = 0; r < character2.length; r++) {
				for (int c = 0; c < character2.length; c++) {
					frame2.pixels[i] = surface.color(character2[r][c].getRed(), character2[r][c].getGreen(), character2[r][c].getBlue());
					i++;
				}
			}
		} else {
			frame3.loadPixels();
			int i = 0;
			for (int r = 0; r < character3.length; r++) {
				for (int c = 0; c < character3.length; c++) {
					frame3.pixels[i] = surface.color(character3[r][c].getRed(), character3[r][c].getGreen(), character3[r][c].getBlue());
					i++;
				}
			}
		}
	}
	
	public void setup () {
		board = new Dashboard(DRAWING_WIDTH * 2/3, DRAWING_HEIGHT - DRAWING_WIDTH/20 - 20, DRAWING_WIDTH, DRAWING_HEIGHT, 
				surface.loadImage("resources/help/helpIcon.gif"));
		paintCanIcon = surface.loadImage("resources/drawingIcons/paintcan.gif");
		saveIcon = surface.loadImage("resources/drawingIcons/save.gif");
	}

	public void draw () {
		int gridSide = 500;

		surface.background(255);

		surface.fill(0);
		surface.rect(switchButton.x, switchButton.y, switchButton.width, switchButton.height);

		surface.noFill();

		drawBackground(150, 0, gridSide);
		drawGrid(150, 0, gridSide);

		palette.draw(surface);
		surface.image(paintCanIcon, paintCanRect.x, paintCanRect.y, paintCanRect.width, paintCanRect.height);
		surface.image(saveIcon, saveRect.x, saveRect.y, saveRect.width, saveRect.height);

		board.draw(surface);
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
	public void drawGrid(float x, float y, float side) {
		float sideLength = side/characters[index].length;

		for (int i = 0; i < characters[index].length; i++) {
			for (int j = 0; j < characters[index][0].length; j++) {
				float rectX = x + j * sideLength;
				float rectY = y + i * sideLength;

				if(characters[index][i][j] != null) {
					Color current = characters[index][i][j];

					//draw grid here (not transparent background, but what is in the character array
					surface.pushStyle();
					surface.noStroke();
					surface.fill(current.getRGB());
					surface.rect(rectX, rectY, sideLength, sideLength);
					surface.popStyle();
				}
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
	public void drawBackground(float x, float y, float side) {
		float sideLength = side/characters[index].length;
		int gridCount = 0;

		for (int i = 0; i < characters[index].length; i++) {
			for (int j = 0; j < characters[index][0].length; j++) {
				float rectX = x + j * sideLength;
				float rectY = y + i * sideLength;

				Color current;

				if (gridCount % 2 == 0) {
					current = Color.WHITE;
				} else {
					current = Color.LIGHT_GRAY;
				}

				//draw grid here (not transparent background, but what is in the character array
				surface.pushStyle();
				surface.noStroke();
				surface.fill(current.getRGB());
				surface.rect(rectX, rectY, sideLength, sideLength);
				surface.popStyle();

				gridCount++;
			}
			gridCount++;
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
		float rectWidth = width/characters[index][0].length;
		float rectHeight = height/characters[index].length;
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
		characters[index][i][j] = palette.getSelectedColor();
	}

	/**
	 * Fills an object beginning at x,y.
	 * 
	 * @param x The x coordinate of the beginning of the paint can fill.
	 * @param y The y coordinate of the beginning of the paint can fill.
	 */
	public void paintCanFill(int x, int y) {
		fill(x, y);
	}
	
	private void fill (int x, int y) {
		if (x < 0 || x >= characters[index][0].length || y < 0 || y >= characters[index].length) {
			//do nothing
		} else if (characters[index][x][y] != null && characters[index][x][y].equals(palette.getSelectedColor())) {
			//do nothing
		} else {
			characters[index][x][y] = palette.getSelectedColor();

			fill(x + 1, y);
			fill(x - 1, y);
			fill(x, y + 1);
			fill(x, y - 1);
		}
	}

	public void mousePressed(Point click) {
		if (surface.mouseButton == surface.LEFT) {
			Point coord = clickToIndex(click, 150, 0, 500, 500);
			if (coord != null) {
				if (selectedTool.equals("PAINTCAN")) {
					paintCanFill(coord.x, coord.y);
				} else {
					toggleCell(coord.x, coord.y);
				}
				prevToggle = coord;
			}

			Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
			if (switchButton.contains(p))
				surface.switchScreen(ScreenSwitcher.GAMESCREEN);

			if (paletteRect.contains(click.x, click.y))
				palette.mousePressed(click);
			
			if (paintCanRect.contains(click.x, click.y)) {
				selectedTool = "PAINTCAN";
			}
			board.mousePressed(click.x, click.y);
		} 
	}

	public void mouseDragged(Point click) {
		if (surface.mouseButton == surface.LEFT) {
			Point coord = clickToIndex(click, 150, 0, 500, 500);
			if (coord != null && !coord.equals(prevToggle)) {
				toggleCell(coord.x, coord.y);
				prevToggle = coord;
			}
			if (paletteRect.contains(click.x, click.y))
				palette.mousePressed(click);

		} 
	}


}


