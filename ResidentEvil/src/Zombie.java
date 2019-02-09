import java.util.Random;

public class Zombie extends Character{

	int aggro;
	
    public Zombie(int health){
        super(health);
        this.aggro = 0;
    }
    
	public int getAggro() {
		return aggro;
	}


	public void setAggro(int aggro) {
		this.aggro = aggro;
	}


	public void attack(Player player){
		Random rand = new Random();
		int random = rand.nextInt(10);
		if((this.aggro >= 100) || (random == 9)){
			player.setHealth(player.getHealth() -1);
    		System.out.println("The zombie attacks !");
    		this.aggro = 0;
		}
		
    	else {
    		switch(random) {
    			
    		case 1:
    			System.out.println("The zombie is just looking upward");
    			this.aggro += 10;
    			break;
    		
    		case 2:
    			System.out.println("The zombie stands still");
    			this.aggro += 15;
    			break;
    			
    		case 3:
    			System.out.println("The zombie is looking at you");
    			this.aggro += 25;
    			break;
    			
    		case 4:
    			System.out.println("The zombie is moving slowly");
    			this.aggro += 30;
    			break;
    			
    		case 5:
    			System.out.println("The zombie screams loudly");
    			this.aggro += 40;
    			break;
    			
    		case 6:
    			System.out.println("The zombie raises its arm and tries to reach you");
    			this.aggro += 50;
    			break;
    			
    		case 7:
    			System.out.println("The zombie is heading towards you!");
    			this.aggro += 85;
    			break;
    			
    		case 8:
    			System.out.println("The zombie is running at you!");
    			this.aggro += 100;
    			break;

    		default:
    			System.out.println("The zombie is not paying attention");
    			break;		
    		}  	
    		System.out.println("Aggro: " + this.aggro + "%");
    	}
	}

}
