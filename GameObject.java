/**
 * Michael Chovanak and Wagih Henawi
 * 3/25/20
 * GameObject.java is an abstract class. Each object in the game inherits GameObject.
 * Class was made by YouTube user 'RealTutsGML' at URL: https://www.youtube.com/playlist?list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
 */

package csc207Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y, xVel, yVel;
	protected ID id;
	
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y; 
		this.id = id;
	}
	
	/**
	 * tick
	 * a method used for GameObjects in which its contents will continuously be done while the game is running
	 */
	public abstract void tick();
	
	/**
	 * render
	 * @param g, Graphic
	 * a method to create the graphic display of each GameObject
	 */
	public abstract void render(Graphics g);
	
	/**
	 * getBounds
	 * @return Rectangle
	 * used to get the bounds of each GameObject (used for collision)
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * setX
	 * @param x, an int
	 * changes the location of the GameObject
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * setY
	 * @param y, an int
	 * changes the location of the GameObject
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * getX
	 * returns the x field of the GameObject
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * getY
	 * returns the y field of GameObject
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * getXVel
	 * returns the xVel field of GameObject
	 */
	public int getXVel() 
	{
		return xVel;
	}
	
	/**
	 * setXVel
	 * @param xVel, an int
	 * changes the xVel of GameObject
	 */
	public void setXVel(int xVel) 
	{
		this.xVel = xVel;
	}
	
	/**
	 * getYVel
	 * returns the yVel field of GameObject
	 */
	public int getYVel() 
	{
		return yVel;
	}
	
	/**
	 * setYVel
	 * @param yVel, an int
	 * changes the yVel of GameObject
	 */
	public void setYVel(int yVel) 
	{
		this.yVel = yVel;
	}
	
	/**
	 * setID
	 * @param id, an ID
	 * changes the id of the GameObject
	 */
	public void setID (ID id) {
		this.id = id;
	}
	
	/**
	 * getID
	 * returns the id of the GameObject
	 */
	public ID getID()
	{
		return id;
	}
}
