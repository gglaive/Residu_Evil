package pkg_engine;
import java.util.StringTokenizer;

import pkg_command.Command;
import pkg_command.CommandWords;

/**
 * @author Geoffrey Glaive
 * @version 04.2019
 * Parser of the "World of Zuul" game modified.
 */

public class Parser 
{

    private CommandWords commands;  // holds all valid command words

    /**
     * Create a new Parser.
     */
    public Parser() 
    {
        commands = new CommandWords();
    }

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     */
    public Command getCommand(String inputLine) 
    {
        //String inputLine = "";   // will hold the full input line
        String word1;
        String word2;

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = null;
        if(tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // get second word
        else
            word2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        return new Command(commands.getCommandWord(word1), word2);

    }

    /**
     * Print out a list of valid command words.
     */
    public String showCommands()
    {
    	//commands.showAll();
    	StringBuilder commandList = new StringBuilder("");
    	for (String command : commands.getValidCommands().keySet()) {
            commandList.append(command + "  ");
          }
          return commandList.toString();
    }
}
