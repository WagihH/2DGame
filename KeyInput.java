/**
 * Michael Chovanak and Wagih Henawi
 * 4/21/20
 * KeyInput.java determines the function of each key press and release.
 * Code inspired by YouTube user 'RealTutsGML' found on URL: https://www.youtube.com/playlist?list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
 */

package csc207Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	/**
	 * keyPressed
	 * @param e, a KeyEvent
	 * does specific actions based on keyboard keys pressed
	 */
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		for (int i = 0; i <handler.object.size(); i++) //go through each game object
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player) //if tempObject is the player, perform movements related to the player
			{
				if(key == KeyEvent.VK_RIGHT) //key input for skipping to the next level
					Level.nextLevel(handler);
				if(key == KeyEvent.VK_W) //W input
					((Player) tempObject).setW(true);
				if(key == KeyEvent.VK_A) //A input
					((Player) tempObject).setA(true);
				if(key == KeyEvent.VK_S) //S input
					((Player) tempObject).setS(true);
				if(key == KeyEvent.VK_D) //D input
					((Player) tempObject).setD(true);
			}
		}
	}
	
	/**
	 * keyReleased
	 * @param e, a KeyEvent
	 * does specific actions based on keyboard keys being released.
	 * same as above but setting the booleans for key presses to be false, indicating they are no longer being pressed
	 */
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		for (int i = 0; i <handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player)
			{
				if(key == KeyEvent.VK_W)
					((Player) tempObject).setW(false);
				if(key == KeyEvent.VK_A)
					((Player) tempObject).setA(false);
				if(key == KeyEvent.VK_S)
					((Player) tempObject).setS(false);
				if(key == KeyEvent.VK_D)
					((Player) tempObject).setD(false);
			}
		}
	}
}