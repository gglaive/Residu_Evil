import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Geoffrey Glaive, Florian Vazelle
 * @version 04.2019
 * GameEngine is the most important class. It creates the game elements, and allows all classes to communicate.
 */

public class GameEngine {
    private Parser parser;
    private UserInterface gui;

    private HashMap<String, Room> rooms = new HashMap<String, Room>();
    private Player player;

    private Item ammo = new Item("ammo", "Munitions for a simple gun", 7, 1);
    private HealItem herb = new HealItem("herb", "An healing item that restore 1 health when used", 1, 1, 1);
    private HealItem spray = new HealItem("spray", "An healing item that restore all health when used", 1, 1, 2);
    private KeyItem medal = new KeyItem("medal", "A medal that should be used somewhere ..", 1, 1);
    private KeyItem beamer = new KeyItem("beamer", "Use it first to save the current room in the beamer, then re-use it to teleport from anywhere to the saved room. NOTE : This item should not be used in your classic playthrough", 1, 2);
    private int beamerCount = 0;
    private Weapon matilda = new Weapon("Matilda", 1, 8, 12);

    private Stack<Room> roadHistory = new Stack<Room>();

    /**
     * Create the game and initialize its internal map.
     */
    public GameEngine() {
        createRooms();
        parser = new Parser();
        player = new Player(3,3,4,rooms.get("outside"));
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
        Room outside, station_hall, station_1st_floor, west_wing, locker_room, west_stairs, black_room, west_corridor, squad_office, reserve, library, east_wing, bathroom, police_office, back_garden, east_corridor, waiting_room, break_room, roof, backyard_room, undergrounds, warning;

        // create the rooms
        // start
        outside = new Room("in the streets of Fock City. You can see the police station from here. Yellow vests are all around you so you should hurry", "img/outside.jpg");

        // Police Station rooms
        // Center
        station_hall = new Room("in the main hall of the police station. This place is huge. You remember there's a secret passage under the big statue here, but you need two medaillons first ..", "img/station_hall.jpg");
        station_1st_floor = new Room("above the police station hall", "img/station_1st_floor.jpg");

        //West side
        west_wing = new Room("in the west wing. The corridor is tight and only the moonlight brighten a bit this place..", "img/west_wing.jpg");
        locker_room = new Room("in the locker_room. There may be useful leftovers to take here. Also, you can see a hole in the ceiling", "img/locker_room.jpg");
        west_stairs = new Room("in the front of the stairs. You can see a small room hide behind those", "img/west_stairs.jpg");
        black_room = new Room("in what looks like a black room. It looks safe but there's not much to see here", "img/black_room.jpg");
        west_corridor = new Room("in the corridor upstairs. A fading light is somehow lightening it", "img/west_corridor.jpg");
        squad_office = new Room("in the office of the squad unity. There is a huge hole on the ground leading down", "img/squad_office.jpg");
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
        undergrounds = new Room("in the undergrounds of the station. You escaped!", "img/gameover.jpg");
        warning = new Room("in a strange room. It says 'THE ROOM AFTER TELEPORTS YOU ANYWHERE ON THE MAP. IF YOU DON'T WANT TO USE IT, GO BACK'", "img/warning.gif");

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

        ArrayList<Room> roomsList = new ArrayList<Room>(rooms.values());
        //Randomizer room
        RoomRandomizer roomRandomizer = new RoomRandomizer(roomsList);
        
        //Teleport Room
        TransporterRoom transporter = new TransporterRoom("in a weird looking place that should not be here. You feel like leaving this place could leave you anywhere", "img/teleport_room.png", roomRandomizer);
        
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
        station_1st_floor.setExit("up", warning);
        
        warning.setExit("down", station_1st_floor);
        warning.setExit("up", transporter);

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
        squad_office.setExit("down", locker_room);

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
        station_hall.addItem(beamer); // It exists only because it is asked on Iteration 4, do not pick it up on a basic playthrough
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
        locker_room.setState("east", true);
        library.setState("east", true);
        east_corridor.setState("west", true);

        // add an item requirement for unlocking
        station_hall.setItemNeeded("down", medal);

        medal.setRoom(station_hall); // set the use location for medal to station_hall
        medal.setNeeded(2); // set the number of medals needed to unlock
        medal.SetUseOn("down"); // set the direction the item can be used on

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

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
		case HELP:
			if(command.hasSecondWord())
        		gui.println("Just use help alone");
        	else
        		printHelp();
			break;

		case GO:
			goRoom(command);
			break;

		case LOOK:
			look();
			break;

		case PICKUP:
			pickup(command);
			break;

		case DROP:
			drop(command);
			break;

		case SHOOT:
			shoot(command);
			break;

		case USE:
			use(command);
			break;

		case TEST:
			test(command);
			break;

		case PLAYER:
			if(command.hasSecondWord())
        		gui.println("Just use player alone");
        	else
        		player();
			break;

		case UNLOCK:
			unlock(command);
			break;

		case RELOAD:
			if(command.hasSecondWord())
        		gui.println("Just use reload alone");
        	else
        		reload();
			break;

		case INFO:
			info(command);
			break;

		case BACK:
			if(command.hasSecondWord())
        		gui.println("Just use back alone");
        	else
        		back();
			break;

		case QUIT:
			if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
			break;

		default:
			break;
		}

