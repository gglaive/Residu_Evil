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

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player(3);
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, station_hall, station_1st_floor, undergrounds, sewers, lab, office;
        Random rand = new Random();

        // create the rooms
        outside = new Room("in the streets of Fock City. You can see the police station from here", rand.nextInt(2), "ammo");
        station_hall = new Room("in the main hall of the police station", rand.nextInt(2), null);
        station_1st_floor = new Room("in the first floor of the police station", rand.nextInt(2), null);
        undergrounds = new Room("in the undergrounds of the station", rand.nextInt(2), null);
        sewers = new Room("in the sewers", rand.nextInt(2), null);
        lab = new Room("in a computing lab", rand.nextInt(2), null);
        office = new Room("in the computing admin office", rand.nextInt(2), null);

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
        System.out.println("Résidu.. Evil (because of copyright)");
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
    	System.out.println(currentRoom.getLongDescription());
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

    /**
     * look est une fonction qui permet au joueur d'avoir une
     * description sur la piece où il se trouve actuellement.
     */
    private void look(){
    	System.out.println(currentRoom.getLongDescription());
    }
    
    private void pickup(){
    	if(currentRoom.getItem() == null)
    		System.out.println("There is no item here !");
    	else if(currentRoom.getItem() == "ammo"){
    		player.getGun().setAmmo(player.getGun().getAmmo() + 15);
    		System.out.println("You picked up " + currentRoom.getItem());
    		currentRoom.setItem(null);
    		System.out.println("You have " + player.getGun().getAmmo() + " ammo");
    	}
    	else{
    		player.getItems().add(currentRoom.getItem());
        	System.out.println("You picked up " + currentRoom.getItem());
        	currentRoom.setItem(null);
        	System.out.println("Your items: " + player.getItems());
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
