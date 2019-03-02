public class HealItem extends Item {
	
	int healing;

	public HealItem(String name, String description, int number, int healing){
		super(name, description, number);
		this.healing = healing;
	}

	public int getHealing() {
		return healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}
	
}
