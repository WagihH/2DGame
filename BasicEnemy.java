/**
 * Michael Chovanak and Wagih Henawi
 * 5/5/20
 * BasicEnemy.java creates the BasicEnemy object.
 *  Class was partially created and inspired by YouTube user 'RealTutsGML' 
 *  	at URL: https://www.youtube.com/playlist?list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
 */

package csc207Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	
	Handler handler;
	
	/**
	 * BasicEnemy
	 * @param x, an int
	 * @param y, an int
	 * @param velX, an int
	 * @param velY, an int
	 * @param id, an ID
	 * @param handler, a Handler
	 * creates a basic enemy at x y with speeds of velX and velY
	 */
	public BasicEnemy(int x, int y,int velX ,int velY,ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		xVel = velX;
		yVel = velY;
	}
	
	/**
	 * collision
	 * makes enemys bounce off of walls when collided
	 */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if (getBounds().intersects(tempObject.getBounds()) && tempObject.getID().equals(ID.Wall)) {
				xVel *= -1;
				yVel *= -1;
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}


	
	@Override
	public void tick() {
		x += xVel;
		y += yVel;
		
		if (y <= 45 || y >= Game.HEIGHT - Window.getHeightOffset() - 40) //if the enemy reaches the border, switch directions
			yVel *= -1;
		if (x <= 20 || x >= Game.WIDTH - Window.getWidthOffset() - 40) //if the enemy reaches the border, switch directions
			xVel *= -1;
		
		collision(); //check if enemy is colliding with the wall. If so, switch direction.
	}
	


	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 20, 20);
		g.setColor(Color.red);
		g.fillRect(x + 2, y + 2, 16, 16);
	}
	
	
}
