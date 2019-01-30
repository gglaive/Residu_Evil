import java.util.*;

public class Room 
{
    public String description;
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public int number;
    public ArrayList<Zombie> zombies = new ArrayList<Zombie>();

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, int random) 
    {
        this.description = description;
        if(random>0)
        	this.description = this.description.concat(", " + random + " yellow jackets");
        this.number = random;
    }

    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
    }

    public void setZombie(){
    	for(int i=0; i<number; i++){
    		Zombie zombie = new Zombie((int)Math.random() * (20));
    		zombies.add(zombie);
    	}
    }

    public String getDescription()
    {
        return description;
    }

}
