public class Zombie extends Character{

    public Zombie(int health){
        super(health);
    }
    
	public void attack(Player player){
		player.setHealth(player.getHealth() -1);
	}

}
