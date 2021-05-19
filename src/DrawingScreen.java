import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author lindsayqin
 *
 */
public class DrawingScreen extends Screen {

	public ColorPalette palette;
	private DrawingSurface surface;
	private Rectangle switchButton;
	private int gridStartX;
		public Dashboard board;
		private Color[][] character;
		private Point prevToggle;
		
		public DrawingScreen(DrawingSurface surface) {
			this.surface = surface;
			palette = new ColorPalette();
			character = new Color [32][32];
			switchButton = new Rectangle (50, 50, 100, 100);
			gridStartX = 150;
		}
		
		public void clickToFill() {
			
		}
		
		public void saveImage() {
			
		}
		
		public void setup () {
			board = new Dashboard(DRAWING_WIDTH * 2/3, DRAWING_HEIGHT - DRAWING_WIDTH/20 - 20, DRAWING_WIDTH, DRAWING_HEIGHT, 
					surface.loadImage("resources/help/helpIcon.gif"));
		}
		
		public void draw () {
			int gridSide = 500;
			
			surface.background(255);
			
			surface.fill(0);
			surface.text( "click the black squares for help and screen switching", 500f, 70f);
			surface.rect(switchButton.x, switchButton.y, switchButton.width, switchButton.height);
			
			surface.noFill();
			
			drawBackground(gridStartX, 0, gridSide);
			drawGrid(gridStartX, 0, gridSide);

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
			float sideLength = side/character.length;
			
			for (int i = 0; i < character.length; i++) {
				for (int j = 0; j < character[0].length; j++) {
					float rectX = x + j * sideLength;
					float rectY = y + i * sideLength;
					
					
					if(character[i][j] != null) {
						Color current = character[i][j];
					
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
			float sideLength = side/character.length;
			int gridCount = 0;
			
			for (int i = 0; i < character.length; i++) {
				for (int j = 0; j < character[0].length; j++) {
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
		
		public void mousePressed(Point click) {
			if (surface.mouseButton == surface.LEFT) {
				Point coord = clickToIndex(click, gridStartX, 0, 500, 500);
				if (coord != null) {
					toggleCell(coord.x, coord.y);
					prevToggle = coord;
				}
			} 
			
			Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
			if (switchButton.contains(p))
				surface.switchScreen(ScreenSwitcher.GAMESCREEN);


			board.mousePressed(click.x, click.y);
		}
		
		public void mouseDragged(Point click) {
			if (surface.mouseButton == surface.LEFT) {
				Point coord = clickToIndex(click, gridStartX, 0, 500, 500);
				if (coord != null && !coord.equals(prevToggle)) {
					toggleCell(coord.x, coord.y);
					prevToggle = coord;
				}
			} 
		}

		
	}


