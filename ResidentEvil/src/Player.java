import java.util.ArrayList;

/**
 * @author Geoffrey Glaive
 * @version 03.2019
 * Subclass of "Character" that focus on the creation of a player with important attributes such as his inventory or if he is alive.
 */

public class Player extends Character{
	
    private int max_health;
    private int max_inventory;
    private boolean isAlive;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Weapon gun;
    private boolean hasWeapon;
    private Room currentRoom;
    
    public Player(int health, int max_health, int max_inventory, Room currentRoom) {
    	super(health);
        this.isAlive = true;
        this.setHasWeapon(false);
        this.max_health = max_health;
        this.max_inventory = max_inventory;
        this.currentRoom = currentRoom;
    }
    
    public int getMax_health() {
		return max_health;
	}

	public void setMax_health(int max_health) {
		this.max_health = max_health;
	}

	public int getMax_inventory() {
		return max_inventory;
	}

	public void setMax_inventory(int max_inventory) {
		this.max_inventory = max_inventory;
	}

	public boolean isAlive() {
	return isAlive;
    }
    
    public void setAlive(boolean isAlive) {
	this.isAlive = isAlive;
    }
	
    public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public Weapon getGun() {
		return gun;
    }
    
    public void setGun(Weapon gun) {
    	this.gun = gun;
    	this.setHasWeapon(true);
    }
    
    public void isAttacked() {
    	setHealth(getHealth() -1);
    	if (getHealth()==0)
    		isAlive = false;
    }
    
    public void isHealed() {
    	setHealth(getHealth() +1);
    }
    
    public void removeItem(Item item){
    	int pos = items.lastIndexOf(item);
		items.get(pos).setNumber(items.get(pos).getNumber() - 1);
		
		if(items.get(pos).getNumber() == 0)
			items.remove(pos);
    }

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public boolean hasWeapon() {
		return hasWeapon;
	}
	
	public void setHasWeapon(boolean hasWeapon) {
		this.hasWeapon = hasWeapon;
	}
}
