import java.util.HashMap;

/**
 * CommandWords permet de verifié la véracité des commandes.
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
     * Regarde si une chaine de caractère est une commande valide.
     * @method isCommand
     * @param {String} aString -
     * @return {boolean} - true si la chaine de caractère en
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
}
