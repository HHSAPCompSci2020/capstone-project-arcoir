package game;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
/** A class that represents all "living" entities in the game. This includes both the main character and the enemies.
 * This class was adapted from Mr. Shelby's Mario class in the AnimationDemoProcession Demo
 * 
 * @author Lead: Nicole Spaulding
 *
 */
public class Entity {
	// Fields
	private Animation animation;
	private double lives;
	private double x, y;
	private double velX, velY;
	private Rectangle hitBox;
	private boolean isAlive, onASurface, movesXAxis;
	private double friction;
	private double gravity;
	private double jumpStrength;
	private double imgShiftX, imgShiftY;
	private String type;
	
	// Constructor
	/**Creates an instance of an Entity with a specified animation, type, lives, and (x, y) coordinates 
	 * 
	 * @param a the specified animation
	 * @param type A string to specify the type of entity
	 * @param lives The number of lives this entity has
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Entity(Animation a, String type, int lives, int x, int y) {
		imgShiftX = 0;
		imgShiftY = 0;
		animation = a;
		isAlive = true;
		this.type = type;
		this.lives = lives;
		velX = 0;
		velY = 0;
		this.x = x;
		this.y = y;
		hitBox = new Rectangle(x, y, 40, 90);
		friction = 0.85;
		gravity = 0.7;
		jumpStrength = 12;
		onASurface = false;
		movesXAxis = true;
	}
	//Methods
	
	/**updates the entity as it acts and is acted upon by forces and obstacles
	 * 
	 * @param obstacles the obstacles interacting with the entity
	 */
	public void act(ArrayList<Ground> obstacles) {
		double xCoord = getX();
		double yCoord = getY();
		double width = getWidth();
		double height = getHeight();
		
		velY += gravity;
		double yCoord2 = yCoord + velY;
		
		Rectangle2D.Double strechY = new Rectangle2D.Double(xCoord,Math.min(yCoord,yCoord2),width,height+Math.abs(velY));
		
		onASurface = false;
		
		if (velY > 0) {
			Ground standingSurface = null;
			for (Ground s : obstacles) {
				if (s.getRect().intersects(strechY)) {
					//System.out.println("made it");
					onASurface = true;
					standingSurface = s;
					velY = 0;
				}
			}
			if (standingSurface != null) {
				Rectangle r = standingSurface.getRect();
				yCoord2 = r.getY()-height;
			}
		} else if (velY < 0) {
			Ground headSurface = null;
			for (Ground s : obstacles) {
				if (s.getRect().intersects(strechY)) {
					headSurface = s;
					velY = 0;
				}
			}
			if (headSurface != null) {
				Rectangle r = headSurface.getRect();
				yCoord2 = r.getY()+r.getHeight();
			}
		}

		if (Math.abs(velY) < .5)
			velY = 0;

		// ***********X AXIS***********


		velX *= friction;

		double xCoord2 = xCoord + velX;

		Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(xCoord,xCoord2),yCoord2,width+Math.abs(velX),height);

		if (velX > 0) {
			Ground rightSurface = null;
			for (Ground s : obstacles) {
				if (s.getRect().intersects(strechX)) {
					rightSurface = s;
					velX = 0;
				}
			}
			if (rightSurface != null) {
				Rectangle r = rightSurface.getRect();
				xCoord2 = r.getX()-width;
			}
		} else if (velX < 0) {
			Ground leftSurface = null;
			for (Ground s : obstacles) {
				if (s.getRect().intersects(strechX)) {
					leftSurface = s;
					velX = 0;
				}
			}
			if (leftSurface != null) {
				Rectangle r = leftSurface.getRect();
				xCoord2 = r.getX()+r.getWidth();
			}
		}


		if (Math.abs(velX) < 0.25)
			velX = 0;

		if(movesXAxis)
			setX(xCoord2);
		setY(yCoord2);
		