        /*
        if (commandWord == CommandWord.HELP)
        	printHelp();
        else if (commandWord == CommandWord.GO)
            goRoom(command);
        else if (commandWord == CommandWord.LOOK)
        	look();
        else if (commandWord == CommandWord.PICKUP)
        	pickup(command);
        else if (commandWord == CommandWord.DROP)
        	drop(command);
        else if (commandWord == CommandWord.SHOOT)
        	shoot(command);
        else if (commandWord == CommandWord.USE)
        	use(command);
        else if (commandWord == CommandWord.PLAYER)
        	if(command.hasSecondWord())
        		gui.println("Just use player alone");
        	else
        		player();
        else if (commandWord == CommandWord.UNLOCK)
        	unlock(command);
        else if (commandWord == CommandWord.RELOAD)
        	if(command.hasSecondWord())
        		gui.println("Just use reload alone");
        	else
        		reload();
        else if (commandWord == CommandWord.INFO)
     	   info(command);
        else if (commandWord == CommandWord.BACK)
        	if(command.hasSecondWord())
        		gui.println("Just use back alone");
        	else
        		back();
        else if (commandWord == CommandWord.QUIT) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }*/
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
    	if(command.getCommandWord() == CommandWord.RELOAD
    			|| command.getCommandWord() == CommandWord.PICKUP
    			|| command.getCommandWord() == CommandWord.USE
    			|| command.getCommandWord() == CommandWord.UNLOCK
    			|| command.getCommandWord() == CommandWord.SHOOT){
    		if(!player.getCurrentRoom().getZombies().isEmpty()){
    			gui.println("==========================================================");
    			gui.println("");
            	for(int i=0;i<player.getCurrentRoom().getZombies().size();i++){
            		gui.print("Yellow vest " + (i+1) + ": ");
            		gui.println(player.getCurrentRoom().getZombies().get(i).attack(player));
            	}
            	gui.println("Your health: " + player.getHealth());
            }
        	if(player.getHealth()<=0){
        		gui.println("You are dead!");
        		endGame();
        	}
    	}
    	if(player.getCurrentRoom().equals(rooms.get("undergrounds"))) {
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
        gui.println(parser.showCommands());
        gui.print("\n");
        gui.println("go *direction* = move to next location");
        gui.println("quit = stop the game");
        gui.println("shoot *ZombieNumber* (ex: 2 yellow vests, 'shoot 1' means shoot on the yellow vest number 1) = fire on a yellow vest");
        gui.println("look = returns the description of the current room");
        gui.println("pickup *ItemName* = get in your inventory the named item");
        gui.println("drop *ItemName* = put the named item from your inventory to the current room");
        gui.println("use *ItemName* = try to 'consume' the named item and apply its effects");
        gui.println("player = shows your stats");
        gui.println("unlock *direction* = unlocking a path");
        gui.println("reload = reload your current weapon");
        gui.println("info *itemName* = display the description of the named item");
        gui.println("back = return on the previous room");
    }

    private void look() {
    	gui.showImage(player.getCurrentRoom().getImageName());
        gui.println(player.getCurrentRoom().getLongDescription());
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
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            gui.println("There is no door!");
            return;
        }

