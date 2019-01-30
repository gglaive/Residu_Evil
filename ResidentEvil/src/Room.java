import java.util.*;

public class Room 
{
    private String description;
    public int number;
    public ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, int random) 
    {
        this.description = description;
        exits = new	HashMap<String, Room>();
        if(random>0)
        	this.description = this.description.concat(", " + random + " yellow jackets");
        this.number = random;
    }
    
    public Room getExit(String direction){
    	
    	return exits.get(direction);
    }
    
    public String getExitString(){
    	String exit = "Exits: ";
    	Set<String> keys = exits.keySet();
    	for (String key : keys){
    		exit += " " + key;
    	}
    	return exit;
    }

    public void setExit(String direction, Room neighbor) {
    	
    	exits.put(direction, neighbor);
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
   
    public String getLongDescription(){
    	return "You are " + description + ".\n" + getExitString();
    }

}
