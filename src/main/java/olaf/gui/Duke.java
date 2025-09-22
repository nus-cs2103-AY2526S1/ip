package olaf.gui;

import olaf.Olaf;

/**
 * Wrapper class for GUI calling Olaf backend.
 */
public class Duke {

    private Olaf olafBot = new Olaf("./data/Olaf.txt");

    /**
     * Returns Olaf's response string for given input.
     */
    public String getResponse(String input) {
        return olafBot.getResponse(input);
    }

    /**
     * Returns the welcome message for the bot at the start.
     *
     * @return Welcome message
     */
    public String getWelcomeMessage() {
        return "Heyyos! Im Olaf! Your personal snowman\n"
                + "What can I do for you in this beautiful day?\n";
    }
}
