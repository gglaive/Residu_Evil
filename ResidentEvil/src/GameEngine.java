import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

/**
 * Game est la classe principale du jeu, elle permet de creer
 * l'environnement du jeu, permet de le lancer, et d'executer
 * les commandes. Elle fait communiquer l'ensemble des classes
 * entre elles.
 */
public class GameEngine {
    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;
    
    private HashMap<String, Room> rooms = new HashMap<String, Room>();
    private Player player;
    
    private Item ammo = new Item("ammo", "Munitions for a simple gun", 7);
    private HealItem herb = new HealItem("herb", "An healing item that restore 1 health when used", 1, 1);
    private HealItem spray = new HealItem("spray", "An healing item that restore all health when used", 1, 2);
    private KeyItem medal = new KeyItem("medal", "A medal that should be used somewhere ..", 1);
    private Weapon matilda = new Weapon("Matilda", 1, 8, 12);

    private Stack<Room> roadHistory = new Stack<Room>();
    
    /**
     * Create the game and initialize its internal map.
     */
    public GameEngine() {
        createRooms();
        parser = new Parser();
        player = new Player(3);
        player.setGun(matilda);
    }

    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();    
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, station_hall, station_1st_floor, west_wing, locker_room, west_stairs, black_room, west_corridor, squad_office, reserve, library, east_wing, bathroom, police_office, back_garden, east_corridor, waiting_room, break_room, roof, backyard_room, undergrounds;
        
        // create the rooms
        // start
        outside = new Room("in the streets of Fock City. You can see the police station from here. Yellow vests are all around you so you should hurry", "img/outside.jpg");
        
        // Police Station rooms
        // Center
        station_hall = new Room("in the main hall of the police station. This place is huge. You remember there's a secret passage under the big statue here, but you need two medaillons first ..", "img/station_hall.jpg");
        station_1st_floor = new Room("above the police station hall", "img/station_1st_floor.jpg");
        
        //West side
        west_wing = new Room("in the west wing. The corridor is tight and only the moonlight brighten a bit this place..", "img/west_wing.jpg");
        locker_room = new Room("in the locker_room. There may be useful leftovers to take here", "img/locker_room.jpg");
        west_stairs = new Room("in the front of the stairs. You can see a small room hide behind those", "img/west_stairs.jpg");
        black_room = new Room("in what looks like a black room. It looks safe but there's not much to see here", "img/black_room.jpg");
        west_corridor = new Room("in the corridor upstairs. A fading light is somehow lightening it", "img/west_corridor.jpg");
        squad_office = new Room("in the office of the squad unity. There should be something useful", "img/squad_office.jpg");
        reserve = new Room("in the reserve. It's really dark in here", "img/reserve.jpg");
        library = new Room("in the library. It's quite big and almost too bright. It's also full of yellow vests", "img/library.jpg");
        
        //East side
        east_wing = new Room("in the east wing. The path looks long, and the weak moonlight shows you blood along the way", "img/east_wing.jpg");
        bathroom = new Room("in the bathroom. The water is flowing from one of the sink", "img/bathroom.jpg");
        police_office = new Room("in the main police office. The desks are messy", "img/police_office.jpg");
        back_garden = new Room("in a small garden, which serves as a fire exit. There's an access to upstairs", "img/back_garden.jpg");
        east_corridor = new Room("in the corridor upstairs. It's the cleanest room you've seen yet", "img/east_corridor.jpg");
        waiting_room = new Room("in the waiting room. It's quite small but it has to contain some ammo. Looks like you can unlock to access to the main hall from here", "img/waiting_room.jpg");
        break_room = new Room("in the break room. Not much useful things here but it's a safe place", "img/break_room.jpg");
        roof = new Room("in the roof of the station. It's pouring rain here. There's a small building downstairs", "img/roof.jpg");
        backyard_room = new Room("in the backyard room. There might be something useful ..", "img/backyard_room.jpg");
        
        //others
        undergrounds = new Room("in the undergrounds of the station. You escaped!", "outside.jpg");
        
        //Add all the rooms in 'rooms'
        rooms.put("outside", outside);
        rooms.put("station hall", station_hall);
        rooms.put("station 1st floor", station_1st_floor);
        rooms.put("west wing", west_wing);
        rooms.put("locker_room", locker_room);
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
        rooms.put("chief office", waiting_room);
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
        station_1st_floor.setExit("east", waiting_room);
        station_1st_floor.setExit("down", station_hall);
        
