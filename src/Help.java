import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Help {
	private JFrame helpWindow;
	
	public Help () {
		helpWindow = new JFrame("Help");
	}
	
	public void show() {
		helpWindow.setSize(500,500);
		helpWindow.setResizable(false);
		
		helpWindow.setAlwaysOnTop(true);
		helpWindow.setLocationRelativeTo(null);  
		
		JTextArea text = new JTextArea(readData("resources/help/ArcoirHelp.txt"));
		text.setSize(400, 400);
	    text.setLineWrap(true);
	    text.setEditable(false);
	    text.setVisible(true);

	    JScrollPane scroll = new JScrollPane (text);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	    helpWindow.add(scroll);
	    helpWindow.setVisible(true);
	    helpWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
						text += line + "\n";
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
