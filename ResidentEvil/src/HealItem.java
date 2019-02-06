
public class HealItem extends Item {
	
	int healing;

	HealItem(String name, int number, int healing){
		super(name, number);
		this.healing = healing;
	}

	public int getHealing() {
		return healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}
	
	
}
