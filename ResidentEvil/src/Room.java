import java.util.*;

/**
 * Room est une classe permettant d'instancier un object "Room" (piece)
 * avec des sorties.
 */

public class Room {
    private String description;
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    private HashMap<String, Room> exits = new HashMap<String, Room>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private HashMap<String, Boolean> states = new HashMap<String, Boolean>();
    private HashMap<String, Item> itemsNeeded = new HashMap<String, Item>();

    /**
     * Crée une pièce avec une description.
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
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
    	StringBuilder exit = new StringBuilder();
    	exit.append("Exits: ");
    	Set<String> keys = exits.keySet();
    	for (String key : keys){
    		exit.append(" " + key);
    	}
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
    public void getLongDescription(){
    	System.out.println("You are " + getDescription());
    	if(zombies.size() > 0) {
    		System.out.println(zombies.size() + " yellow jackets");
    	}
    	if(!items.isEmpty()) {
    		System.out.println("Some items are here !");
    		for(int i=0;i<items.size();i++) {
    			System.out.println(" *" + items.get(i).getName() +"*");
    		}
    	}
    	System.out.println(getExitString());
    	return;
    }
}
