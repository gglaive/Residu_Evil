import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<String, Room> rooms = new HashMap<String, Room>();
    private Player player;
    
    private Item ammo = new Item("ammo", 7);
    private HealItem herb = new HealItem("herb", 1, 1);
    private HealItem spray = new HealItem("spray", 1, 2);
    private KeyItem medal = new KeyItem("medal", 1);
    private Weapon matilda = new Weapon("matilda", 1, 8, 12);

    /**
     * Create the game and initialize its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player(3);
        player.setGun(matilda);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, station_hall, station_1st_floor, west_wing, armory, west_stairs, black_room, west_corridor, squad_office, reserve, library, east_wing, bathroom, police_office, back_garden, east_corridor, chief_office, break_room, roof, backyard_room, undergrounds;
        
        // create the rooms
        // start
        outside = new Room("in the streets of Fock City. You can see the police station from here. Zombies are all around you so you should hurry");
        
        // Police Station rooms
        // Center
        station_hall = new Room("in the main hall of the police station. This place is huge. You remember there's a secret passage under the big statue here, but you need two medaillons first ..");
        station_1st_floor = new Room("above the police station hall");
        
        //West side
        west_wing = new Room("in the west wing. The corridor is tight and only the moonlight brighten a bit this place..");
        armory = new Room("in the armory. There's many weapons exposed here, but you need a keycard first");
        west_stairs = new Room("in the front of the stairs. You can see a small room hide behind those");
        black_room = new Room("in what looks like a black room. It looks safe but there's not much to see here");
        west_corridor = new Room("in the corridor upstairs. A fading light is somehow lightening it");
        squad_office = new Room("in the office of the squad unity. There should be something useful");
        reserve = new Room("in the reserve. It's really dark in here");
        library = new Room("in the library. It's quite big and almost too bright. It's also full of zombies");
        
        //East side
        east_wing = new Room("in the east wing. The path looks long, and the weak moonlight shows you blood along the way");
        bathroom = new Room("in the bathroom. The water is flowing from one of the sink");
        police_office = new Room("in the main police office. The desks are messy");
        back_garden = new Room("in a small garden, which serves as a fire exit. There's an access to upstairs");
        east_corridor = new Room("in the corridor upstairs. It's the cleanest room you've seen yet");
        chief_office = new Room("in the office of the police chief. It's quite small but it has to contain some ammo. Looks like you can unlock to access to the main hall from here");
        break_room = new Room("in the break room. Not much useful things here but it's a safe place");
        roof = new Room("in the roof of the station. It's pouring rain here. There's a small building downstairs");
        backyard_room = new Room("in the backyard room. There might be something useful ..");
        
        //others
        undergrounds = new Room("in the undergrounds of the station. You escaped!");
        
        //Add all the rooms in 'rooms'
        rooms.put("outside", outside);
        rooms.put("station hall", station_hall);
        rooms.put("station 1st floor", station_1st_floor);
        rooms.put("west wing", west_wing);
        rooms.put("armory", armory);
        rooms.put("west stairs", west_stairs);
        rooms.put("black room", black_room);
        rooms.put("west corridor", west_corridor);
        rooms.put("squad office", squad_office);
        rooms.put("reserve", reserve);
        rooms.put("library", library);
        rooms.put("east wing", east_wing);
        rooms.put("bathroom", bathroom);
        rooms.put("police office", police_office);
        rooms.put("back garden", back_garden);
        rooms.put("east corridor", east_corridor);
        rooms.put("chief office", chief_office);
        rooms.put("break room", break_room);
        rooms.put("roof", roof);
        rooms.put("backyard room", backyard_room);
        rooms.put("undergrounds", undergrounds);

        // initialise room exits
        // start
        outside.setExit("north", station_hall);
        
        // Police station
        // Center
        station_hall.setExit("south", outside);
        station_hall.setExit("west", west_wing);
        station_hall.setExit("east", east_wing);
        station_hall.setExit("up", station_1st_floor);
        station_hall.setExit("down", undergrounds);
        
        station_1st_floor.setExit("west", library);
        station_1st_floor.setExit("east", chief_office);
        station_1st_floor.setExit("down", station_hall);
        
        // West side
        west_wing.setExit("east", station_hall);
        west_wing.setExit("west", armory);
        west_wing.setExit("north", west_stairs);
        
        armory.setExit("east", west_wing);
        
        west_stairs.setExit("south", west_wing);
        west_stairs.setExit("east", black_room);
        west_stairs.setExit("up", west_corridor);
        
        black_room.setExit("west", west_stairs);
        
        west_corridor.setExit("down", west_stairs);
        west_corridor.setExit("west", squad_office);
        west_corridor.setExit("east", reserve);
        
        squad_office.setExit("east", west_corridor);
        
        reserve.setExit("west", west_corridor);
        reserve.setExit("south", library);
        
        library.setExit("north", reserve);
        library.setExit("east", station_1st_floor);
        
        // East Side
        
        east_wing.setExit("east", back_garden);
        east_wing.setExit("west", station_hall);
        east_wing.setExit("north", bathroom);
        east_wing.setExit("south", police_office);
        
        bathroom.setExit("south", east_wing);
        
        police_office.setExit("north", east_wing);
        
        back_garden.setExit("west", east_wing);
        back_garden.setExit("up", east_corridor);
    
        east_corridor.setExit("down", back_garden);
        east_corridor.setExit("west", chief_office);
        east_corridor.setExit("east", roof);
        east_corridor.setExit("north", break_room);
        
        break_room.setExit("south", east_corridor);
        
        chief_office.setExit("east", east_corridor);
        chief_office.setExit("west", station_1st_floor);
        
        roof.setExit("west", east_corridor);
        roof.setExit("down", backyard_room);
        
        backyard_room.setExit("up", roof);
        
        //others
        undergrounds.setExit("up", station_hall);

        
        // place objects in rooms
        station_hall.addItem(spray);
        armory.addItem(ammo);
        black_room.addItem(herb);
        squad_office.addItem(ammo);
        chief_office.addItem(ammo);
        police_office.addItem(ammo);
        bathroom.addItem(herb);
        break_room.addItem(herb);
        reserve.addItem(medal);
        backyard_room.addItem(medal);
        
        // place zombies in rooms
        outside.setZombiesInt(15);
        east_wing.setZombiesInt(3);
        west_wing.setZombiesInt(1);
        police_office.setZombiesInt(1);
        east_corridor.setZombiesInt(2);
        west_corridor.setZombiesInt(2);
        backyard_room.setZombiesInt(2);
        library.setZombiesInt(3);
        west_stairs.setZombiesInt(2);
        station_1st_floor.setZombiesInt(2);
        
        // lock directions
        station_hall.setState("down", true);
        library.setState("east", true);
        east_corridor.setState("west", true);
        
        // add an item requirement for unlocking
        station_hall.setItemNeeded("down", medal);
        
        currentRoom = outside;  // start game outside
        medal.setRoom(station_hall); // set the use location for medal to station_hall
        medal.setNeeded(2); // set the number of medals needed to unlock
        medal.SetUseOn("down"); // set the direction the item can be used on
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
            finished = step(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Residu.. Evil (because of copyright)");
        System.out.println("You are Leonn, a rookie police officer in Fock City");
        System.out.println("Due to a virus, the city is now infested with yellow vests");
        System.out.println("It's night-time and zombies are moving fast. You have to find a way out of this city");
        System.out.println("Your only solution now is to take refuge in the police_station");
        System.out.println("Can you make it out alive?");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        look();
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
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look"))
        	look();
        else if (commandWord.equals("pickup"))
        	pickup(command);
        else if (commandWord.equals("shoot"))
        	shoot(command);
        else if (commandWord.equals("use"))
        	use(command);
        else if (commandWord.equals("player"))
        	player();
        else if (commandWord.equals("unlock"))
        	unlock(command);
        else if (commandWord.equals("reload"))
    	   reload();
        else if (commandWord.equals("go"))
        	flee(command);
        
        return wantToQuit;
    }
    
    //happens after a command
    //ends the game if you are dead
    //ends the game if you escaped
    private boolean step(Command command){
    	
    	if(command.isUnknown()) {
            return false;
        }
    	if(command.getCommandWord().equals("reload")
    			|| command.getCommandWord().equals("pickup")
    			|| command.getCommandWord().equals("use")
    			|| command.getCommandWord().equals("unlock")
    			|| command.getCommandWord().equals("shoot")){
    		if(!currentRoom.getZombies().isEmpty()){
            	for(int i=0;i<currentRoom.getZombies().size();i++){
            		System.out.print("Zombie " + (i+1) + ": ");
            		currentRoom.getZombies().get(i).attack(player);		
            	}
            	System.out.println("Your health: " + player.getHealth());
            }
        	if(player.getHealth()<=0){
        		System.out.println("You are dead!");
        		return true;
        	}
    	}
    	if(currentRoom.equals(rooms.get("undergrounds"))) {
    		return true;
    	}
    	return false;
    }
    
    // implementations of user commands:
    
    /**
     * Print out some help information.
     */
    private void printHelp() {
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println();
        System.out.println("go + *direction* = move to next location");
        System.out.println("quit = stop the game");
        System.out.println("shoot + *ZombieNumber* (ex: 2 zombies, 'shoot 1' means shoot on the zombie n°1)");
        System.out.println("look returns the description of the current room");
        System.out.println("pickup + *ItemName* = get in your inventory the named item");
        System.out.println("player shows your stats");
        System.out.println("unlock + *direction* for unlocking a path");
        System.out.println("reload = reload your current weapon");
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
        }
        
        // verifie que le passage n'est pas ferme
        else if(!currentRoom.getStates().isEmpty() && currentRoom.getStates().containsKey(direction)){
        	if(currentRoom.getState(direction)==true) {
        		System.out.println("This access is locked");
        		return;
        	}	
        }
        	
        // verifie que l'acces n'est pas bloque depuis l'autre piece
        else if(!nextRoom.getStates().isEmpty()){
        	String opposite = nextRoom.getOpposite(direction);
            if(nextRoom.getStates().containsKey(opposite)) {      	
            	if(nextRoom.getState(opposite)==true) {
            		System.out.println("This access is locked from the other side");
            		return;
            	}
            }
        }	
        currentRoom = nextRoom;
        look();
    }
    
    /**
     * look est une fonction qui permet au joueur d'avoir une
     * description sur la piece où il se trouve actuellement.
     */
    private void look(){
    	currentRoom.getLongDescription();
    }
    
    //affiche les informations du joueur
    private void player(){
    	System.out.println("Your current health: " + player.getHealth());
    	System.out.println("Ammo in weapon: " + player.getGun().getAmmo());
    	System.out.print("Your items: ");
    	for(int i=0;i<player.getItems().size();i++) {
    		System.out.print(player.getItems().get(i).getName() + " x" + player.getItems().get(i).getNumber() + " ");
    	}
    	System.out.println("");
    }
    
    
    //recharge l'arme
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
    	
        if (currentRoom.getExit(command.getSecondWord()) == null) {
            System.out.println("There is no door!");
            return;
        }
    	
        if(!currentRoom.equals(rooms.get("outside"))) {
        	if(!currentRoom.getZombies().isEmpty()){
        		Random rand = new Random();
            	if(rand.nextInt(10) >= 7) {
            		player.isAttacked();
            		System.out.println("A zombie bite you while you were escaping!");
            	}
            	else {
            		System.out.println("You managed to flee without getting hurt");
            	}
            	System.out.println("Your health: " + player.getHealth());            	
        	}
        }
        goRoom(command);
    }
    
    
    //cible et tire sur un ennemi
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
    	Random rand = new Random();
    	
    	if(rand.nextInt(10) >= 6) {
    		zombie.setAggro(zombie.getAggro() - 50);
    		if(zombie.getAggro() < 0)
    			zombie.setAggro(0);
    		System.out.println("Zombie " + zombie_number + " flinched!");
    	}
    	
    	zombie.setHealth(zombie.getHealth()-1);
    	player.getGun().setAmmo(player.getGun().getAmmo() -1);
    	
    	System.out.println("Ammo left in gun: " + player.getGun().getAmmo());
    	System.out.println("Zombie " + zombie_number + " has " + zombie.getHealth() + " hp left !");
    	
    	if(zombie.getHealth() == 0) {
    		System.out.println("Zombie " + zombie_number + " is dead !");
    		ArrayList<Zombie> zombies = currentRoom.getZombies();
    		zombies.remove(zombie_number -1);
    		currentRoom.setZombies(zombies);
    	}
    	
    }
    
    //utilise un objet
    private void use(Command command) {
    	
    	if(!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }
    	
    	switch(command.getSecondWord()){
    	
    		case "herb":
    			useHeal(herb);
    			break;
    			
    		case "spray":
    			useHeal(spray);
    			break;
    			
    		case "medal":
    			useKey(medal);
    			break;
    		
    		default:
    			System.out.println("What is that?");
    			break;
    	}
    	
    }
    
    
    private void useHeal(HealItem item){
    	
    	if(!player.getItems().contains(item)) {
			System.out.println("You don't have any " + item.getName());
            return;
		}
		
		if(player.getHealth() == player.max_health) {
			System.out.println("You are full health already.");
			System.out.println("Your current health: " + player.getHealth());
            return;
		}
		
		player.setHealth(player.getHealth() + item.getHealing());
		if(player.getHealth()>player.max_health)
			player.setHealth(player.max_health);
		
		player.removeItem(item);
		
		System.out.println("You used a " + item.getName());
		System.out.println("Your current health: " + player.getHealth());
		return;
    }
    
    
    private void useKey(KeyItem item){
    	
    	if(!player.getItems().contains(item)) {
			System.out.println("You don't have any " + item.getName());
            return;
		}
		
		if(!currentRoom.equals(item.getRoom())){
			System.out.println("You can't use this here");
            return;
		}
		
		player.removeItem(item);
		
		System.out.println("You used " + item.getName());
		
		item.setNeeded(item.getNeeded() -1);
		if(item.getNeeded() == 0){
			currentRoom.setState(item.getUseOn(), false);
			currentRoom.setItemNeeded(item.getUseOn(), null);
			System.out.println("You've unlocked the " + item.getUseOn() + " access");
		}
		else {
			System.out.println("You need " + item.getNeeded() + " more");
		}
    }

    
    
    private void pickup(Command command){
    	
    	if(currentRoom.getItems().isEmpty())	
    		System.out.println("There is no item here !");
    	
    	else if(!command.hasSecondWord()) {
    		System.out.println("Pickup what?");
    	}
    	
    	else if(currentRoom.haveItemName(command.getSecondWord())){
    		
    		Item item = Item.findByName(command.getSecondWord(), currentRoom.getItems());
    		System.out.println("You picked up " + item.getName());
    		
    		if(!player.getItems().contains(item)) {
    			player.getItems().add(item);
    		}
    		
        	else {
        		int pos = player.getItems().lastIndexOf(item);
        		player.getItems().get(pos).setNumber(player.getItems().get(pos).getNumber() + item.initial_number);
        	}
    		
    		currentRoom.removeItem(item);
    	}   	
    	
    }
    
    private void unlock(Command command) {
    	
    	if(currentRoom.getStates().isEmpty()) {
        	System.out.println("There's nothing to unlock in this direction");
        }
    	
    	else if(!command.hasSecondWord()) {
            System.out.println("Unlock what?");
        }
    	
    	else if(currentRoom.getState(command.getSecondWord()) == false) {
        	System.out.println("This access is already unlocked");
        }
    	
    	else if(currentRoom.getItemNeeded(command.getSecondWord()) != null) {
    		System.out.println("You need to use an specific item first");
    	}
    	
    	else {
    		currentRoom.setState(command.getSecondWord(), false);
    		System.out.println("You've unlocked the " + command.getSecondWord() + " access");
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
