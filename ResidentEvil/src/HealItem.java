/**
 * @author Geoffrey Glaive
 * @version 03.2019
 * Subclass of "Item" that interacts with the "health" attribute of the "Character" class
 */

public class HealItem extends Item {
	
	int healing;

	public HealItem(String name, String description, int number, int inventory_space, int healing){
		super(name, description, number, inventory_space);
		this.healing = healing;
	}

	public int getHealing() {
		return healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}
	
}
