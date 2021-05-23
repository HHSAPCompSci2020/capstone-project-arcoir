import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import processing.core.PImage;
import processing.core.PConstants;

/**
 * 
 * @author lindsayqin
 *
 */
public class DrawingScreen extends Screen {
//need to fix UI, input RGB, frames show up
	private ColorPalette palette;
	private DrawingSurface surface;
	private Rectangle paletteRect, paintCanRect, saveRect, frameSelect, refreshRect, colorRect, addRect, resetRect;
	private Dashboard board;
	private Color [][][] characters;
	private Color[][] character1, character2, character3, idle;
	private PImage frame1R, frame2R, frame3R, frame4R, idleR, frame1L, frame2L, frame3L, frame4L, idleL;
	private int index, prevIndex;
	private Point prevToggle;
	private PImage paintCanIcon, saveIcon, refreshIcon, addIcon, resetIcon;
	private String selectedTool, currentFrame;
	private boolean move1, move2, move3, realIdle;
	boolean [] check;

	public DrawingScreen(DrawingSurface surface) {
		this.surface = surface;
		palette = new ColorPalette();
		paletteRect = new Rectangle (660, 0, 40, 240);
		character1 = new Color [128][128];
		character2 = new Color [128][128];
		character3 = new Color [128][128];
		idle = new Color [128][128];
		check = new boolean [4];
		index = 3;
		prevIndex = 0;
		paintCanRect = new Rectangle (710, 0, 40, 40);
		saveRect = new Rectangle  (755, 0, 40, 40);
		refreshRect = new Rectangle (755, 45, 40, 40);
		frameSelect = new Rectangle (710, 210, 80, 30);
		colorRect = new Rectangle (710, 135, 40, 38);
		addRect = new Rectangle(710, 45, 40, 40);
		resetRect = new Rectangle(710, 90, 40, 40);
		move1 = false;
		move2 = false;
		move3 = false;
		realIdle = false;
		selectedTool = "";
		currentFrame = "Idle";
		characters = new Color [][][] {character1, character2, character3, idle};
	}

	/**
	 * Saves the frame from the drawing interface as an actual frame to be used in an animation.
	 */
	public void saveImage() {
		if (characters[index] != null && check[index] != false) {
			if (index == 0) {
				frame1R.loadPixels();
				int i = 0;
				for (int r = 0; r < character1.length; r++) {
					for (int c = 0; c < character1.length; c++) {
						if (character1[r][c] != null)
							frame1R.pixels[i] = surface.color(character1[r][c].getRed(), character1[r][c].getGreen(), character1[r][c].getBlue());
						else {
							frame1R.pixels[i] = surface.color(0, 0, 0, 0); //transparent
						}
							
						i++;
					}
				}
				frame1R.updatePixels();
				frame1L = reflect(character1);
				frame1L.updatePixels();
				move1 = true;
				JOptionPane.showMessageDialog(null, "Move 1 successfully saved");
			} else if (index == 1) {
				frame2R.loadPixels();
				int i = 0;
				for (int r = 0; r < character2.length; r++) {
					for (int c = 0; c < character2.length; c++) {
						if (character2[r][c] != null)
							frame2R.pixels[i] = surface.color(character2[r][c].getRed(), character2[r][c].getGreen(), character2[r][c].getBlue());
						else {
							frame2R.pixels[i] = surface.color(0, 0, 0, 0);
						}
						i++;
					}
				}
				frame2R.updatePixels();
				frame2L = reflect(character2);
				frame2L.updatePixels();
				move2 = true;
				JOptionPane.showMessageDialog(null, "Move 2 successfully saved");
			} else if (index == 2){
				frame3R.loadPixels();
				int i = 0;
				for (int r = 0; r < character3.length; r++) {
					for (int c = 0; c < character3.length; c++) {
						if (character3[r][c] != null)
							frame3R.pixels[i] = surface.color(character3[r][c].getRed(), character3[r][c].getGreen(), character3[r][c].getBlue());
						else {
							frame3R.pixels[i] = surface.color(0, 0, 0, 0);
						}
						i++;
					}
				}
				frame3R.updatePixels();
				frame3L = reflect(character3);
				frame3L.updatePixels();
				move3 = true;
				JOptionPane.showMessageDialog(null, "Move 3 successfully saved");
			} else {
				idleR.loadPixels();
				int i = 0;
				for (int r = 0; r < idle.length; r++) {
					for (int c = 0; c < idle.length; c++) {
						if (idle[r][c] != null) {
							idleR.pixels[i] = surface.color(idle[r][c].getRed(), idle[r][c].getGreen(), idle[r][c].getBlue());
						} else {
							idleR.pixels[i] = surface.color(0, 0, 0, 0);
						}
						i++;
					}
				}
				idleR.updatePixels();
				idleL = reflect(idle);
				idleL.updatePixels();
				realIdle = true;
				JOptionPane.showMessageDialog(null, "Idle successfully saved");
			}
		}
	}
	
