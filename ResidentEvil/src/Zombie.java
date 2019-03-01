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


	public String attack(Player player){
		Random rand = new Random();
		int random = rand.nextInt(10);
		StringBuilder string = new StringBuilder("");
		if((this.aggro >= 100) || (random == 9)){
			player.setHealth(player.getHealth() -1);
			string.append("The zombie attacks ! \n");
    		this.aggro = 0;
		}
		
    	else {
    		switch(random) {
    			
    		case 1:
    			string.append("The zombie is just looking upward \n");
    			this.aggro += 10;
    			break;
    		
    		case 2:
    			string.append("The zombie stands still \n");
    			this.aggro += 15;
    			break;
    			
    		case 3:
    			string.append("The zombie is looking at you \n");
    			this.aggro += 25;
    			break;
    			
    		case 4:
    			string.append("The zombie is moving slowly \n");
    			this.aggro += 30;
    			break;
    			
    		case 5:
    			string.append("The zombie screams loudly \n");
    			this.aggro += 40;
    			break;
    			
    		case 6:
    			string.append("The zombie raises its arm and tries to reach you \n");
    			this.aggro += 50;
    			break;
    			
    		case 7:
    			string.append("The zombie is heading towards you! \n");
    			this.aggro += 85;
    			break;
    			
    		case 8:
    			string.append("The zombie is running at you! \n");
    			this.aggro += 100;
    			break;

    		default:
    			string.append("The zombie is not paying attention \n");
    			break;		
    		}  	
    		string.append("Aggro: " + this.aggro + "% \n");	
    	}
		return string.toString();
	}

}