        // West side
        west_wing.setExit("east", station_hall);
        west_wing.setExit("west", locker_room);
        west_wing.setExit("north", west_stairs);
        
        locker_room.setExit("east", west_wing);
        
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
        east_corridor.setExit("west", waiting_room);
        east_corridor.setExit("east", roof);
        east_corridor.setExit("north", break_room);
        
        break_room.setExit("south", east_corridor);
        
        waiting_room.setExit("east", east_corridor);
        waiting_room.setExit("west", station_1st_floor);
        
        roof.setExit("west", east_corridor);
        roof.setExit("down", backyard_room);
        
        backyard_room.setExit("up", roof);
        
        //others
        undergrounds.setExit("up", station_hall);

        
        // place objects in rooms
        station_hall.addItem(spray);
        locker_room.addItem(ammo);
        black_room.addItem(herb);
        squad_office.addItem(ammo);
        waiting_room.addItem(ammo);
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
        
        medal.setRoom(station_hall); // set the use location for medal to station_hall
        medal.setNeeded(2); // set the number of medals needed to unlock
        medal.SetUseOn("down"); // set the direction the item can be used on
        
        currentRoom = outside;  // start game outside    
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
    	gui.println("");
        gui.println("Residu.. Evil (because of copyright)");
        gui.println("You are Leonn, a rookie police officer in Fock City");
        gui.println("Due to a virus, the city is now infested with yellow vests");
        gui.println("It's night-time and yellow vests are moving fast. You have to find a way out of this city");
        gui.println("Your only solution now is to take refuge in the police station");
        gui.println("Can you make it out alive?");
        gui.println("Type 'help' if you need help.");
        gui.println("");
        look();
    }
    
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        gui.println("");
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        	if(command.hasSecondWord())
        		gui.println("Just use help alone");
        	else
        		printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("look"))
        	look();
        else if (commandWord.equals("pickup"))
        	pickup(command);
        else if (commandWord.equals("shoot"))
        	shoot(command);
        else if (commandWord.equals("use"))
        	use(command);
        else if (commandWord.equals("player"))
        	if(command.hasSecondWord())
        		gui.println("Just use player alone");
        	else
        		player();
        else if (commandWord.equals("unlock"))
        	unlock(command);
        else if (commandWord.equals("reload"))
        	if(command.hasSecondWord())
        		gui.println("Just use reload alone");
        	else
        		reload();
        else if (commandWord.equals("info"))
     	   info(command);
        else if (commandWord.equals("back"))
        	if(command.hasSecondWord())
        		gui.println("Just use back alone");
        	else
        		back();
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
        step(command);
        gui.println("");
    }
    
    //happens after a command
    //ends the game if you are dead
    //ends the game if you escaped
    private void step(Command command){
    	
    	if(command.isUnknown()) {
            return;
        }
    	if(command.getCommandWord().equals("reload")
    			|| command.getCommandWord().equals("pickup")
    			|| command.getCommandWord().equals("use")
    			|| command.getCommandWord().equals("unlock")
    			|| command.getCommandWord().equals("shoot")){   		
    		if(!currentRoom.getZombies().isEmpty()){
    			gui.println("==========================================================");
    			gui.println("");
            	for(int i=0;i<currentRoom.getZombies().size();i++){
            		gui.print("Yellow vest " + (i+1) + ": ");
            		gui.println(currentRoom.getZombies().get(i).attack(player));		
            	}
            	gui.println("Your health: " + player.getHealth());
            }
        	if(player.getHealth()<=0){
        		gui.println("You are dead!");
        		endGame();
        	}
    	}
    	if(currentRoom.equals(rooms.get("undergrounds"))) {
    		endGame();
    	}
    	gui.println("==========================================================");
    	return;
    }
    
    // implementations of user commands:
    
    /**
     * Print out some help information.
     */
    private void printHelp() {
        gui.println("Your command words are:");
        parser.showCommands();
        gui.print("\n");
        gui.println("go *direction* = move to next location");
        gui.println("quit = stop the game");
        gui.println("shoot *ZombieNumber* (ex: 2 yellow vests, 'shoot 1' means shoot on the yellow vest number 1) = fire on a yellow vest");
        gui.println("look = returns the description of the current room");
        gui.println("pickup *ItemName* = get in your inventory the named item");
        gui.println("player = shows your stats");
        gui.println("unlock *direction* = unlocking a path");
        gui.println("reload = reload your current weapon");
        gui.println("info *itemName* = display the description of the named item");
        gui.println("back = return on the previous room");
    }

    private void look() {
    	gui.showImage(currentRoom.getImageName());
        gui.println(currentRoom.getLongDescription());
        gui.println("");
    }
    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }
	
        String direction = command.getSecondWord();
	
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
	
        if (nextRoom == null) {
            gui.println("There is no door!");
            return;
        }
        
        // verifie que le passage n'est pas ferme
        else if(!currentRoom.getStates().isEmpty() && currentRoom.getStates().containsKey(direction)){
        	if(currentRoom.getState(direction)==true) {
        		gui.println("This access is locked");
        		return;
        	}	
        }
        	
        // verifie que l'acces n'est pas bloque depuis l'autre piece
        else if(!nextRoom.getStates().isEmpty()){
        	String opposite = nextRoom.getOpposite(direction);
            if(nextRoom.getStates().containsKey(opposite)) {      	
            	if(nextRoom.getState(opposite)==true) {
            		gui.println("This access is locked from the other side");
            		return;
            	}
            }
        }	
        
        if(!currentRoom.equals(rooms.get("outside"))) {
        	if(!currentRoom.getZombies().isEmpty()){
        		Random rand = new Random();
            	if(rand.nextInt(10) >= 7) {
            		player.isAttacked();
            		gui.println("A yellow vest bite you while you were escaping!");
            	}
            	else {
            		gui.println("You managed to flee without getting hurt");
            	}
            	gui.println("Your health: " + player.getHealth());            	
        	}
        }
        
        roadHistory.push(currentRoom);
        currentRoom = nextRoom;
        gui.println(currentRoom.getLongDescription());
        if(currentRoom.getImageName() != null)
            gui.showImage(currentRoom.getImageName());
    }
    
    
    //affiche les informations du joueur
    private void player(){
    	gui.println("Your current health: " + player.getHealth());
    	gui.println("Ammo in weapon: " + player.getGun().getAmmo());
    	gui.print("Your items: ");
    	for(int i=0;i<player.getItems().size();i++) {
    		gui.print(player.getItems().get(i).getName() + " x" + player.getItems().get(i).getNumber() + " ");
    	}
    	gui.println("");
    }
    
    
    //recharge l'arme
    private void reload() {
    	if(!player.getItems().contains(ammo)) {
    		gui.println("You don't have any ammo in your inventory dude \n");
            return;
    	}
    	
    	if(player.getGun().getAmmo() == player.getGun().max_ammo) {
    		gui.println("Your weapon is fully charged already! \n");
            return;
    	}
    	
    	int pos = player.getItems().lastIndexOf(ammo);
    	int ammo_needed = player.getGun().max_ammo - player.getGun().getAmmo();
    	int ammo_available = player.getItems().get(pos).getNumber();
    	
    	gui.println("You reload your weapon");
    	
    	if(ammo_needed > ammo_available) {
    		player.getGun().setAmmo(player.getGun().getAmmo() + ammo_available);
    	}
    	else {
    		player.getGun().setAmmo(player.getGun().getAmmo() + ammo_needed);
    		player.getItems().get(pos).setNumber(player.getItems().get(pos).getNumber() - ammo_needed);
    		gui.println("Weapon fully reloaded. Ammo in weapon: " + player.getGun().getAmmo());
    	}
    	
    	if(player.getItems().get(pos).getNumber() == 0) {
    		player.getItems().remove(pos);
    	}
    	
    	return;
    } 
    
    //cible et tire sur un ennemi
    private void shoot (Command command){
    	if(player.getGun().getAmmo() == 0) {
    		gui.println("No more ammo!");
    		return;
    	}
    	
    	if(!command.hasSecondWord()) {
            gui.println("Shoot who?");
            return;
        }
    	
    	if(currentRoom.getZombies().isEmpty()) {
    		gui.println("No yellow vests here!");
    		return;
    	}
    	
    	int zombie_number =  Integer.parseInt(command.getSecondWord());
    	if(zombie_number > currentRoom.getZombies().size()) {
    		gui.println("Not that much yellow vests here! What are you aiming at?");
    		return;
    	}
    	Zombie zombie = currentRoom.getZombies().get(zombie_number -1);
    	Random rand = new Random();
    	
    	if(rand.nextInt(10) >= 6) {
    		zombie.setAggro(zombie.getAggro() - 50);
    		if(zombie.getAggro() < 0)
    			zombie.setAggro(0);
    		gui.println("Yellow vest " + zombie_number + " flinched!");
    	}
    	
    	zombie.setHealth(zombie.getHealth()-1);
    	player.getGun().setAmmo(player.getGun().getAmmo() -1);
    	
    	gui.println("Ammo left in gun: " + player.getGun().getAmmo());
    	gui.println("Yellow vest " + zombie_number + " has " + zombie.getHealth() + " hp left !");
    	
    	if(zombie.getHealth() == 0) {
    		gui.println("Yellow vest " + zombie_number + " is dead !");
    		ArrayList<Zombie> zombies = currentRoom.getZombies();
    		zombies.remove(zombie_number -1);
    		currentRoom.setZombies(zombies);
    	}
    	
    }
    
    //utilise un objet
    private void use(Command command) {
    	
    	if(!command.hasSecondWord()) {
            gui.println("Use what?");
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
    			gui.println("What is that?");
    			break;
    	}
    	
    }
    
    
    private void useHeal(HealItem item){
    	
    	if(!player.getItems().contains(item)) {
			gui.println("You don't have any " + item.getName());
            return;
		}
		
		if(player.getHealth() == player.max_health) {
			gui.println("You are full health already.");
			gui.println("Your current health: " + player.getHealth());
            return;
		}
		
		player.setHealth(player.getHealth() + item.getHealing());
		if(player.getHealth()>player.max_health)
			player.setHealth(player.max_health);
		
		player.removeItem(item);
		
		gui.println("You used a " + item.getName());
		gui.println("Your current health: " + player.getHealth());
		return;
    }
    
    
    private void useKey(KeyItem item){
    	
    	if(!player.getItems().contains(item)) {
			gui.println("You don't have any " + item.getName());
            return;
		}
		
		if(!currentRoom.equals(item.getRoom())){
			gui.println("You can't use this here");
            return;
		}
		
		player.removeItem(item);
		
		gui.println("You used " + item.getName());
		
		item.setNeeded(item.getNeeded() -1);
		if(item.getNeeded() == 0){
			currentRoom.setState(item.getUseOn(), false);
			currentRoom.setItemNeeded(item.getUseOn(), null);
			gui.println("You've unlocked the " + item.getUseOn() + " access");
		}
		else {
			gui.println("You need " + item.getNeeded() + " more");
		}
    }

    
    
    private void pickup(Command command){
    	
    	if(currentRoom.getItems().isEmpty())	
    		gui.println("There is no item here !");
    	
    	else if(!command.hasSecondWord()) {
    		gui.println("Pickup what?");
    	}
    	
    	else if(currentRoom.haveItemName(command.getSecondWord())){
    		
    		Item item = Item.findByName(command.getSecondWord(), currentRoom.getItems());
    		gui.println("You picked up " + item.getName());
    		
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
        	gui.println("There's nothing to unlock in this direction");
        }
    	
    	else if(!command.hasSecondWord()) {
            gui.println("Unlock what?");
        }
    	
    	else if(currentRoom.getState(command.getSecondWord()) == false) {
        	gui.println("This access is already unlocked");
        }
    	
    	else if(currentRoom.getItemNeeded(command.getSecondWord()) != null) {
    		gui.println("You need to use an specific item first");
    	}
    	
    	else {
    		currentRoom.setState(command.getSecondWord(), false);
    		gui.println("You've unlocked the " + command.getSecondWord() + " access");
    	}
    	
    }
    
    private void info(Command command) {
    	
    	if(!command.hasSecondWord()) {
            gui.println("Info on what?");
        }
    	
    	else {
    		switch(command.getSecondWord()){
        	
    			case "herb":
    				gui.println(herb.getDescription());
    				break;
    			
    			case "spray":
    				gui.println(spray.getDescription());
    				break;
    			
    			case "medal":
    				gui.println(medal.getDescription());
    				break;
    			
    			case "ammo":
    				gui.println(ammo.getDescription());
    				break;
    		
    			default:
    				gui.println("What is that?");
    				break;
    		}
    	}
    }
    
    private void back() {
    	
    	try {
			currentRoom =roadHistory.pop();
			gui.println(currentRoom.getLongDescription());
	        if(currentRoom.getImageName() != null)
	            gui.showImage(currentRoom.getImageName());
	        
		} catch (Exception e) {
			gui.println("You can't go back any further");
		}
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    
    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.showImage("img/gameover.jpg");
        gui.enable(false);
    }
}
