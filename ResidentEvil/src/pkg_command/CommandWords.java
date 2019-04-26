package pkg_command;
import java.util.HashMap;

/**
 * @author Geoffrey Glaive
 * @version 04.2019
 * Class that check if a command is valid or not.
 */

public class CommandWords {

	private HashMap<String, CommandWord> validCommands;

    /**
     * Constructeur - initialise les commandes.
     */
    public CommandWords() {
    	validCommands = new HashMap<String, CommandWord>();
        for (CommandWord commands : CommandWord.values()) {
          if (commands != CommandWord.UNKNOWN) {
						for(String command : commands.getValues())
            	validCommands.put(command, commands);
          }
        }
    }

    public CommandWord getCommandWord(String commandWord) {
      CommandWord command = (CommandWord)validCommands.get(commandWord);
      if (command != null) {
        return command;
      }

      return CommandWord.UNKNOWN;
    }

    /**
     * Regarde si une chaine de caractere est une commande valide.
     * @method isCommand
     * @param {String} aString -
     * @return {boolean} - true si la chaine de caractere en
     *  parametre est une commande valide, false sinon
     */
    public boolean isCommand(String aString) {
    	return validCommands.containsKey(aString);
    }

    public void showAll() {
      for (String command : validCommands.keySet()) {
        System.out.print(command + "  ");
      }
      System.out.println();
    }

	public HashMap<String, CommandWord> getValidCommands() {
		return validCommands;
	}

	public void setValidCommands(HashMap<String, CommandWord> validCommands) {
		this.validCommands = validCommands;
	}
    
}
