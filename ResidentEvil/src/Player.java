import java.util.ArrayList;

public class Player {
    private int health;
    
    private boolean isAlive;
    private ArrayList<String> items = new ArrayList<String>();
    private Weapon gun;

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

    public ArrayList<String> getItems() {
	return items;
    }
    
    public void setItems(ArrayList<String> items) {
	this.items = items;
    }
	
    public Weapon getGun() {
	return gun;
    }
    
    public void setGun(Weapon gun) {
	this.gun = gun;
    }

    public Player(int health) 
    {
	this.health = health;
        this.isAlive = true;
        this.gun = new Weapon("gun", 1, 15);
    }
    
    public int getHeath() {
        return health;
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
