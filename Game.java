/**
 * Michael Chovanak and Wagih Henawi
 * 3/25/20
 * Game.java creates the Game object that controls the clock/running of the game.
 * 
 */

package csc207Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 2912064847230250352L;

	public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private int rightSide;
	private int bottomSide;
	private int deaths;
	
	/**
	 * Game
	 * Creates the Game objects, which starts up the handler, keyListener, and window
	 * Also adds the beginning objects to the scene (the first level with no enemies)
	 */
	public Game()
	{
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Game by Michael and Wagih", this);
		hud = new HUD();
		rightSide  = WIDTH - Window.getWidthOffset();
		bottomSide = HEIGHT - Window.getHeightOffset();
		deaths = 0;
		handler.addObject(new Player(20,bottomSide / 2,ID.Player, handler));
		handler.addObject(new Key(rightSide/2 - 15, bottomSide / 2,ID.Key, handler));
		handler.addObject(new Door(rightSide - 40, bottomSide / 2 - 15,ID.Door, handler));
		Level.setLevels(handler);
		
	}
	
	/**
	 * start
	 * creates a thread for the game and starts it
	 */
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * stop
	 * stops the game
	 */
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * run
	 * runs the game using a commonly used game clock.
	 * This Game Loop was copied from the YouTube tutorial linked at the top.
	 */
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime(); //get the current internal time
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis(); //is used to check when one second has passed to print Frames Per Second
		int frames = 0;
		while(running) //continues to loop while the program is running
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) //caps ticks to about 60 ticks per second, preventing higher/lower frame rates from changing the "speed" of the game
			{
				tick();
				delta--;
			}
			if(running) //render frames as quickly as possible, recording how many frames occur before a second passes
			{
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) //print out the frame rate every second
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	/**
	 * tick
	 * uses tick on all classes (handler) and restarts level based on health
	 */
	private void tick()
	{
		handler.tick(); //tick each GameObject in the handler
		hud.tick(); //tick the HUD
		if (HUD.getHEALTH() <= 0) //check if player has died. If so, restart the level and increment deaths
		{
			Level.restartLevel(handler);
			deaths++;
		}
	}
	
	/**
	 * render
	 * renders the background, text, and all other GameObjects (handler)
	 */
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//draw the background 
		g.setColor(Color.black);
		g.fillRect(0, 0, rightSide, bottomSide);
		g.setColor(Color.darkGray);
		g.fillRect(20, 45, rightSide - 40, bottomSide - 65);
		//draw the text, which changes based on level and level state
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("Health: ", rightSide - 270, 30);
		g.drawString("Deaths: ", rightSide - 100, 30);
		g.drawString(Integer.toString(deaths), rightSide - 35, 30);
		switch(Level.getLevelNum())
		{
		case(-1):
			g.drawString("Use W,A,S,D to move", 20, 30);
			g.drawString("Collect the Key", rightSide / 2 - 70, bottomSide / 2 - 10);
			if(HUD.getKEYS() == 1) //prints a new message once the player picks up the key
			{
				g.drawString("Move to the Door", rightSide - 200, bottomSide / 2 + 15);
			}
			break;
		case(0):
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			g.drawString("Avoid the enemies", 20, 30);
			break;
		case(100):
			g.setColor(Color.green);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 80)); 
			g.drawString("You Win!", rightSide / 2 - 160, bottomSide / 2);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			g.drawString("Thanks for Playing!", rightSide / 2 - 150, bottomSide / 2 + 40);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			g.drawString("By Michael Chovanak and Wagih Henawi", rightSide / 2 - 160, bottomSide / 2 + 65);
			break;
		default:
			
		}
		handler.render(g); //render each object in the handler
		hud.render(g); //render the HUD (health bar)
		
		g.dispose();
		bs.show();
	}
	
	/**
	 * clamp
	 * @param var, an int
	 * @param min, an int
	 * @param max, an int
	 * @return var, an int
	 * stops var from being smaller or bigger than min and max, respectively
	 */
	public static int clamp (int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else 
			return var;
	}
	
	/**
	 * getHandler
	 * @return handler, a Handler
	 */
	public Handler getHandler()
	{
		return handler;
	}
	
	//main method starts the game by calling the Game constructor.
	public static void main(String[] args)
	{
		new Game();
	}
}
