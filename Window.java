/**
 * Michael Chovanak and Wagih Henawi
 * 3/25/20
 * Window.java creates the game GUI.
 * Code made by YouTube user 'RealTutsGML' found on URL: https://www.youtube.com/playlist?list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa 
 */

package csc207Game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;

public class Window extends Canvas{
	
	private static final long serialVersionUID = -1134098370765710346L;
	private static int widthOffset;
	private static int heightOffset;
	
	/**
	 * Window
	 * @param width, an int
	 * @param height, an int
	 * @param title, a String
	 * @param game, a Game
	 * creates a window for the game
	 */
	public Window(int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close button actually works
		frame.setResizable(false); //cannot resize window
		frame.setLocationRelativeTo(null); //starts window in the center of screen vs top left
		frame.add(game); //adds the game class into the frame
		frame.setVisible(true); //sets the frame to visible so user can see the frame

		Insets insets = frame.getInsets(); //gives the size of the borders
		widthOffset = insets.left + insets.right; //the right edge is shifted this many pixels left
		heightOffset = insets.top + insets.bottom; //the bottom edge is shifted this many pixels up (subtract this from HEIGHT for border)
		System.out.println(widthOffset + " " + heightOffset + " " + insets);
		game.start(); //runs the start method in the game class
	}
	
	public static int getWidthOffset()
	{
		return widthOffset;
	}
	
	public static int getHeightOffset()
	{
		return heightOffset;
	}
}

