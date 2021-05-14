import processing.core.PApplet;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Dashboard {

	// Fields
	private Map realMap;
	private Rectangle help, map, pause; 
	
	// Constructor
	public Dashboard () {
		help = new Rectangle(10, 10, 50, 50);
		map = new Rectangle();
		pause = new Rectangle();		
	}
	
	//Methods
	
	
	public void draw(PApplet marker) {
		//load images
		
		//NULL ON BACKGROUND SEND HELP
		marker.rect(help.x, help.y, help.width, help.height);
	}
	
	public void mousePressed(double x, double y) {
		if (help.contains(x, y)) {
			JOptionPane.showMessageDialog(null, readData("resources/help/ArcoirHelp.txt"));
		}
		if (map.contains(x, y)) {
			
		}
		if (pause.contains(x, y)) {
			
		}
	}
			
	private String readData(String fileName) {
		File dataFile = new File(fileName);
		String text = "";

		if (dataFile.exists()) {

			FileReader reader = null;
			Scanner in = null;
			try {
					reader = new FileReader(dataFile);
					in = new Scanner(reader);
					
					while (in.hasNext()) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							text += line.charAt(i);
					}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + fileName + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
			
		} else {
			throw new IllegalArgumentException("Data file " + fileName + " does not exist.");
		}
		
		return text;
	}
}
