import java.util.ArrayList;

public class Player {
	
    private int health;
    public int max_health = 3;
    private boolean isAlive;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Weapon gun;
    
    public Player(int health) {
    	this.health = health;
        this.isAlive = true;
        this.gun = new Weapon("gun", 1, 12, 15);
    }

    public int getHealth() {
	return health;
    }
    
    public void setHealth(int health) {
	this.health = health;
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
    }
    
    public void isAttacked() {
    	health = health -1;
    	if (health==0)
	    isAlive = false;
    }
    
    public void isHealed() {
    	health = health + 1;
    }
}
