/**
 * Michael Chovanak and Wagih Henawi
 * 3/26/20
 * Handler.java updates and renders objects to the screen.
 * Code made by YouTube user 'RealTutsGML' found on URL: https://www.youtube.com/playlist?list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
 */

package csc207Game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	/**
	 * tick
	 * does the tick command for each GameObject in object
	 */
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	/**
	 * render
	 * @param g, Graphics
	 * does the render command for each GameObject in object
	 */
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	/**
	 * getObjects
	 * @return object, a LinkedList<GameObject>
	 */
	public LinkedList<GameObject> getObjects()
	{
		return object;
	}
	
	/**
	 * addObject
	 * @param object, a GameObject
	 * adds object to object LinkedList
	 */
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	/**
	 * removeObject
	 * @param object, a GameObject
	 * removes a specific object from the object LinkedList
	 */
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	/**
	 * removeObjects
	 * removes all objects from handler by creating a new LinkedList, adding a player, then replacing objects with the new LinkedList
	 */
	public void removeObjects()
	{
		LinkedList<GameObject> object = new LinkedList<GameObject>();
		object.add(new Player(20,(Game.HEIGHT - Window.getHeightOffset()) / 2,ID.Player, this));
		this.object = object;
		
	}
}
