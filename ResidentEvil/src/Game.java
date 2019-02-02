import java.util.ArrayList;
import java.util.Random;

/**
 * Game est la classe principale du jeu, elle permet de creer
 * l'environnement du jeu, permet de le lancer, et d'executer
 * les commandes. Elle fait communiquer l'ensemble des classes
 * entre elles.
 */
public class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Item ammo = new Item("ammo", 10);
    private Item herb = new Item("herb", 1);
    private Item medal = new Item("medal", 1);

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player(2);    
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, station_hall, station_1st_floor, undergrounds, sewers, lab, office;
        Random rand = new Random();
        

        // create the rooms
        outside = new Room("in the streets of Fock City. You can see the police station from here", rand.nextInt(3), herb);
        station_hall = new Room("in the main hall of the police station", rand.nextInt(3), herb);
        station_1st_floor = new Room("in the first floor of the police station", rand.nextInt(3), null);
        undergrounds = new Room("in the undergrounds of the station", rand.nextInt(3), null);
        sewers = new Room("in the sewers", rand.nextInt(3), null);
        lab = new Room("in a computing lab", rand.nextInt(3), ammo);
        office = new Room("in the computing admin office", rand.nextInt(3), ammo);

        // initialise room exits
        outside.setExit("east", station_hall);
        outside.setExit("south", lab);
        outside.setExit("west", sewers);
        
        station_hall.setExit("west", outside);
        station_hall.setExit("up", station_1st_floor);
        station_hall.setExit("down", undergrounds);
        
        station_1st_floor.setExit("down", station_hall);
        
        undergrounds.setExit("up", station_hall);
        
        sewers.setExit("east", outside);
        
        lab.setExit("north", outside);
        lab.setExit("east", office);
        
        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    //private void createZombies(){}

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Residu.. Evil (because of copyright)");
        System.out.println("You are Leon, a rookie police officer in Fock City");
        System.out.println("Due to a virus, the city is now infested with yellow vests");
        System.out.println("Can you make it out alive?");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("flee"))
        	flee(command);
        else if (commandWord.equals("look"))
        	look();
        else if (commandWord.equals("pickup"))
        	pickup();
        else if (commandWord.equals("shoot"))
        	shoot(command);
        else if (commandWord.equals("use"))
        	use(command);
        else if (commandWord.equals("inventory"))
        	inventory();
       else if (commandWord.equals("unlock"))
        	unlock(command);
       else if (commandWord.equals("reload"))
    	   reload();
        
        return wantToQuit;
    }
    
    // implementations of user commands:
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the streets.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
	
        String direction = command.getSecondWord();
	
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
	
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }
    
    /**
     * Permet d'afficher à l'utilisateur dans 
     * quelle pièce il est actuellement.
     */
    private void printLocationInfo(){
    	currentRoom.getLongDescription();
    }
    
    
    private void inventory(){
    	System.out.print("Your items: ");
    	for(int i=0;i<player.getItems().size();i++) {
    		System.out.print(player.getItems().get(i).getName() + " x" + player.getItems().get(i).getNumber() + " ");
    	}
    	System.out.println("");
    	return;
    }
    
    
    private void reload() {
    	if(!player.getItems().contains(ammo)) {
    		System.out.println("You don't have any ammo in your inventory dude");
            return;
    	}
    	
    	if(player.getGun().getAmmo() == player.getGun().max_ammo) {
    		System.out.println("Your weapon is fully charged already!");
            return;
    	}
    	
    	int pos = player.getItems().lastIndexOf(ammo);
    	int ammo_needed = player.getGun().max_ammo - player.getGun().getAmmo();
    	int ammo_available = player.getItems().get(pos).getNumber();
    	
    	System.out.println("You reload your weapon");
    	
    	if(ammo_needed > ammo_available) {
    		player.getGun().setAmmo(player.getGun().getAmmo() + ammo_available);
    	}
    	else {
    		player.getGun().setAmmo(player.getGun().getAmmo() + ammo_needed);
    		player.getItems().get(pos).setNumber(player.getItems().get(pos).getNumber() - ammo_needed);
    		System.out.println("Weapon fully reloaded. Ammo in weapon: " + player.getGun().getAmmo());
    	}
    	
    	if(player.getItems().get(pos).getNumber() == 0) {
    		player.getItems().remove(pos);
    	}
    	
    	return;
    }
    

    /**
     * flee est une commande qui permet au joueur de fuir
     */
    private void flee (Command command){
    	
    	Random rand = new Random();
    	if(rand.nextInt(10) >= 5) {
    		player.isAttacked();
    	}
    	System.out.println("Your health: " + player.getHealth());
    	goRoom(command);
    }
    
    
    private void shoot (Command command){
    	if(player.getGun().getAmmo() == 0) {
    		System.out.println("No more ammo!");
    		System.out.println("The zombies are coming closer! RUN!!");
    		return;
    	}
    	
    	if(!command.hasSecondWord()) {
            System.out.println("Shoot who?");
            return;
        }
    	
    	if(currentRoom.getZombies().isEmpty()) {
    		System.out.println("No zombies here!");
    		return;
    	}
    	
    	int zombie_number =  Integer.parseInt(command.getSecondWord());
    	if(zombie_number > currentRoom.getZombies().size()) {
    		System.out.println("Not that much zombies here! What are you aiming at?");
    		return;
    	}
    	Zombie zombie = currentRoom.getZombies().get(zombie_number -1);
    	
    	zombie.setHealth(zombie.getHealth()-1);
    	player.getGun().setAmmo(player.getGun().getAmmo() -1);
    	
    	System.out.println("Ammo left in gun: " + player.getGun().getAmmo());
    	System.out.println("Zombie " + zombie_number + " has " + zombie.getHealth() + " left !");
    	
    	if(zombie.getHealth() == 0) {
    		System.out.println("Zombie " + zombie_number + " is dead !");
    		ArrayList<Zombie> zombies = currentRoom.getZombies();
    		zombies.remove(zombie_number -1);
    		currentRoom.setZombies(zombies);
    	}
    	
    }
    
    private void use(Command command) {
    	
    	if(!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }
    	
    	if(command.getSecondWord().equals("herb")) {
    		if(!player.getItems().contains(herb)) {
    			System.out.println("You don't have any herb!");
                return;
    		}
    		
    		if(player.getHealth() == player.max_health) {
    			System.out.println("You are full health already.");
    			System.out.println("Your current health: " + player.getHealth());
                return;
    		}
    		
    		player.setHealth(player.getHealth() +1);
    		
    		int pos = player.getItems().lastIndexOf(herb);
    		player.getItems().get(pos).setNumber(player.getItems().get(pos).getNumber() - 1);
    		if(player.getItems().get(pos).getNumber() == 0) {
    			player.getItems().remove(pos);
    		}
    		
    		System.out.println("You used a herb!");
    		System.out.println("Your current health: " + player.getHealth());
    		return;
    	}

    	System.out.println("What is that?");
    	return;
    }

    /**
     * look est une fonction qui permet au joueur d'avoir une
     * description sur la piece où il se trouve actuellement.
     */
    private void look(){
    	currentRoom.getLongDescription();
    }
    
    private void pickup(){
    	
    	if(currentRoom.getItem() == null)
    		System.out.println("There is no item here !");
    	
    	else {
    		
    		System.out.println("You picked up " + currentRoom.getItem().getName());
    		
    		if(!player.getItems().contains(currentRoom.getItem())) {
    			player.getItems().add(currentRoom.getItem());
    		}
    		
        	else {
        		int pos = player.getItems().lastIndexOf(currentRoom.getItem());
        		player.getItems().get(pos).setNumber(player.getItems().get(pos).getNumber() + currentRoom.getItem().initial_number);
        	}
    		
    		currentRoom.setItem(null);
    	}   	
    	
    }
    
    private void unlock(Command command) {
    	
    	if(!command.hasSecondWord()) {
            System.out.println("Unlock what?");
            return;
        }
    	
    	
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
