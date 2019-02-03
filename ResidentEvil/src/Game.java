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
        Room outside, station_hall, station_1st_floor, west_wing, armory, west_stairs, black_room, west_corridor, squad_office, reserve, library, east_wing, bathroom, police_office, back_garden, east_corridor, chief_office, break_room, roof, backyard_room, undergrounds;
        Random rand = new Random();
        

        // create the rooms
        // start
        outside = new Room("in the streets of Fock City. You can see the police station from here. Zombies are all around you so you should hurry", rand.nextInt(10), herb);
        
        // Police Station rooms
        // Center
        station_hall = new Room("in the main hall of the police station. This place is huge. You remember there's a secret passage under the big statue here, but you need two medaillons first ..", 0, herb);
        station_1st_floor = new Room("above the police station hall", rand.nextInt(3), null);
        
        //West side
        west_wing = new Room("in the west wing. The corridor is tight and only the moonlight brighten a bit this place..", rand.nextInt(3), null);
        armory = new Room("in the armory. There's many weapons exposed here, but you need a keycard first", rand.nextInt(3), ammo);
        west_stairs = new Room("in the front of the stairs. You can see a small room hide behind those", rand.nextInt(3), null);
        black_room = new Room("in what looks like a black room. It looks safe but there's not much to see here", 0, herb);
        west_corridor = new Room("in the corridor upstairs. A fading light is somehow lightening it", rand.nextInt(3), null);
        squad_office = new Room("in the office of the squad unity. There should be something useful", 0, ammo);
        reserve = new Room("in the reserve. It's really dark in here", rand.nextInt(3), null);
        library = new Room("in the library. It's quite big and almost too bright, but you see one of the medals thanks to that!", rand.nextInt(3), medal);
        
        //East side
        east_wing = new Room("in the east wing. The path looks long, and the weak moonlight shows you blood along the way", rand.nextInt(3), null);
        bathroom = new Room("in the bathroom. The water is flowing from one of the sink", rand.nextInt(3), herb);
        police_office = new Room("in the main police office. The desks are messy", rand.nextInt(3), ammo);
        back_garden = new Room("in a small garden, which serves as a fire exit. There's an access to upstairs", 0, null);
        east_corridor = new Room("in the corridor upstairs. It's the cleanest room you've seen yet", rand.nextInt(3), null);
        chief_office = new Room("in the office of the police chief. It's quite small but it has to contain some ammo. Looks like you can unlock to access to the main hall from here", 0, ammo);
        break_room = new Room("in the break room. Not much useful things here but it's a safe place", 0, herb);
        roof = new Room("in the roof of the station. It's pouring rain here. There's a small building downstairs", rand.nextInt(3), null);
        backyard_room = new Room("in the backyard room. There might be something useful ..", rand.nextInt(3), medal);
        
        //others
        undergrounds = new Room("in the undergrounds of the station", rand.nextInt(3), null);

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
        
        chief_office.setExit("east", west_corridor);
        chief_office.setExit("west", station_1st_floor);
        
        roof.setExit("west", east_corridor);
        roof.setExit("down", backyard_room);
        
        backyard_room.setExit("up", roof);
        
        //others
        undergrounds.setExit("up", station_hall);

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
        System.out.println("You are Leonn, a rookie police officer in Fock City");
        System.out.println("Due to a virus, the city is now infested with yellow vests");
        System.out.println("It's night-time and zombies are moving fast. You have to find a way out of this city");
        System.out.println("Your only solution now is to take refuge in the police_station");
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
    	System.out.println("Zombie " + zombie_number + " has " + zombie.getHealth() + " hp left !");
    	
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
