import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;

public class Character {
	// Fields
	private Animation animation;
	private int lives;
	private double x, y;
	private double velX, velY;
	private Rectangle hitBox;
	private boolean isAlive, onASurface;
	private double friction;
	private double gravity;
	private double jumpStrength;
	private double imgShiftX, imgShiftY;
	
	// Constructor
	public Character(Animation a, int lives, int x, int y) {
		imgShiftX = 0;
		imgShiftY = 0;
		animation = a;
		isAlive = true;
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
	}
	//Methods
	
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

		setX(xCoord2);
		setY(yCoord2);
		
	}
	
	public void draw(PApplet g) {
		animation.update();
		g.image(animation.getFrame(), (float)(x  + imgShiftX), (float)(y + imgShiftY));
	//	System.out.println("w: " + animation.getFrame().width+ " h: " + animation.getFrame().height);
	}
	
	public void jump() {
		if(onASurface)
			velY -= jumpStrength;
	}
	
	public void translate(double xDir){
		//if (velX <= 10 && velX >= -10)
			velX += xDir;
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
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public int getWidth() {
		return hitBox.width;
	}
	
	public int getHeight() {
		return hitBox.height;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public boolean getSurfaceState() {
		return onASurface;
	}
	
	public boolean intersects(Character other) {
		if(hitBox.intersects(other.getHitBox())) {
			return true;
		}
		return false;
	}
	
	public void setAnimation(Animation a ) {
		this.animation = a;
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public boolean getLiveState() {
		return isAlive;
	}
	
	public void setWidth(int w) {
		hitBox.setRect(x, y, w, getHeight());
	}
	
	public void setHeight(int h) {
		hitBox.setRect(x, y, getWidth(), h);
	}
	
	public void adjustImgShift(int x, int y) {
		imgShiftX = x;
		imgShiftY = y;
	}
	
}


