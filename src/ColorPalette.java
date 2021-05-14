import java.awt.Color;

/**
 * @author Lindsay Qin
 *
 */
public class ColorPalette {
	private Color [] palette;
	private Color selectedColor;
	
	/**
	 * Initializes a new palette with 24 default colors. Selects the first color.
	 */
	public ColorPalette () {
		palette = new Color [24];
		reset();
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
		palette[8] = new Color (0, 168, 107); //jade green
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
}
