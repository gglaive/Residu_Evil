import java.util.*;

/**
 * Room est une classe permettant d'instancier un object "Room" (piece)
 * avec des sorties.
 */

public class Room {
    private String description;
    private String imageName;
    private ArrayList<Zombie> zombies;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private HashMap<String, Boolean> states;
    private HashMap<String, Item> itemsNeeded;

    /**
     * Crée une pièce avec une description.
     * @param description The room's description.
     */
    public Room(String description, String image) {
        this.description = description;
        zombies = new ArrayList<Zombie>();
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        states = new HashMap<String, Boolean>();
        itemsNeeded = new HashMap<String, Item>();
        imageName = image;
    }
    
    public ArrayList<Zombie> getZombies() {
    	return zombies;
    }
    
    public void setZombies(ArrayList<Zombie> zombies) {
    	this.zombies = zombies;
    }
    
    public void setZombiesInt(int nb){
    	zombies.clear();
    	for (int i=0; i<nb; i++) {
        	Zombie zombie = new Zombie(2);   	
        	zombies.add(zombie);
        }
    }
    
    public HashMap<String, Room> getExits() {
    	return exits;
    }
    
    public void setExits(HashMap<String, Room> exits) {
    	this.exits = exits;
    }

    public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public boolean haveItemName(String name) {
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getName().equals(name))
				return true;
		}
		return false;
	}

	public void setDescription(String description) {
    	this.description = description;
    }
	
	public HashMap<String, Boolean> getStates() {
		return states;
	}
	
	public void setStates(HashMap<String, Boolean> states){
		this.states = states;
	}
	
	public boolean getState(String direction) {
		return states.get(direction);
	}
	
	public void setState(String direction, Boolean state){
		this.states.put(direction, state);
	}

    public HashMap<String, Item> getItemsNeeded() {
		return itemsNeeded;
	}

	public void setItemsNeeded(HashMap<String, Item> itemsNeeded) {
		this.itemsNeeded = itemsNeeded;
	}
	
	public Item getItemNeeded(String direction) {
		return itemsNeeded.get(direction);
	}
	
	public void setItemNeeded(String direction, Item item){
		this.itemsNeeded.put(direction, item);
	}

	/**
     * getExit permet de retourner un objet room
     * en fonction d'une direction.
     * @param {String} direction
     * @return {Room} - retourne la sortie dans la direction indiqué
     */
    public Room getExit(String direction){
    	return exits.get(direction);
    }

    /**
     * getExitString permet de retourner en chaine de caractère
     * l'ensemble des sorties de la piece courante.
     * @return {String} - indique l'ensemble des sorties.
     */
    public String getExitString(){
    	StringBuilder exit = new StringBuilder("Exits: ");
    	//exit.append("Exits: ");
    	for (String key : exits.keySet())
    		exit.append(" " + key);
    	return exit.toString();
    }

    /**
     * Permet de fixer une sortie.
     * @method setExit
     * @param {String} direction - clé
     * @param {Room} neighbor - valeur
     */
    public void setExit(String direction, Room neighbor) {
    	exits.put(direction, neighbor);
    }
    
    // return the opposite direction from where the player is going
    public String getOpposite(String direction) {
    	String opposite ="";
    	if(direction.matches("north"))
    		opposite="south";
    	if(direction.matches("south"))
    		opposite="north";
    	if(direction.matches("east"))
    		opposite="west";
    	if(direction.matches("west"))
    		opposite="east";
    	if(direction.matches("up"))
    		opposite="down";
    	if(direction.matches("down"))
    		opposite="up";
    	return opposite;
    }

    /**
     * Permet de récupérer la description courante.
     * @method getDescription
     * @return {String} - description de l'objet courant
     */
    public String getDescription(){
        return description;
    }


	/**
     * Permet de récupérer la description courante,
     * ainsi que la liste des sorties. 
     * @method getLongDescription
     * @return {String} - description de l'objet courant et les sorties.
     */   
    public String getLongDescription(){
    	StringBuilder longDescription = new StringBuilder("You are " + getDescription() + "\n");
    	if(zombies.size() > 0) {
    		longDescription.append(zombies.size() + " yellow vests" + "\n");
    	}
    	if(!items.isEmpty()) {
    		longDescription.append("Some items are here !" + "\n");
    		for(int i=0;i<items.size();i++) {
    			longDescription.append(" *" + items.get(i).getName() +"* \n");
    		}
    	}
    	longDescription.append(getExitString());
    	return longDescription.toString();
    }
    
    public String getImageName()
	{
		return imageName;
	}
}