	private PImage reflect (Color [][] copy) {
		Color [][] reflect = new Color [128][128];
		for (int r = 0; r < 128; r++) {
			for (int c = 0; c < 64; c++) {
				reflect[r][c] = copy[r][128 - c - 1];
				reflect[r][128 - c - 1] = copy[r][c];
			}
		}
		PImage reflected = surface.createImage(128, 128, PConstants.ARGB);

		int i = 0;
		for (int r = 0; r < reflect.length; r++) {
			for (int c = 0; c < reflect.length; c++) {
				if (reflect[r][c] != null) {
					reflected.pixels[i] = surface.color(reflect[r][c].getRed(), reflect[r][c].getGreen(), reflect[r][c].getBlue());
				} else {
					reflected.pixels[i] = surface.color(0, 0, 0, 0);
				}
				i++;
			}
		}
		return reflected;
	}
	
	//initializes the frames
	private void createFrames() {
		frame1R = surface.createImage(128,  128,  PConstants.ARGB);
		frame2R = surface.createImage(128,  128,  PConstants.ARGB);
		frame3R = surface.createImage(128,  128,  PConstants.ARGB);
		frame4R = surface.createImage(128,  128,  PConstants.ARGB);
		idleR = surface.createImage(128,  128,  PConstants.ARGB);
		frame1L = surface.createImage(128,  128,  PConstants.ARGB);
		frame2L = surface.createImage(128,  128,  PConstants.ARGB);
		frame3L = surface.createImage(128,  128,  PConstants.ARGB);
		frame4L = surface.createImage(128,  128,  PConstants.ARGB);
		idleL = surface.createImage(128,  128,  PConstants.ARGB);
	}
	/**
	 * 
	 * @return the animation frames.
	 */
	public PImage[][] getFrames() {
		PImage [][] frames = new PImage[][] {{frame1R, frame2R, frame3R, frame4R}, {idleR}, 
			{frame1L, frame2L, frame3L, frame4L}, {idleL}};
		return frames;
	}
	
	/**
	 * 
	 * @return whether all frames have been saved or not
	 */
	public boolean framesDone() {
		return move1 && move2 && move3 && realIdle;
	}
	
	public void setup () {
		board = new Dashboard(DRAWING_WIDTH * 2/3, DRAWING_HEIGHT - DRAWING_WIDTH/20 - 20, DRAWING_WIDTH, DRAWING_HEIGHT, 
				surface.loadImage("resources/dash/help/helpIcon.gif"), surface.loadImage("resources/dash/back.gif"));
		paintCanIcon = surface.loadImage("resources/drawingIcons/paintcan.gif");
		saveIcon = surface.loadImage("resources/drawingIcons/save.gif");
		refreshIcon = surface.loadImage("resources/drawingIcons/refresh.gif");
		addIcon = surface.loadImage("resources/drawingIcons/add.gif");
		resetIcon = surface.loadImage("resources/drawingIcons/reset.gif");
		createFrames();

	}

