import java.util.*;

/**
 * Room est une classe permettant d'instancier un object "Room" (piece)
 * avec des sorties.
 */

public class Room {
    private String description;
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    private HashMap<String, Room> exits;
    private Item item;

    /**
     * Crée une pièce avec une description.
     * @param description The room's description.
     */
    public Room(String description, int random, Item item) {
        this.description = description;
        exits = new HashMap<String, Room>();
        if(random>0) {
            for (int i=0; i<random; i++) {
            	Zombie zombie = new Zombie(2);
            	zombies.add(zombie);
            }
        }
        this.item = item;
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

    public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setDescription(String description) {
    	this.description = description;
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
    	String exit = "Exits: ";
    	Set<String> keys = exits.keySet();
    	for (String key : keys){
    		exit += " " + key;
    	}
    	return exit;
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
    	if(item != null) {
    		System.out.println("There is an item here ! *" + getItem().getName() + "*");
    	}
    	System.out.println(getExitString());
    	return;
    }
}
