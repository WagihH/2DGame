/**
 * Michael Chovanak and Wagih Henawi
 * 5/2/20
 * HUD.java stores HEALTH and KEYS and provides a graphical indication of these values.
 * Code inspired by YouTube user 'RealTutsGML' found on URL: https://www.youtube.com/playlist?list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
 */

package csc207Game;

import java.awt.Color;
import java.awt.Graphics;

import RoomTracking.Game;

public class HUD {
	
	private static int HEALTH = 100;
	private static int KEYS = 0;
	
	public void tick() {
	}

	/**
	 * render
	 * @param g, Graphic
	 * creates a health bar based on HEALTH
	 */
	public void render (Graphics g) {
		//Draw the Health Bar
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH - 135, 15, 100, 16);
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH - 135, 15, HEALTH, 16);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH - 135, 15, 100, 16);
		
	}
	
	public static int getHEALTH()
	{
		return HEALTH;
	}
	
	public static int getKEYS()
	{
		return KEYS;
	}
	
	public static void setHEALTH(int val)
	{
		HEALTH = val;
	}
	
	public static void setKEYS(int val)
	{
		KEYS = val;
	}
}
