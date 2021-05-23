
public interface ScreenSwitcher {
	public static final int MENUSCREEN = 0;
	public static final int DRAWINGSCREEN = 1;
	public static final int GAMESCREEN = 2;
	public static final int GAMEOVERSCREEN = 3;
	
	public void switchScreen(int i);
}
