

public enum CommandWord {

	GO("go"),  QUIT("quit"), HELP("help"), SHOOT("shoot"), LOOK("look"), PICKUP("pickup"),
	DROP("drop"), USE("use"), PLAYER("player"), UNLOCK("unlock"), RELOAD("reload"), INFO("info"), BACK("back"), UNKNOWN("?");

	  private String commandString;

	  private CommandWord(String commandString){
	    this.commandString = commandString;
	  }
	  
	  public String toString(){
	    return commandString;
	  }
}
