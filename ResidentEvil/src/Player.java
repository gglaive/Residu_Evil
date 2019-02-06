import java.util.ArrayList;

public class Player extends Character{
	
    public int max_health = 3;
    private boolean isAlive;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Weapon gun;
    
    public Player(int health) {
    	super(health);
        this.isAlive = true;
        this.gun = new Weapon("gun", 1, 12, 15);
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
    	setHealth(getHealth() -1);
    	if (getHealth()==0)
    		isAlive = false;
    }
    
    public void isHealed() {
    	setHealth(getHealth() +1);
    }
}
