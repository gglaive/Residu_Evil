public class Zombie {
    private int health;

    public Zombie(int health){
        this.health = health;
    }
    
    public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void attack(Player player){
		player.setHealth(player.getHealth() -1);
	}

}
