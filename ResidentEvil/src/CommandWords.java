/**
 * CommandWords permet de verifié la véracité des commandes.
 */
public class CommandWords {
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "shoot", "flee", "look", "pickup", "use"
    };
    
    /**
     * Constructeur - initialise les commandes.
     */
    public CommandWords() {
        // nothing to do at the moment...
    }

    /**
     * Regarde si une chaine de caractère est une commande valide.
     * @method isCommand
     * @param {String} aString -
     * @return {boolean} - true si la chaine de caractère en
     *  parametre est une commande valide, false sinon
     */
    public boolean isCommand(String aString) {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString)) return true;
        }
        return false;
    }

    /**
     * Permet d'afficher la liste de toutes les commandes
     * reconnu par le jeu.
     * @method showAll
     */
    public void showAll() {
    	for(String command : validCommands) {
    		System.out.print(command + "  ");
    	}
    	System.out.println();
    }
}