		updateHitbox();
	}
	
	/**Draws the entity to the PApplet
	 * 
	 * @param g the specified PApplet
	 */
	public void draw(PApplet g) {
		animation.update();
		//g.rect((int)x, (int)y, getWidth(), getHeight());
		g.image(animation.getFrame(), (float)(x + imgShiftX), (float)(y + imgShiftY));

	}
	
	/**Shifts the character in the y-direction to allow it to jump
	 * 
	 */
	public void jump() {
		if(onASurface)
			velY -= jumpStrength;
	}
	
	/**Translated the character in the x direction by the specified amount
	 * 
	 * @param xDir the specified amount to translate by
	 */
	public void translate(double xDir){
		//if (velX <= 10 && velX >= -10)
			velX += xDir;
	}
	
	/**Attacks the specified entity.
	 * 
	 * @param other the specified entity
	 */
	public void attack(Entity other) {
		if (other.getType().equals("ENEMY"))
			other.takeDamage();
		else {
			other.takeDamage(0.01);
		}
	}
	
	/**Makes this entity take damage in response to an attack.
	 * 
	 */
	public void takeDamage() {
		lives--;
		if(lives <= 0) {
			isAlive = false;
		}
	}
	
	/**Makes this entity take the specified amount of damage in response to an attack.
	 * 
	 * @param i the specified amount of damage to take
	 */
	public void takeDamage(double i) {
		lives -= i;
		if (lives <= 0) {
			isAlive = false;
		}
	}
	
	/**returns this entity's type
	 * 
	 * @return this entity's string type
	 */
	public String getType() {
		return type;
	}
	
	/**return the amount of lives this character currently has
	 * 
	 * @return amount of lives
	 */
	public double getLives() {
		return lives;
	}
	
	/**Sets the number of lives to the specified number
	 * 
	 * @param num the specified number of lives
	 */
	public void setLives(double num) {
		lives = num;
		isAlive = true;
	}
	
	/**sets this entity's x-coordinate to the specified number
	 * 
	 * @param xCoord the specified x-coordinate
	 */
	public void setX(double xCoord) {
		x = xCoord;
	}
	
	/**sets this entity's y-coordinate to the specified number
	 * 
	 * @param yCoord the specified y-coordinate
	 */
	public void setY(double yCoord) {
		y = yCoord;
	}

	/**returns this entity's current x-coordinate 
	 * 
	 * @return the x-coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**returns this entity's current x-coordinate 
	 * 
	 * @return the y-coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**returns this entity's hitbox
	 * 
	 * @return the rectangle representing this entity's hitbox
	 */
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	/** returns the width of returns this entity's hitbox 
	 * 
	 * @return the width of returns this entity's hitbox
	 */
	public int getWidth() {
		return hitBox.width;
	}
	
	/**returns the height of returns this entity's hitbox 
	 * 
	 * @return the height of returns this entity's hitbox
	 */
	public int getHeight() {
		return hitBox.height;
	}
	
	/** returns the velocity of this entity in the x direction 
	 * 
	 * @return the velocity of this entity in the x direction 
	 */
	public double getVelX() {
		return velX;
	}
	
	/** returns the velocity of this entity in the y direction 
	 * 
	 * @return the velocity of this entity in the y direction 
	 */
	public double getVelY() {
		return velY;
	}
	
	/**returns whether or not this entity is currently on a surface
	 * 
	 * @return true if this entity is on a surface, false otherwise
	 */
	public boolean getSurfaceState() {
		return onASurface;
	}
	
	/**checks whether this entity intersects with the specified entity
	 * 
	 * @param other the specified entity
	 * @return true if they intersect, false otherwise
	 */
	public boolean intersects(Entity other) {
		if(hitBox.intersects(other.getHitBox())) {
			return true;
		}
		return false;
	}
	
	/**sets this entity's animation to the specified animation
	 * 
	 * @param a the specified animation
	 */
	public void setAnimation(Animation a ) {
		this.animation = a;
	}
	
	/** sets whether this entity will move in the x direction or not
	 * 
	 * @param moves the specified choice, true if this entity does move in the x direction and false otherwise 
	 */
	public void setMovesXAxis(boolean moves) {
		movesXAxis = moves;
	}
	
	/**returns this entity's animation
	 * 
	 * @return this entity's animation
	 */
	public Animation getAnimation() {
		return animation;
	}
	
	/** returns the current living state of this entity
	 * 
	 * @return true if alive, false otherwise
	 */
	public boolean getLiveState() {
		return isAlive;
	}
	
	/**sets the width of this entity's hitbox to the specified width
	 * 
	 * @param w the specified width
	 */
	public void setWidth(int w) {
		hitBox = new Rectangle((int)x, (int)y, w, getHeight());
	}
	
	/**sets the height of this entity's hitbox to the specified width
	 * 
	 * @param w the specified height
	 */
	public void setHeight(int h) {
		hitBox = new Rectangle((int)getX(), (int)getY(), getWidth(), h);
	}
	
	/**updates this entity's hitbox to its current dimensions
	 * 
	 */
	public void updateHitbox() {
		hitBox = new Rectangle((int)getX(), (int)getY(), getWidth(), getHeight() );
	}
	
	/**adjusts the shift of the images in the animation by the specified amounts
	 * 
	 * @param x the amount to be shifted in the x direction
	 * @param y the amount to be shifted in the y direction
	 */
	public void adjustImgShift(int x, int y) {
		imgShiftX = x;
		imgShiftY = y;
	}
	
}


