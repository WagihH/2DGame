/**
 * Michael Chovanak and Wagih Henawi
 * 5/7/20
 * Level.java stores the GameObjects for each level
 *  
 */

package csc207Game;

import java.util.LinkedList;

public class Level {
	private static int levelNum = -1;
	private static int rightSide = Game.WIDTH - Window.getWidthOffset();
	private static int bottomSide = Game.HEIGHT - Window.getHeightOffset();
	static LinkedList<LinkedList<GameObject>> levels;
	/**
	 * setLevels
	 * @param handler, a Handler
	 * creates the levels for the game
	 * Each level is represented as a LinkedList<GameObject>, where each level is stored in another LinkedList as levels.
	 * Each GameObject for the level is added to its corresponding LinkedList, so when switching levels, every GameObject is removed
	 * from the handler and the objects for the next level are added to the handler.
	 */
	public static void setLevels(Handler handler)
	{
		levels = new LinkedList<LinkedList<GameObject>>();
		//set objects for level00
		LinkedList<GameObject> level00 = new LinkedList<GameObject>();
		levels.add(level00);
		for(int i = 160; i < 300; i += 40)
			level00.add(new BasicEnemy(i, 45, 0, 4, ID.BasicEnemy, handler));
		for(int i = 400; i < rightSide- 170; i += 40)
			level00.add(new BasicEnemy(i, 45, 0, 4, ID.BasicEnemy, handler));
		addNewKey(handler, level00);
		addNewDoor(handler, level00);

//		//set objects for level01
		LinkedList<GameObject> level01 = new LinkedList<GameObject>();
		levels.add(level01);
		for(int i = 45; i < rightSide - 40; i += 60)
			level01.add(new BasicEnemy(i, 45, 0, 4, ID.BasicEnemy, handler));
		addNewKey(handler, level01);
		addNewDoor(handler, level01);
//		
//		//set objects for level02
		LinkedList<GameObject> level02 = new LinkedList<GameObject>();
		levels.add(level02);
		for(int i = 45; i < bottomSide - 20; i += 60)
			level02.add(new BasicEnemy(100, i, 4, 0, ID.BasicEnemy, handler));
		for(int i = 45; i <rightSide - 40; i += 60)
			level02.add(new BasicEnemy(i, 45, 0, 4, ID.BasicEnemy, handler));
		addNewKey(handler, level02);
		addNewDoor(handler, level02);
		
//		//set objects for level03
		LinkedList<GameObject> level03 = new LinkedList<GameObject>();
		levels.add(level03);
		for(int i = 45; i < bottomSide - 20; i += 60)
			level03.add(new BasicEnemy(100, i, 3, 2, ID.BasicEnemy, handler));
		addNewKey(handler, level03);
		addNewDoor(handler, level03);
		
//		//set objects for level04
		LinkedList<GameObject> level04 = new LinkedList<GameObject>();
		levels.add(level04);
		for(int i = 105; i <rightSide - 80; i += 60)
			level04.add(new BasicEnemy(i, 45, 0, 4, ID.BasicEnemy, handler));
		for(int i = 75; i < rightSide - 40; i += 60)
			level04.add(new BasicEnemy(i, bottomSide - 40, 0, -4, ID.BasicEnemy, handler));
		addNewKey(handler, level04);
		addNewDoor(handler, level04);

		//set objects for level05
		LinkedList<GameObject> level05= new LinkedList<GameObject>();
		levels.add(level05);
		for(int i = 45; i < 285; i += 60)
			level05.add(new BasicEnemy(i, 100 - Window.getHeightOffset(), 0, 5, ID.BasicEnemy, handler));
		for (int i = 280; i < 445; i += 60) 
			level05.add(new BasicEnemy(i, bottomSide/2, 0, 3, ID.BasicEnemy, handler));
		for(int i = 465; i < rightSide - 20; i += 60)
			level05.add(new BasicEnemy(i, 100 - Window.getHeightOffset(), 0, 5, ID.BasicEnemy, handler));

		level05.add(new Wall(rightSide/2 - 100, bottomSide/2 + 85, ID.Wall, 175, 15));
		level05.add(new Wall(rightSide/2 - 100, bottomSide/2 - 90, ID.Wall, 175, 15));		
		addNewKey(handler, level05);
		addNewDoor(handler, level05);

		//set objects for level06
		LinkedList<GameObject> level06 = new LinkedList<GameObject>();
		levels.add(level06);
		for(int i = 48; i < bottomSide; i += 60)
			level06.add(new BasicEnemy(rightSide - 100, i, 4, 0, ID.BasicEnemy, handler));
			level06.add(new BasicEnemy(rightSide - 100, bottomSide / 2 + 3, 4, 0, ID.BasicEnemy, handler));
		for(int i = 60; i < 260; i += 20)
			level06.add(new BasicEnemy(i, 45, 0, 6, ID.BasicEnemy, handler));
			level06.add(new Wall(rightSide/2 - 100, bottomSide/2 - 82, ID.Wall, 15, 175));
			level06.add(new Wall(rightSide/2 - 100, bottomSide/2 + 93, ID.Wall, 175, 15));
			level06.add(new Wall(rightSide/2 - 100, bottomSide/2 - 82, ID.Wall, 175, 15));
		for(int i = 285; i < rightSide - 40; i += 120)
			level06.add(new BasicEnemy(i, 45, 0, 4, ID.BasicEnemy, handler));
		level06.add(new BasicEnemy(285, bottomSide - 100, 0, 4, ID.BasicEnemy, handler));
		level06.add(new BasicEnemy(405, bottomSide - 100, 0, 4, ID.BasicEnemy, handler));	
		addNewKey(handler, level06);
		addNewDoor(handler, level06);
		
		//player wins
	}
	
