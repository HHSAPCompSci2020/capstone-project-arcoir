import java.awt.Graphics;
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
		hitBox = new Rectangle(x, y, 20, 20);
	}
	//Methods
	
	public void jump() {
		velY -= 5;
	}
	
	public void duck() {
		hitBox = new Rectangle((int)x, (int)y, 20, 10);
	}
	
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
	
	public int getWidth() {
		return hitBox.width;
	}
	
	public int getHeight() {
		return hitBox.height;
	}
	

}


