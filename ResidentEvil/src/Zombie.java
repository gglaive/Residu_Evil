
public class Zombie 
{
    public int health;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Zombie(int heath) 
    {
        this.health = health;
    }
    
    public int getHeath()
    {
        return health;
    }

}