	/**
	 * nextLevel
	 * @param handler, a Handler
	 * goes to the next level in levels
	 */
	public static void nextLevel(Handler handler)
	{
		System.out.println("Next Level!");
		levelNum++;
		if(levelNum >= levels.size()) //if the player reaches a level number higher than the max implemented level, they win
		{
			System.out.println("You win!");
			levelNum = 100; //level 100 represents the win screen level
			handler.removeObjects();
			return;
		}
		//if there are still more levels to by played, do this
		HUD.setKEYS(0); //reset the number of keys
		HUD.setHEALTH(100); //reset health
		handler.removeObjects(); //remove all objects from the handler
		LinkedList<GameObject> nextLevel = levels.get(levelNum); //since levelNum was incremented above, this represents the next level
		for(int i = 0; i < nextLevel.size(); i++) //for each GameObject, add it to the handler
		{
			GameObject tempObject = nextLevel.get(i);
			handler.addObject(tempObject);
		}	
	}
	
	/**
	 * restartLevel
	 * @param handler, a Handler
	 * resets the objects in the current level
	 */
	public static void restartLevel(Handler handler)
	{
		System.out.println("You Died! Restarting Level!");
		HUD.setKEYS(0); //reset keys
		HUD.setHEALTH(100); //reset health
		handler.removeObjects(); //remove all objects
		setLevels(handler); //reset each level's game object into its original state
		LinkedList<GameObject> currentLevel = levels.get(levelNum); //since levelNum was not incremented, this represents the same level
		for(int i = 0; i < currentLevel.size(); i++) //add each GameObject to the handler
		{
			GameObject tempObject = currentLevel.get(i);
			handler.addObject(tempObject);
		}
	}
	
	/**
	 * addNewKey
	 * @param handler, a Handler
	 * @param level, a LinkedList<GameObject>
	 * creates a key in the center of the level
	 */
	public static void addNewKey(Handler handler, LinkedList<GameObject> level)
	{
		level.add(new Key(rightSide/2 - 15, bottomSide / 2,ID.Key, handler));
	}
	
	/**
	 * addNewDoor
	 * @param handler, a Handler
	 * @param level, a LinkedList<GameObject>
	 * creates a door in the middle-right of the level
	 */
	public static void addNewDoor(Handler handler, LinkedList<GameObject> level)
	{
		level.add(new Door(rightSide - 40, bottomSide / 2 - 15,ID.Door, handler));
	}
	
	/**
	 * getLevelNum
	 * @return levelNum, an int
	 * returns the current level number
	 */
	public static int getLevelNum()
	{
		return levelNum;
	}
}
