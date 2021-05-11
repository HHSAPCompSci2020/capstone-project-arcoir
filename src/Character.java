import java.awt.Rectangle;

public class Character {
	// Fields
	private Animation animation;
	private int lives;
	private double x, y;
	private float velX, velY;
	private Rectangle hitBox;
	private boolean isAlive;
	// Constructor
	public Character(Animation a, int lives, int x, int y) {
		animation = a;
		isAlive = true;
		this.lives = lives;
		velX = 0;
		velY = 0;
		this.x = x;
		this.y = y;
		hitBox = new Rectangle();
	}
	//Methods
	
	public void translate(int xDir, int yDir){
		x += xDir;
		y += yDir;
	}
	
	public void attack(Character other) {
		other.takeDamage();
	}
	
	public void takeDamage() {
		lives--;
		if(lives == 0) {
			isAlive = false;
		}
	}
	
	public void setX(double xCoord) {
		x = xCoord;
	}
	public void setY(double yCoord) {
		y = yCoord;
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	

}


