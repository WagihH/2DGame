/**
 * Michael Chovanak and Wagih Henawi
 * 5/2/20
 * Key.java creates the key object.
 */

package csc207Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Key extends GameObject {

	/**
	 * Key
	 * @param x,  an int
	 * @param y,  an int
	 * @param id, an ID creates a key at specified x y
	 */
	Key(int x, int y, ID id, Handler handler) {
		super(x, y, id);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, 15, 15);
		g.setColor(Color.yellow);
		g.fillOval(x + 2, y + 2, 11, 11);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
	}

}
