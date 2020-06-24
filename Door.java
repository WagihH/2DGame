/**
 * Michael Chovanak and Wagih Henawi
 * 5/9/20
 * Door.java creates the Door object.
 */

package csc207Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends GameObject{

	/**
	 * Door
	 * @param x, an int
	 * @param y, an int
	 * @param id, an int
	 * @param handler, a Handler
	 * creates a door at x y
	 */
	public Door(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 20, 40);
		g.setColor(Color.blue);
		g.fillRect(x + 2, y + 2, 16, 36);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 40);
	}

}
