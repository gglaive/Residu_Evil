/**
 * @author Geoffrey Glaive
 * @version 02.2019
 * Subclass of "Item" that interacts with the "states" attribute of the "Room" class
 */

public class Weapon extends Item{

	private int damage;
	private int ammo;
	public int max_ammo;
	
	public Weapon(String name, String description, int number, int inventory_space, int damage, int ammo, int max_ammo) {
		super(name, description, number, inventory_space);
		this.damage = damage;
		this.ammo = ammo;
		this.max_ammo = max_ammo;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
}
