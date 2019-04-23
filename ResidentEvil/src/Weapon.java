/**
 * @author Geoffrey Glaive
 * @version 02.2019
 * Subclass of "Item" that interacts with the "states" attribute of the "Room" class
 */

public class Weapon {

	private String name;
	private int damage;
	private int ammo;
	public int max_ammo;
	
	public Weapon(String name, int damage, int ammo, int max_ammo) {
		this.name = name;
		this.damage = damage;
		this.ammo = ammo;
		this.max_ammo = max_ammo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
