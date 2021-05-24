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
	private Rectangle paletteRect, paintCanRect, saveRect, frameSelect, refreshRect, 
	colorRect, addRect, resetRect, typeRect, pencilRect, downloadRect;
	private Dashboard board;
	private Color [][][] characters;
	private Color[][] character1, character2, character3, idle;
	private Color[][] enemy1, enemy2;
	private PImage frame1R, frame2R, frame3R, frame4R, idleR, frame1L, frame2L, frame3L, frame4L, idleL;
	private int index, prevIndex;
	private Point prevToggle;
	private PImage paintCanIcon, saveIcon, refreshIcon, addIcon, resetIcon, pencilIcon;
	private String selectedTool, currentFrame;
	private boolean move1, move2, move3, realIdle, allDone;
	private boolean [] check;
	private int [] pencilWidth;
	private int widthIndex;
	
	private boolean isEnemy;
	private String type;
	private Color [][][] enemies;
	private PImage e1R, e2R, e1L, e2L;
	private boolean emove1, emove2, eallDone;
	private String currentEnemyFrame;
	private int enemyIndex, prevEnemyIndex;
	private boolean [] enemyCheck;
	private int recursionCount;

	/**
	 * Constructs a DrawingScreen object.
	 * 
	 * @param surface PApplet surface that uses Processing methods.
	 */
	public DrawingScreen(DrawingSurface surface) {
		this.surface = surface;
		isEnemy = false;
		palette = new ColorPalette();
		paletteRect = new Rectangle (660, 0, 40, 240);
		character1 = new Color [128][128];
		character2 = new Color [128][128];
		character3 = new Color [128][128];
		idle = new Color [128][128];
		check = new boolean [4];
		index = 3;
		prevIndex = 0;
		pencilWidth = new int [] {1, 2};
		widthIndex = 0;
		
		paintCanRect = new Rectangle (710, 0, 40, 40);
		saveRect = new Rectangle  (755, 0, 40, 40);
		addRect = new Rectangle(710, 45, 40, 40);
		refreshRect = new Rectangle (755, 45, 40, 40);
		resetRect = new Rectangle(710, 90, 40, 40);
		pencilRect = new Rectangle (755, 90, 40, 40);
		
		typeRect = new Rectangle (710, 136, 85, 30);
		frameSelect = new Rectangle (710, 172, 85, 30);
		colorRect = new Rectangle (710, 209, 85, 30);
		downloadRect = new Rectangle (660, 247, 134, 41);
		
		move1 = false;
		move2 = false;
		move3 = false;
		realIdle = false;
		selectedTool = "";
		currentFrame = "Idle";
		characters = new Color [][][] {character1, character2, character3, idle};
		
		type = "Hero";
		
		enemy1 = new Color [64][64];
		enemy2 = new Color [64][64];
		
		enemies = new Color [][][] {enemy1, enemy2};
		
		emove1 = false;
		emove2 = false;
		eallDone = false;
		
		enemyIndex = 0;
		prevEnemyIndex = 0;
		currentEnemyFrame = "Move 1";
		enemyCheck = new boolean [2];
		recursionCount = 0;
	}

	/**
	 * Saves the frame from the drawing interface as an actual frame to be used in an animation.
	 */
	public void saveImage() {
		if (!isEnemy) {
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
			if (move1 && move2 && move3 && realIdle) {
				allDone = true;
			}
		} else {
			if (enemies[enemyIndex] != null && enemyCheck[enemyIndex] != false) {
				if (enemyIndex == 0) {
					e1R.loadPixels();
					int i = 0;
					for (int r = 0; r < enemy1.length; r++) {
						for (int c = 0; c < enemy1.length; c++) {
							if (enemy1[r][c] != null)
								e1R.pixels[i] = surface.color(enemy1[r][c].getRed(), enemy1[r][c].getGreen(), enemy1[r][c].getBlue());
							else {
								e1R.pixels[i] = surface.color(0, 0, 0, 0); //transparent
							}

							i++;
						}
					}
					e1R.updatePixels();
					e1L = reflect(enemy1);
					e1L.updatePixels();
					emove1 = true;
					JOptionPane.showMessageDialog(null, "Move 1 successfully saved");
				} else if (enemyIndex == 1) {
					frame2R.loadPixels();
					int i = 0;
					for (int r = 0; r < enemy2.length; r++) {
						for (int c = 0; c < enemy2.length; c++) {
							if (enemy2[r][c] != null)
								e2R.pixels[i] = surface.color(enemy2[r][c].getRed(), enemy2[r][c].getGreen(), enemy2[r][c].getBlue());
							else {
								e2R.pixels[i] = surface.color(0, 0, 0, 0);
							}
							i++;
						}
					}
					e2R.updatePixels();
					e2L = reflect(enemy2);
					e2L.updatePixels();
					emove2 = true;
					JOptionPane.showMessageDialog(null, "Move 2 successfully saved");
				}
				if (emove1 && emove2) {
					eallDone = true;
				}
			}	
		}
	}
	
	private PImage reflect (Color [][] copy) {
		PImage reflected;
		if (!isEnemy) {
			Color [][] reflect = new Color [128][128];
			for (int r = 0; r < 128; r++) {
				for (int c = 0; c < 128; c++) {
					reflect[r][c] = copy[r][128 - c - 1];
					reflect[r][128 - c - 1] = copy[r][c];
				}
			}
			reflected = surface.createImage(128, 128, PConstants.ARGB);
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
		} else {
			Color [][] reflect = new Color [64][64];
			for (int r = 0; r < 64; r++) {
				for (int c = 0; c < 64; c++) {
					reflect[r][c] = copy[r][64 - c - 1];
					reflect[r][64 - c - 1] = copy[r][c];
				}
			}
			reflected = surface.createImage(64, 64, PConstants.ARGB);
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
		
		e1R = surface.createImage(64, 64, PConstants.ARGB);
		e2R = surface.createImage(64, 64, PConstants.ARGB);
		e1L = surface.createImage(64, 64, PConstants.ARGB);
		e2L = surface.createImage(64, 64, PConstants.ARGB);
	}
	/**
	 * 
	 * @return the hero frames.
	 */
	public PImage[][] getFrames() {
		PImage [][] frames = new PImage[][] {{frame1R, frame2R, frame3R, frame4R}, {idleR}, 
			{frame1L, frame2L, frame3L, frame4L}, {idleL}};
		return frames;
	}
	
	/**
	 * 
	 * @return the enemy frames.
	 */
	public PImage [][] getEnemyFrames() {
		PImage [][] frames = new PImage [][] {{e1R, e2R}, {e1L, e2L}};
		return frames;
	}
	
	/**
	 * 
	 * @return whether all hero frames have been saved or not
	 */
	public boolean framesDone() {
		return allDone;
	}
	
	/**
	 * 
	 * @return whether all enemy frames have been saved or not
	 */
	public boolean eframesDone() {
		return eallDone;
	}
	
	public void setup () {
		board = new Dashboard(DRAWING_WIDTH * 2/3, DRAWING_HEIGHT - DRAWING_WIDTH/20 - 20, DRAWING_WIDTH, DRAWING_HEIGHT, 
				false, false, surface.loadImage("resources/dash/help/helpIcon.gif"), surface.loadImage("resources/dash/back.gif"));
		paintCanIcon = surface.loadImage("resources/drawingIcons/paintcan.gif");
		saveIcon = surface.loadImage("resources/drawingIcons/save.gif");
		refreshIcon = surface.loadImage("resources/drawingIcons/refresh.gif");
		addIcon = surface.loadImage("resources/drawingIcons/add.gif");
		resetIcon = surface.loadImage("resources/drawingIcons/reset.gif");
		pencilIcon = surface.loadImage("resources/drawingIcons/pencil.gif");
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
		surface.image(pencilIcon, pencilRect.x,  pencilRect.y, pencilRect.width, pencilRect.height);
		
		surface.pushStyle();
		surface.noFill();
		surface.stroke(91, 15, 0);
		surface.strokeWeight(2);
		surface.rect(frameSelect.x, frameSelect.y, frameSelect.width, frameSelect.height);
		surface.rect(typeRect.x,  typeRect.y,  typeRect.width,  typeRect.height);
		surface.rect(downloadRect.x, downloadRect.y, downloadRect.width, downloadRect.height);
		surface.popStyle();
		
		surface.pushStyle();
		surface.fill(91, 15, 0);
		surface.textSize(10);
		surface.textLeading(13);
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		if (!isEnemy)
			surface.text("Current Frame: \n" + currentFrame, frameSelect.x + 45, frameSelect.y + 12);
		else {
			surface.text("Current Frame: \n" + currentEnemyFrame, frameSelect.x + 45, frameSelect.y + 12);
		}
		surface.text(type, typeRect.x + 44, typeRect.y + 13);
		surface.textSize(15);
		surface.text("Download Image", downloadRect.x + 68, downloadRect.y + 15);
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
		if (!isEnemy) {
			String [] realFrames = new String [] {"Idle", "Move 1", "Move 2", "Move 3"};
			String input = (String)JOptionPane.showInputDialog(null, "Choose a frame to draw", "Which frame?", 
					JOptionPane.QUESTION_MESSAGE, null, realFrames, realFrames[prevIndex]);

			if (input == null)
				return;

			selectFrame(input);
		} else {
			String [] realFrames = new String [] {"Move 1", "Move 2"};
			String input = (String)JOptionPane.showInputDialog(null, "Choose a frame to draw", "Which frame?", 
					JOptionPane.QUESTION_MESSAGE, null, realFrames, realFrames[prevEnemyIndex]);

			if (input == null)
				return;

			selectFrame(input);
		}
	}
	
	private void selectFrame(String input) {
		if (!isEnemy) {
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
		} else {
			if (input.equals("Move 1")) {
				enemyIndex = 0;
				prevEnemyIndex = 0;
				currentEnemyFrame = "Move 1";
			} else {
				enemyIndex = 1;
				prevEnemyIndex = 1;
				currentEnemyFrame = "Move 2";
			}
		}
	}

	/**
	 * Changes the selected color to a user input color.
	 */
	public void changeColor() {
		int r = -1, g = -1, b = -1;

		String first = JOptionPane.showInputDialog(null, "Enter r value.");
		if (first != null && isNumeric(first)) {
			r = Integer.parseInt(first);
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid value.");
			return;
		}
		String second = JOptionPane.showInputDialog(null, "Enter g value.");
		if (second != null && isNumeric(second)) {
			g = Integer.parseInt(second);
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid value.");
			return;
		}
		String third = JOptionPane.showInputDialog(null, "Enter b value.");
		if (third != null && isNumeric(third)) {
			b = Integer.parseInt(third);
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid value.");
			return;
		}
		
		if (!(r < 0 || g < 0 || b < 0)) {
			palette.changeColor(palette.getCurrentIndex(), r, g, b);
		} 
	}
	
	public boolean isNumeric(String str)
	{
	    for (int i = 0; i < str.length(); i++)
	    {
	        if (!Character.isDigit(str.charAt(i)))
	        		return false;
	    }
	    return true;
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
		if (!isEnemy) {
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
		} else {
			float sideLength = side/enemies[enemyIndex].length;

			for (int i = 0; i < enemies[enemyIndex].length; i++) {
				for (int j = 0; j < enemies[enemyIndex][0].length; j++) {
					float rectX = x + j * sideLength;
					float rectY = y + i * sideLength;

					if(enemies[enemyIndex][i][j] != null) {
						Color current = enemies[enemyIndex][i][j];

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
		if (!isEnemy) {
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
		} else {
			float sideLength = side/enemies[enemyIndex][0].length;
			int gridCount = 0;

			for (int i = 0; i < enemies[enemyIndex].length; i++) {
				for (int j = 0; j < enemies[enemyIndex][0].length; j++) {
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
		int a = -1; //def not on the grid
		int b = -1; //def not on the grid

		if (!isEnemy) {
			float rectWidth = width/characters[index][0].length;
			float rectHeight = height/characters[index].length;
			float x1 = p.x - x;
			float y1 = p.y - y;

			if ((x <= p.x && p.x < x + width) && (y <= p.y && p.y < y + height)) {
				a = (int)(y1/rectHeight);
				b = (int)(x1/rectWidth);
			}
		} else {
			float rectWidth = width/enemies[enemyIndex][0].length;
			float rectHeight = height/enemies[enemyIndex].length;
			float x1 = p.x - x;
			float y1 = p.y - y;

			if ((x <= p.x && p.x < x + width) && (y <= p.y && p.y < y + height)) {
				a = (int)(y1/rectHeight);
				b = (int)(x1/rectWidth);
			}
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
		if (!isEnemy) {
			characters[index][i][j] = palette.getSelectedColor();
			if (pencilWidth[widthIndex] == 2) {
				if (j + 1 < characters[index].length)
					characters[index][i][j + 1] = palette.getSelectedColor();
				if (i + 1 < characters[index].length)
					characters[index][i + 1][j] = palette.getSelectedColor();
				if (i + 1 < characters[index].length && j + 1 < characters[index].length)
					characters[index][i + 1][j + 1] = palette.getSelectedColor();
			}
		} else {
			enemies[enemyIndex][i][j] = palette.getSelectedColor();
			if (pencilWidth[widthIndex] == 2) {
				if (j + 1 < enemies[enemyIndex].length)
					enemies[enemyIndex][i][j + 1] = palette.getSelectedColor();
				if (i + 1 < enemies[enemyIndex].length)
					enemies[enemyIndex][i + 1][j] = palette.getSelectedColor();
				if (i + 1 < enemies[enemyIndex].length && j + 1 < enemies[enemyIndex].length)
					enemies[enemyIndex][i + 1][j + 1] = palette.getSelectedColor();
			}		
		}
	}

	/**
	 * Fills an object beginning at x,y.
	 * 
	 * @param x The x coordinate of the beginning of the paint can fill.
	 * @param y The y coordinate of the beginning of the paint can fill.
	 */
	public void paintCanFill(int x, int y) {
		fill(x, y);
		recursionCount = 0;
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
	
	public void downloadImage() {
		if (!isEnemy) {
			PImage [][] frames = getFrames();
			if (index == 3) {
				frames[1][0].save("HeroIdle.png");
			} else {
				frames[0][index].save("HeroMove" + index + ".png");
			}
			JOptionPane.showMessageDialog(null, "Frame successfully downloaded.");
		} else {
			PImage [][] frames = getEnemyFrames();
			if (enemyIndex == 0)
				frames[0][0].save("EnemyMove1.png");
			else {
				frames[1][0].save("EnemyMove2.png");
			}	
			JOptionPane.showMessageDialog(null, "Frame successfully downloaded.");
		}
	}
	
	/**
	 * Resets the frame to its default state.
	 */
	public void reset() {
		if (!isEnemy) {
		characters[index] = new Color [128][128];
		} else {
			enemies[enemyIndex] = new Color [64][64];
		}
	}
	
	private void fill (int x, int y) {
		recursionCount++;
		if(recursionCount >= 5000) {
			
			return;
		}
		if (!isEnemy) {
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
		} else {
			if (x < 0 || x >= 64 || y < 0 || y >= 64) {
				//do nothing
			} else if (enemies[enemyIndex][x][y] != null && enemies[enemyIndex][x][y].equals(palette.getSelectedColor())) {
				//do nothing
			} else {
				enemies[enemyIndex][x][y] = palette.getSelectedColor();

				fill(x + 1, y);
				fill(x - 1, y);
				fill(x, y + 1);
				fill(x, y - 1);
			}
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
					if (!isEnemy) {
					check[index] = true;
					} else {
						enemyCheck[enemyIndex] = true;
					}
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
			
			if (pencilRect.contains(click.x, click.y)) {
				String widths[] = new String [] {"1", "2"};
				String input = (String)JOptionPane.showInputDialog(null, "Choose a pencil width.", "Which width?", 
						JOptionPane.QUESTION_MESSAGE, null, widths, "" + widths[widthIndex]);
				if (Integer.parseInt(input) == 1) 
					widthIndex = 0;
				else {
					widthIndex = 1;
				}
			}
			
			if (typeRect.contains(click.x, click.y)) {
				isEnemy = !isEnemy;
				if (isEnemy) {
					type = "Enemy";
				} else {
					type = "Hero";
				}
			}
			
			if (downloadRect.contains(click.x, click.y)) {
				downloadImage();
			}
			
			board.mousePressed(click.x, click.y, surface, 1, framesDone(), eframesDone());
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


