/**
 * Michael Chovanak and Wagih Henawi
 * 3/21/20
 * Player.java creates the Player object.
 *  
 */

package csc207Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	private int xVel, yVel;
	private boolean w,a,s,d;
	Handler handler;
	
	/**
	 * Player
	 * @param x, an int
	 * @param y, an int
	 * @param id, an ID
	 * @param handler, a Handler
	 * creates a Player at specified x y.
	 */
	public Player(int x, int y, ID id, Handler handler)
	{
		super(x,y,id);
		xVel = 2;
		yVel = 2;
		w = false;
		a = false;
		s = false;
		d = false;
		
		this.handler = handler;
	}
	
	/**
	 * setXVel
	 * @param vel, an int
	 * changes the xVel of Player
	 */
	public void setXVel(int vel)
	{
		xVel = vel;
	}
	
	/**
	 * setYVel
	 * @param vel, an int
	 * changes the yVel of Player
	 */
	public void setYVel(int vel)
	{
		yVel = vel;
	}
	
	/**
	 * getXVel
	 * @return xVel, an int
	 * returns the Player xVel
	 */
	public int getXVel()
	{
		return xVel;
	}
	
	/**
	 * getXVel
	 * @return yVel, an int
	 * returns the Player yVel
	 */
	public int getYVel()
	{
		return yVel;
	}
	
	
	public void setW(boolean val)
	{
		w = val;
	}
	public void setA(boolean val)
	{
		a = val;
	}
	public void setS(boolean val)
	{
		s = val;
	}
	public void setD(boolean val)
	{
		d = val;
	}
	
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
	}
	
	/**
	 * enemyCollision
	 * checks if player collides with different GameObjects and does certain actions depending on what Player collides with
	 */
	private void enemyCollision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if (getBounds().intersects(tempObject.getBounds()) && tempObject.getID().equals(ID.BasicEnemy)) 
				HUD.setHEALTH(HUD.getHEALTH() - 7);
			
			else if (getBounds().intersects(tempObject.getBounds()) && tempObject.getID().equals(ID.Key) && HUD.getKEYS() != 1) {
				HUD.setKEYS(1);
				System.out.println("Key Obtained!");
				handler.removeObject(tempObject);
			}
			else if(getBounds().intersects(tempObject.getBounds()) && tempObject.getID().equals(ID.Door) && HUD.getKEYS() == 1)
			{
				Level.nextLevel(handler);
			}
			else if (getBounds().intersects(tempObject.getBounds()) && tempObject.getID().equals(ID.Wall)) {
				if (y > tempObject.getY() && w)
					y += 2;
				if (x > tempObject.getX() && a)
					x += 2;
				if (y < tempObject.getY() && s)
					y -= 2;
				if (x < tempObject.getX() && d)
					x -= 2;
			}
		}
	}
	
	
	@Override
	public void tick() {
		x = Game.clamp(x, 20, Game.WIDTH - Window.getWidthOffset() - 40); //clamp the x coordinate to be within the border's bounds
		y = Game.clamp(y, 45, Game.HEIGHT - Window.getHeightOffset() - 40); //clamp the y coordinate to be within the border's bounds
		
		
		if(w && s); //if opposite keys are pressed, do nothing
		else if(w) //if only one of the keys are pressed, move in the intended direction
			y -= yVel;
		else if (s)
			y += yVel;
		
		if(a && d); //if opposite keys are pressed, do nothing
		else if (a) //if only one of the keys are pressed, move in the intended direction
			x -= xVel;
		else if (d)
			x += xVel;
		
		enemyCollision(); //check for enemy collision
		
	}

	

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 20, 20);
		g.setColor(Color.green);
		g.fillRect(x + 2, y + 2, 16, 16);
		
		if(HUD.getKEYS() == 1) //gives a different look for the character while carrying the key
		{
			g.setColor(Color.black);
			g.fillOval(x + 2, y + 2, 15, 15);
			g.setColor(Color.yellow);
			g.fillOval(x + 4, y + 4, 11, 11);
		}
	}


}