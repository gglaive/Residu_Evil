package pkg_character;
/**
 * @author Geoffrey Glaive
 * @version 02.2019
 * Class that allows the creation of characters in the game, with an attribute "health".
 */

public class Character {

	private int health;
	
	Character(int health){
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
}
