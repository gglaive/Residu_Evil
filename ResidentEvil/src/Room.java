import java.util.*;

public class Room 
{
    private String description;
    private int number;
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    private HashMap<String, Room> exits;
    private String item;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, int random, String item) 
    {
        this.description = description;
        exits = new	HashMap<String, Room>();
        if(random>0)
        	this.description = this.description.concat(", " + random + " yellow jackets");
        this.number = random;
        this.item = item;
    }
    
    public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(ArrayList<Zombie> zombies) {
		this.zombies = zombies;
	}

	public HashMap<String, Room> getExits() {
		return exits;
	}

	public void setExits(HashMap<String, Room> exits) {
		this.exits = exits;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setDescription(String description) {
		this.description = description;
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
