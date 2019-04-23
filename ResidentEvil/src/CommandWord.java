import java.util.List;
import java.util.Arrays;

/**
 * @author Geoffrey Glaive, Florian Vazelle
 * @version 03.2019
 * Class that matches words typed by the player with functional commands.
 */

public enum CommandWord {

	TEST("test"),
	GO("go", "aller", "يذهب"),  QUIT("quit", "quitter"), HELP("help", "aide"), SHOOT("shoot", "tirer"),
	LOOK("look", "regarder"), PICKUP("pickup", "prendre"), DROP("drop", "deposer"),
	USE("use", "utiliser"), PLAYER("player", "joueur"), UNLOCK("unlock", "deverouiller"),
	RELOAD("reload", "recharger"), INFO("info"), BACK("back", "retour"), UNKNOWN("?");

	  private List<String> commandString;

	  private CommandWord(String ...commandString){
	    this.commandString = Arrays.asList(commandString);
	  }

	  public List<String> getValues(){
	    return commandString;
	  }
}
