
public class Weapon {

	private String name;
	private int damage;
	private int ammo;
	
	public Weapon(String name, int damage, int ammo) {
		this.name = name;
		this.damage = damage;
		this.ammo = ammo;
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
