/**
 * Michael Chovanak and Wagih Henawi
 * 5/8/20
 * Wall.java creates the wall object.
 */

package csc207Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {
	
	private int wid, hght;
	
	/**
	 * 
	 * @param x, an int
	 * @param y, an int
	 * @param id, an ID
	 * @param width, an int
	 * @param height, and int
	 * creates a wall at specified x y location and specified height and width
	 */
	public Wall(int x, int y, ID id,int width, int height) {
		super(x,y,id);
		hght = height;
		wid = width;
	}
	

	@Override
	public void tick() {
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, wid, hght);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,wid,hght);
	}

}