	public void draw () {
		int gridSide = 500;

		surface.background(255);

		surface.fill(0);

		surface.noFill();

		drawBackground(150, 0, gridSide);
		drawGrid(150, 0, gridSide);

		palette.draw(surface);
		select(selectedTool);
		surface.image(paintCanIcon, paintCanRect.x, paintCanRect.y, paintCanRect.width, paintCanRect.height);
		surface.image(saveIcon, saveRect.x, saveRect.y, saveRect.width, saveRect.height);
		surface.image(refreshIcon, refreshRect.x,  refreshRect.y, refreshRect.width, refreshRect.height);
		surface.image(addIcon, addRect.x, addRect.y, addRect.width, addRect.height);
		surface.image(resetIcon, resetRect.x,  resetRect.y, resetRect.width, resetRect.height);
		
		surface.pushStyle();
		surface.noFill();
		surface.stroke(91, 15, 0);
		surface.strokeWeight(2);
		surface.rect(frameSelect.x, frameSelect.y, frameSelect.width, frameSelect.height);
		surface.popStyle();
		
		surface.pushStyle();
		surface.fill(91, 15, 0);
		surface.textSize(10);
		surface.textLeading(13);
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.text("Current Frame: \n" + currentFrame, frameSelect.x + 42, frameSelect.y + 12);
		surface.popStyle();
		
		surface.pushStyle();
		surface.fill(palette.getSelectedColor().getRGB());
		surface.strokeWeight(2);
		surface.stroke(91, 15, 0);
		surface.rect(colorRect.x, colorRect.y, colorRect.width, colorRect.height);
		surface.popStyle();

		board.draw(surface);
	}
	
	private void showFrameSelect() {
		String [] realFrames = new String [] {"Idle", "Move 1", "Move 2", "Move 3"};
		String input = (String)JOptionPane.showInputDialog(null, "Choose a frame to draw", "Which frame?", 
				JOptionPane.QUESTION_MESSAGE, null, realFrames, realFrames[prevIndex]);
		
		if (input == null)
			return;
		
		selectFrame(input);
	}
	
	private void selectFrame(String input) {
		if (input.equals("Idle")) {
			index = 3;
			currentFrame = "Idle";
			prevIndex = 0;
		} else if (input.equals("Move 1")) {
			index = 0;
			currentFrame = "Move 1";
			prevIndex = 1;
		} else if (input.equals("Move 2")) {
			index = 1;
			currentFrame = "Move 2";
			prevIndex = 2;
		} else {
			index = 2;
			currentFrame = "Move 3";
			prevIndex = 3;
		}
	}

	public void changeColor() {
		String first = JOptionPane.showInputDialog(null, "Enter r value.");
		int r = Integer.parseInt(first);
		String second = JOptionPane.showInputDialog(null, "Enter g value.");
		int g = Integer.parseInt(second);
		String third = JOptionPane.showInputDialog(null, "Enter b value.");
		int b = Integer.parseInt(third);
		palette.changeColor(palette.getCurrentIndex(), r, g, b);
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
		float sideLength = side/characters[index][0].length;
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
	
	/**
	 * Selects a drawing tool to be used. 
	 * 
	 * @param name of tool to be used
	 */
	public void select (String name) {
		if (name.equals("")) {
			//do nothing
		} else if (name.equals("PAINTCAN")) {
			surface.fill(168, 166, 166);
			surface.rect(paintCanRect.x, paintCanRect.y, paintCanRect.width, paintCanRect.height);
		}
	}
	
	/**
	 * Resets the frame to its default state.
	 */
	public void reset() {
		characters[index] = new Color [128][128];
	}
	
	private void fill (int x, int y) {
		if (x < 0 || x >= 128 || y < 0 || y >= 128) {
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
					check[index] = true;
				}
				prevToggle = coord;
			}

			if (paletteRect.contains(click.x, click.y))
				palette.mousePressed(click);
			
			if (paintCanRect.contains(click.x, click.y)) {
				if (selectedTool.equals("PAINTCAN")) {
					selectedTool = "";
				} else {
					selectedTool = "PAINTCAN";
				}
			}
			
			if (refreshRect.contains(click.x, click.y)) {
				reset();
			}
			
			if (saveRect.contains(click.x, click.y)) {
				saveImage();
			}
			
			if (frameSelect.contains(click.x, click.y) ) {
				showFrameSelect();
			}
			
			if (addRect.contains(click.x, click.y)) {
				changeColor();
			}
			
			if (resetRect.contains(click.x, click.y)) {
				palette.reset();
			}
			
			board.mousePressed(click.x, click.y, surface, 1, framesDone());
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


