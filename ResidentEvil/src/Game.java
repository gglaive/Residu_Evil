/**
 * @author Geoffrey Glaive
 * @version 03.2019
 * Class that instantiate a game.
 */

public class Game
{
	private UserInterface gui;
	private GameEngine engine;

    /**
     * Create the game and initialize its internal map.
     */
    public Game() 
    {
		engine = new GameEngine();
		gui = new UserInterface(engine);
		engine.setGUI(gui);
    }
}