
public class Player
{
    public int health;
    public boolean isAlive;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Player(int health) 
    {
        this.health = health;
        this.isAlive = true;
    }
    
    public int getHeath()
    {
        return health;
    }
    
    public void isAttacked(){
    	health = health -1;
    	if (health==0)
    		isAlive = false;
    }
    
    public void isHealed(){
    	health = health +1;
    }

}