        // verifie que le passage n'est pas ferme
        else if(!player.getCurrentRoom().getStates().isEmpty() && player.getCurrentRoom().getStates().containsKey(direction)){
        	if(player.getCurrentRoom().getState(direction)==true) {
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

        if(!player.getCurrentRoom().equals(rooms.get("outside"))) {
        	if(!player.getCurrentRoom().getZombies().isEmpty()){
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

        roadHistory.push(player.getCurrentRoom());
        player.setCurrentRoom(nextRoom);
        gui.println(player.getCurrentRoom().getLongDescription());
        if(player.getCurrentRoom().getImageName() != null)
            gui.showImage(player.getCurrentRoom().getImageName());
    }

    //execute a file to play the game
    private void test(Command command) {
      	if(!command.hasSecondWord()) {
      		  gui.println("Test what?");
      	} else {
            Path pathFile = Paths.get(command.getSecondWord());
            try {
                List<String> lines = Files.readAllLines(pathFile, Charset.forName("UTF-8"));
                for (String line : lines) {
                    //System.out.println(line);
                    interpretCommand(line);
                }
            } catch(SecurityException e) {
                System.out.println(e);
            } catch(IOException e) {
                System.out.println(e);
            }
        }
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

    	if(player.getCurrentRoom().getZombies().isEmpty()) {
    		gui.println("No yellow vests here!");
    		return;
    	}

    	int zombie_number =  Integer.parseInt(command.getSecondWord());
    	if(zombie_number > player.getCurrentRoom().getZombies().size()) {
    		gui.println("Not that much yellow vests here! What are you aiming at?");
    		return;
    	}
    	Zombie zombie = player.getCurrentRoom().getZombies().get(zombie_number -1);
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
    		ArrayList<Zombie> zombies = player.getCurrentRoom().getZombies();
    		zombies.remove(zombie_number -1);
    		player.getCurrentRoom().setZombies(zombies);
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
    		
    		case "beamer":
    			useBeamer(beamer);
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

		if(player.getHealth() == player.getMax_health()) {
			gui.println("You are full health already.");
			gui.println("Your current health: " + player.getHealth());
            return;
		}

		player.setHealth(player.getHealth() + item.getHealing());
		if(player.getHealth()>player.getMax_health())
			player.setHealth(player.getMax_health());

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

  		if(!player.getCurrentRoom().equals(item.getRoom())){
  			gui.println("You can't use this here");
              return;
  		}

  		player.removeItem(item);

  		gui.println("You used " + item.getName());

  		item.setNeeded(item.getNeeded() -1);
  		if(item.getNeeded() == 0){
  			player.getCurrentRoom().setState(item.getUseOn(), false);
  			player.getCurrentRoom().setItemNeeded(item.getUseOn(), null);
  			gui.println("You've unlocked the " + item.getUseOn() + " access");
  		}
  		else {
  			gui.println("You need " + item.getNeeded() + " more");
  		}
    }

    private void useBeamer(KeyItem beamer) {
    	
    	if(!player.getItems().contains(beamer)) {
		     gui.println("You don't have any " + beamer.getName());
		     return;
		}
    	
    	if(beamerCount == 0) {
    		beamer.setRoom(player.getCurrentRoom());
    		gui.println("The beamer saved the current room");
    		beamerCount = 1;
    	}
    	
    	else if(beamerCount == 1) {
    		player.setCurrentRoom(beamer.getRoom());
    		look();
    		gui.println("The beamer teleported you in the saved location. You need to resave a location before reusing it");
    		beamerCount = 0;
    	}
    }

    private void pickup(Command command){

    	if(player.getCurrentRoom().getItems().isEmpty())
    		gui.println("There is no item here !");

    	else if(!command.hasSecondWord()) {
    		gui.println("Pickup what?");
    	}

    	else if(!player.getCurrentRoom().haveItemName(command.getSecondWord())) {
    		gui.println("There's no such item here, or it doesn't exist");
    	}

    	else {

    		Item item = Item.findByName(command.getSecondWord(), player.getCurrentRoom().getItems());

    		if(!player.getItems().contains(item)) {
    			if(player.getItems().size() == player.getMax_inventory()) {
    				gui.println("Your inventory is full already!");
    				return;
    			}
    			else {
    				player.getItems().add(item);
    			}
    		}

        	else {
        		int pos = player.getItems().lastIndexOf(item);
        		player.getItems().get(pos).setNumber(player.getItems().get(pos).getNumber() + item.initial_number);
        	}

    		gui.println("You picked up " + item.getName());

    		player.getCurrentRoom().removeItem(item);
    	}
    }

    private void drop(Command command) {

    	if(!command.hasSecondWord()) {
    		gui.println("Drop what?");
    	}

    	else {
    		Item item = Item.findByName(command.getSecondWord(), player.getItems());

        	if(!player.getItems().contains(item)) {
        		gui.println("You don't have that item on you, or it doesn't exist");
        	}

        	else {
        		player.getCurrentRoom().addItem(item);
        		player.getItems().remove(item);
        		gui.println("You dropped " + item.getName());
        	}
    	}
    }

    private void unlock(Command command) {

    	if(player.getCurrentRoom().getStates().isEmpty()) {
        	gui.println("There's nothing to unlock in this direction");
        }

    	else if(!command.hasSecondWord()) {
            gui.println("Unlock what?");
        }

    	else if(player.getCurrentRoom().getState(command.getSecondWord()) == false) {
        	gui.println("This access is already unlocked");
        }

    	else if(player.getCurrentRoom().getItemNeeded(command.getSecondWord()) != null) {
    		gui.println("You need to use an specific item first");
    	}

    	else {
    		player.getCurrentRoom().setState(command.getSecondWord(), false);
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
    				
    			case "beamer":
    				gui.println(beamer.getDescription());
    				break;

    			default:
    				gui.println("What is that?");
    				break;
    		}
    	}
    }

    private void back() {

    	try {
			player.setCurrentRoom(roadHistory.pop());
			gui.println(player.getCurrentRoom().getLongDescription());
	        if(player.getCurrentRoom().getImageName() != null)
	            gui.showImage(player.getCurrentRoom().getImageName());

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
